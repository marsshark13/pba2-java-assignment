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
package dao;

import model.Student;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Student readStudent(ResultSet rs) throws SQLException {
        return new Student(rs.getInt("ID"), rs.getString("NAME"),
                rs.getString("EMAIL"), rs.getString("COURSE"), rs.getString("PHONE"));
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT ORDER BY ID");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) students.add(readStudent(rs));
        }
        return students;
    }

    public Student getStudentById(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? readStudent(rs) : null;
            }
        }
    }

    private void bind(PreparedStatement ps, Student student) throws SQLException {
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getCourse());
        ps.setString(4, student.getPhone());
    }

    public boolean addStudent(Student student) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO STUDENT (NAME, EMAIL, COURSE, PHONE) VALUES (?, ?, ?, ?)")) {
            bind(ps, student);
            return ps.executeUpdate() == 1;
        }
    }

    public boolean updateStudent(Student student) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "UPDATE STUDENT SET NAME = ?, EMAIL = ?, COURSE = ?, PHONE = ? WHERE ID = ?")) {
            bind(ps, student);
            ps.setInt(5, student.getId());
            return ps.executeUpdate() == 1;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM STUDENT WHERE ID = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
}
