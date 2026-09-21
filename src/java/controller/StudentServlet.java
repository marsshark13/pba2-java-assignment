/*
 * PBA 2 - Employee Management System
 *
 * Name: SHAFIQ ARIF BIN SHAMSUL ARIF
 * Registration Number: 01DIT24F1203
 *
 * Name: MUHAMMAD AMSYAR ADAM BIN SHARUDIN
 * Registration Number: 01DIT24F1224
 *
 * Name: MUHAMMAD ADAM DANIAL BIN MOHAMMAD RIDZUAN
 * Registration Number: 01DIT24F11995
 *
 * Class: DITP5B
 */
package controller;

import dao.StudentDAO;
import model.Student;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() { studentDAO = new StudentDAO(); }

    private int parseId(String value) {
        int id = Integer.parseInt(value);
        if (id <= 0) throw new NumberFormatException();
        return id;
    }

    private String value(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        return value == null ? "" : value.trim();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("new".equals(action)) {
                request.getRequestDispatcher("/student-form.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                Student student = studentDAO.getStudentById(parseId(request.getParameter("id")));
                if (student == null) {
                    response.sendError(404, "The selected employee no longer exists.");
                    return;
                }
                request.setAttribute("student", student);
                request.getRequestDispatcher("/student-form.jsp").forward(request, response);
            } else {
                request.setAttribute("students", studentDAO.getAllStudents());
                request.getRequestDispatcher("/student-list.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            response.sendError(400, "Please select a valid employee.");
        } catch (SQLException ex) {
            log("Unable to load employee records", ex);
            response.setStatus(503);
            request.setAttribute("error", "Sorry, records could not be loaded. Please check that the employee database is running and try again.");
            request.getRequestDispatcher("/student-list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if ("delete".equals(request.getParameter("action"))) {
            try {
                boolean deleted = studentDAO.deleteStudent(parseId(request.getParameter("id")));
                request.getSession().setAttribute(deleted ? "message" : "error",
                        deleted ? "Employee deleted successfully." : "The selected employee no longer exists.");
            } catch (NumberFormatException ex) {
                response.sendError(400, "Please select a valid employee.");
                return;
            } catch (SQLException ex) {
                log("Unable to delete employee", ex);
                request.getSession().setAttribute("error", "Sorry, the employee could not be deleted. Please try again.");
            }
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        String idValue = value(request, "id");
        int id = 0;
        try {
            if (!idValue.isEmpty()) id = parseId(idValue);
        } catch (NumberFormatException ex) {
            response.sendError(400, "Please select a valid employee.");
            return;
        }
        Student student = new Student(id, value(request, "name"), value(request, "email"),
                value(request, "course"), value(request, "phone"));
        request.setAttribute("student", student);
        String error = null;
        if (student.getName().isEmpty() || student.getCourse().isEmpty()) {
            error = "Please enter a name and course.";
        } else if (student.getName().length() > 100 || student.getCourse().length() > 100) {
            error = "Please keep the name and course within 100 characters each.";
        } else if (student.getEmail().length() > 100 ||
                !student.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)+$")) {
            error = "Please enter a valid email address, such as name@example.com (up to 100 characters).";
        } else if (student.getPhone().length() > 20 ||
                (!student.getPhone().isEmpty() && !student.getPhone().matches("[+0-9() -]{7,20}"))) {
            error = "Please enter a phone number of 7 to 20 characters using digits, spaces, +, - or parentheses, or leave it blank.";
        }
        if (error == null) {
            try {
                boolean success = id == 0 ? studentDAO.addStudent(student) : studentDAO.updateStudent(student);
                if (success) {
                    request.getSession().setAttribute("message", id == 0
                            ? "Employee added successfully." : "Employee updated successfully.");
                    response.sendRedirect(request.getContextPath() + "/students");
                    return;
                }
                error = "The selected employee no longer exists. Please return to the employee list.";
            } catch (SQLException ex) {
                log("Unable to save employee", ex);
                error = "Sorry, your changes could not be saved. Please check the database and try again.";
                response.setStatus(503);
            }
        }
        request.setAttribute("error", error);
        request.getRequestDispatcher("/student-form.jsp").forward(request, response);
    }
}
