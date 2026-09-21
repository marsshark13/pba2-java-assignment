<%@page import="model.Student"%>
<%@page import="util.Html"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Student student =
            (Student) request.getAttribute("student");

    boolean editing = student != null && student.getId() > 0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>
        <%= editing ? "Edit Employee" : "Add Employee" %>
    </title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 40px;
        }

        .container {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button, .button {
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            text-decoration: none;
            cursor: pointer;
            border-radius: 5px;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            margin-bottom: 15px;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>
        <%= editing ? "Edit Employee" : "Add Employee" %>
    </h1>

    <%
        String error =
                (String) request.getAttribute("error");

        if (error != null) {
    %>

        <div class="error">
            <%= Html.escape(error) %>
        </div>

    <%
        }
    %>


    <form action="<%= request.getContextPath() %>/students"
          method="post">

        <% if (editing) { %>

            <input type="hidden"
                   name="id"
                   value="<%= student.getId() %>">

        <% } %>


        <label for="name">Name *</label>

        <input type="text"
               name="name" id="name" maxlength="100"
               required
               value="<%= student != null ? Html.escape(student.getName()) : "" %>">


        <label for="email">Email *</label>

        <input type="email"
               name="email" id="email" maxlength="100"
               required
               value="<%= student != null ? Html.escape(student.getEmail()) : "" %>">


        <label for="course">Course *</label>

        <input type="text"
               name="course" id="course" maxlength="100"
               required
               value="<%= student != null ? Html.escape(student.getCourse()) : "" %>">


        <label for="phone">Phone (optional)</label>

        <input type="text"
               name="phone" id="phone" maxlength="20"
               value="<%= student != null ? Html.escape(student.getPhone()) : "" %>">


        <button type="submit">

            <%= editing ? "Update Employee" : "Add Employee" %>

        </button>


        <a class="button"
           href="<%= request.getContextPath() %>/students">

            Cancel

        </a>

    </form>

</div>

<footer style="max-width:1000px;margin:24px auto;color:#555;font-size:14px">Academic demonstration for PBA 2. Records are used only to demonstrate record management. Please use fictional data and respect privacy.</footer>
</body>
</html>