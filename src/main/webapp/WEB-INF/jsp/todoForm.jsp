<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Todo</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="/">Home</a></h3>
    <h2>${requestScope['javax.servlet.forward.request_uri'].contains('create') ? 'Create todo' : 'Edit todo'}</h2>
    <hr>
    <jsp:useBean id="todo" type="vn.todo.domain.Todo" scope="request"/>
    <form method="post" action="/todos">
        <input type="hidden" name="id" value="${todo.id}">
        <dl>
            <dt>Title:</dt>
            <dd><input type="text" value="${todo.title}" name="title"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>