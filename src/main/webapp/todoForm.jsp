<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="todo" type="vn.todo.domain.Todo" scope="request"/>
    <h3><spring:message code="${todo.isNew() ? 'todo.add' : 'todo.edit'}"/></h3>
    <hr>
    <form method="post" action="/todos">
        <input type="hidden" name="id" value="${todo.id}">
        <dl>
            <dt><spring:message code="todo.description"/>:</dt>
            <dd><input type="text" value="${todo.title}" name="title"></dd>
        </dl>
        <button type="submit"><spring:message code="common.save"/></button>
        <button onclick="window.history.back()" type="button"><spring:message code="common.cancel"/></button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>