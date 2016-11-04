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
                <td>Title</td>
                <td>Status</td>
                <td>Category</td>
                <td>Date</td>
                <td>User</td>
                <c:choose>
                    <c:when test="${user.isStaff()}">
                        <td>Add to Knowledge Base</td>
                    </c:when>
                </c:choose>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="current" items="${requestScope.list}">
                <tr>
                    <td><a href="Issue?issueID=<c:out value="${current.getIssueID()}"/>&issueList=false"><c:out value="${current.getIssueID()}"/></a></td>
                    <td><c:out value="${current.getTitle()}"/></td>
                    <td><c:out value="${current.getState()}"/></td>
                    <td><c:out value="${current.getCategory()}"/></td>
                    <td><c:out value="${current.getReportedDateTime()}"/></td>
                    <td><c:out value="${current.getUsername()}"/></td>
                    <c:choose>
                        <c:when test="${user.isStaff()}">
                            <c:choose>
                                <c:when test="${!current.getState().equals('KnowledgeBase')}">
                                    <c:choose>
                                        <c:when test="${current.getState().equals('Completed')}">
                                            <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=KnowledgeBase&path=issueList"><img src="resources/plus.jpg" width="22" height="22" /></a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><img src="resources/yellow-exclamation-mark.jpg" width="22" height="22" /></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=Completed&path=issueList"><img src="resources/minus.jpg" width="22" height="22" /></a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    <jsp:include page="includes/bootStrapCoreJS.jsp" />
    </body>
</html>