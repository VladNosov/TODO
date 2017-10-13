<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>TODO's</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3>TODO's</h3>
    <hr/>
    <a href="todos/create">Add TODO</a>
    <hr/>
    <table border="0" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Title</th>
            <th colspan="2">Action</th>
        </tr>
        </thead>
        <c:forEach items="${todos}" var="todo">
            <jsp:useBean id="todo" type="vn.todo.domain.Todo"/>
            <tr class="exceeded">
                <td><a href="/todo?id=${todo.id}">${todo.title}</a></td>
                <td><a href="todos/update?id=${todo.id}">Update</a></td>
                <td><a href="todos/delete?id=${todo.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>