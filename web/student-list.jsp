<%@page import="java.util.List"%>
<%@page import="model.Student"%>
<%@page import="util.Html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employee Management System</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 40px;
        }

        .container {
            width: 90%;
            max-width: 1000px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
        }

        h1 {
            margin-top: 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }

        th {
            background-color: #333;
            color: white;
        }

        .button {
            display: inline-block;
            padding: 8px 14px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        .message {
            background-color: #dff0d8;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 5px;
        }

        form {
            display: inline;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Employee Management System</h1>

    <%
        String message =
                (String) session.getAttribute("message");

        if (message != null) {
    %>

        <div class="message">
            <%= Html.escape(message) %>
        </div>

    <%
            session.removeAttribute("message");
        }
    %>


    <a class="button"
       href="<%= request.getContextPath() %>/students?action=new">

        Add Employee

    </a>


    <%
        String error = (String) request.getAttribute("error");
        if (error == null) error = (String) session.getAttribute("error");
        session.removeAttribute("error");
        if (error != null) {
    %>
        <p role="alert" style="background:#f8d7da;color:#721c24;padding:12px"><%= Html.escape(error) %></p>
    <% } %>
    <table>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
            <th>Phone</th>
            <th>Action</th>
        </tr>

        <%
            List<Student> students =
                    (List<Student>) request.getAttribute("students");

            if (students != null && students.isEmpty()) {
        %><tr><td colspan="6">No employees yet. Select Add Employee to create a record.</td></tr><%
            }
            if (students != null) {

                for (Student student : students) {
        %>

        <tr>

            <td><%= student.getId() %></td>

            <td><%= Html.escape(student.getName()) %></td>

            <td><%= Html.escape(student.getEmail()) %></td>

            <td><%= Html.escape(student.getCourse()) %></td>

            <td><%= Html.escape(student.getPhone()) %></td>

            <td>

                <a class="button"
                   href="<%= request.getContextPath() %>/students?action=edit&id=<%= student.getId() %>">

                    Edit

                </a>


                <form action="<%= request.getContextPath() %>/students"
                      method="post">

                    <input type="hidden"
                           name="action"
                           value="delete">

                    <input type="hidden"
                           name="id"
                           value="<%= student.getId() %>">

                    <button
                        type="submit"
                        class="button"
                        onclick="return confirm('Are you sure you want to delete this employee?');">

                        Delete

                    </button>

                </form>

            </td>

        </tr>

        <%
                }
            }
        %>

    </table>

</div>

<footer style="max-width:1000px;margin:24px auto;color:#555;font-size:14px">Academic demonstration for PBA 2. Records are used only to demonstrate record management. Please use fictional data and respect privacy.</footer>
</body>
</html>