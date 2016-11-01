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

<c:set var="current" value="${requestScope.issue}"/>


    <table id="issueDiv" class="table table-bordered table-striped">
        <c:set var="current" value="${requestScope.issue}"/>
        <tbody>
        <tr>
            <td>Issue #</td>
            <td><c:out value="${current.getIssueID()}"/></td>
        </tr>
        <tr>
            <td>Status</td>
            <td><c:out value="${current.getStatus()}"/></td>
        </tr>
        <tr>
            <td>Category</td>
            <td><c:out value="${current.getCategory()}"/></td>
        </tr>
        <tr>
            <td>Reported Date</td>
            <td><c:out value="${current.getReportedDateTime()}"/></td>
        </tr>
        <tr>
            <td>User</td>
            <td><c:out value="${current.getUser()}"/></td>
        </tr>
        <tr>
            <td>Content</td>
            <td><c:out value="${current.getContent()}"/></td>
        </tr>
        </tbody>
    </table>

<jsp:include page="includes/bootStrapCoreJS.jsp" />
</body>
</html>