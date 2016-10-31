<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Models.User" scope="session"/>
<html>
<head>
    <title>UoN IT Services</title>
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7/dist/css/bootstrap.min.css" />
</head>
    <body>
        <ul class="list-group">
            <c:forEach var="current" items="${requestScope.list}">
            <li class="list-group-item"><p>Issue <c:out value="${current.getIssueID()}"/></p>
                <p>Status: <c:out value="${current.getStatus()}"/></p>
                <p>Category: <c:out value="${current.getCategory()}"/></p>
                <p><c:out value="${current.getReportedDateTime()}"/></p>
                <p>User: <c:out value="${current.getUser()}"/></p>
            </li>
            </c:forEach>
        </ul>
    </body>
</html>