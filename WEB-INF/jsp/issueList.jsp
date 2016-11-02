<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="Models.User" scope="session"/>
<html>
<head>
    <jsp:include page="includes/defaultHead.jsp" />
</head>
    <body>
    <jsp:include page="includes/header.jsp" />
        <table id="issueDiv" class="table table-bordered table-hover">
            <thead>
            <tr>
                <td>Issue ID</td>
                <td>Status</td>
                <td>Category</td>
                <td>Date</td>
                <td>User</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="current" items="${requestScope.list}">
                <tr>
                    <td><a href="Issue?issueID=<c:out value="${current.getIssueID()}"/>"><c:out value="${current.getIssueID()}"/></a></td>
                    <td><c:out value="${current.getState()}"/></td>
                    <td><c:out value="${current.getCategory()}"/></td>
                    <td><c:out value="${current.getReportedDateTime()}"/></td>
                    <td><c:out value="${current.getUsername()}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <jsp:include page="includes/bootStrapCoreJS.jsp" />
    </body>
</html>