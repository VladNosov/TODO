<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body onload="buildAjaxUrl()">
<script type="text/javascript" src="/js/datatablesUtil.js" defer></script>
<script type="text/javascript" src="/js/taskDatatables.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <h3><spring:message code="task.title"/></h3>
        <br/>
        <a class="btn btn-primary" onclick="add()">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <spring:message code="common.add"/>
        </a>
        <table class="table table-striped display" id="datatable">
            <thead>
            <tr>
                <th></th>
                <th><spring:message code="task.description"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${tasks}" var="task">
                <jsp:useBean id="task" scope="page" type="vn.todo.domain.Task"/>
                <tr>
                    <td><input type="checkbox" <c:if test="${task.complete}">checked</c:if> onclick="enable($(this), ${task.id})"/></td>
                    <td>${task.title}</td>
                    <td><a><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                    <td><a onclick="deleteRow(${task.todo.id}, ${task.id})"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitle"><spring:message code="task.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="complete" name="complete">

                    <div class="form-group">
                        <label for="title" class="control-label col-xs-3"><spring:message
                                code="task.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="title" name="title"
                                   placeholder="<spring:message code="task.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="save()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>