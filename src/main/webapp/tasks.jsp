<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tasks</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="/">Home</a></h3>
    <h2>Tasks</h2>
    <hr/>
    <a href="todos/create">Add task</a>
    <hr/>
    <table border="0" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Title</th>
        </tr>
        </thead>
        <c:forEach items="${tasks}" var="task">
            <jsp:useBean id="task" type="vn.todo.domain.Task"/>
            <tr class="exceeded">
                <td><input type="checkbox" name="complete" ${task.complete ? "checked='checked'" : ""}/></td>
                <td>${task.title}</td>
                <td><a href="todos/update?id=${task.id}">Update</a></td>
                <td><a href="todos/delete?id=${task.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>