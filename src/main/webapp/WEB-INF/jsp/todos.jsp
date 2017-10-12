<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TODO's</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="/">Home</a></h3>
    <h2>TODO's</h2>
    <hr/>
    <a href="todos/create">Add TODO</a>
    <hr/>
    <table border="0" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Title</th>
        </tr>
        </thead>
        <c:forEach items="${todos}" var="todo">
            <jsp:useBean id="todo" type="vn.todo.domain.Todo"/>
            <tr class="exceeded">
                <td>${todo.title}</td>
                <td><a href="todos/update?id=${todo.id}">Update</a></td>
                <td><a href="todos/delete?id=${todo.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>