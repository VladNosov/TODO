<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <h3><spring:message code="todo.title"/></h3>
    <hr/>
    <a href="todos/create"><spring:message code="todo.add"/></a>
    <hr/>
    <table border="0" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><spring:message code="todo.description"/></th>
            <th colspan="2"><spring:message code="common.actions"/></th>
        </tr>
        </thead>
        <c:forEach items="${todos}" var="todo">
            <jsp:useBean id="todo" type="vn.todo.domain.Todo"/>
            <tr>
                <td><a href="/todo/${todo.id}/">${todo.title}</a></td>
                <td class="green"><a href="todos/update?id=${todo.id}"><spring:message code="common.update"/></a></td>
                <td class="red"><a href="todos/delete?id=${todo.id}"><spring:message code="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>