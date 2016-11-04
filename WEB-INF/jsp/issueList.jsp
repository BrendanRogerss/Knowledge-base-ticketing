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
                <td>Add to Knowledge Base</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="current" items="${requestScope.list}">
                <tr>
                    <td><a href="Issue?issueID=<c:out value="${current.getIssueID()}"/>"><c:out value="${current.getIssueID()}"/></a></td>
                    <td><c:out value="${current.getTitle()}"/></td>
                    <td><c:out value="${current.getState()}"/></td>
                    <td><c:out value="${current.getCategory()}"/></td>
                    <td><c:out value="${current.getReportedDateTime()}"/></td>
                    <td><c:out value="${current.getUsername()}"/></td>
                    <c:choose>
                        <c:when test="${!current.getState().equals('KnowledgeBase')}">
                            <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=KnowledgeBase&issueList=true"><img src="resources/plus.jpg" width="22" height="22" /></a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=Completed&issueList=true"><img src="resources/minus.jpg" width="22" height="22" /></a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <jsp:include page="includes/bootStrapCoreJS.jsp" />
    </body>
</html>