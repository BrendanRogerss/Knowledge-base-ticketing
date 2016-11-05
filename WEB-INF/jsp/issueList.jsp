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
                <td>
                Issue ID
                <c:choose>
                <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                <form action="ReportedIssues" method="POST">
                    </c:when>
                    <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                    <form action="KnowledgeBase" method="POST">
                        </c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                        <form action="CompletedIssues" method="POST">
                            </c:when>
                            </c:choose>
                            <input type="hidden" name="sortString" value="issueID"/>
                            <button type="submit" class="btn btn-default">Sort IssueID</button>
                        </form>
                </td>
                <td>
                    Title
                        <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                        <form action="ReportedIssues" method="POST">
                            </c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                            <form action="KnowledgeBase" method="POST">
                                </c:when>
                                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                                <form action="CompletedIssues" method="POST">
                                    </c:when>
                                    </c:choose>
                                    <input type="hidden" name="sortString" value="title"/>
                                                <button type="submit" class="btn btn-default">Sort Title</button>
                                </form>
                </td>
                <td>
                    Status
                        <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                        <form action="ReportedIssues" method="POST">
                            </c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                            <form action="KnowledgeBase" method="POST">
                                </c:when>
                                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                                <form action="CompletedIssues" method="POST">
                                    </c:when>
                                    </c:choose>
                                    <input type="hidden" name="sortString" value="state"/>
                                                <button type="submit" class="btn btn-default">Sort Status</button>
                                </form>
                </td>
                <td>
                    Category
                        <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                        <form action="ReportedIssues" method="POST">
                            </c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                            <form action="KnowledgeBase" method="POST">
                                </c:when>
                                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                                <form action="CompletedIssues" method="POST">
                                    </c:when>
                                    </c:choose>
                                    <input type="hidden" name="sortString" value="category"/>
                                                <button type="submit" class="btn btn-default">Sort Category</button>
                                </form>
                </td>
                <td>
                    Reported date
                        <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                        <form action="ReportedIssues" method="POST">
                            </c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                            <form action="KnowledgeBase" method="POST">
                                </c:when>
                                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                                <form action="CompletedIssues" method="POST">
                                    </c:when>
                                    </c:choose>
                                    <input type="hidden" name="sortString" value="reportDateTime"/>
                                                <button type="submit" class="btn btn-default">Sort Reported date</button>
                                </form>
                </td>
                <td>
                    User
                        <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                        <form action="ReportedIssues" method="POST">
                            </c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                            <form action="KnowledgeBase" method="POST">
                                </c:when>
                                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssue\")==0}">
                                <form action="CompletedIssues" method="POST">
                                    </c:when>
                                    </c:choose>
                                    <input type="hidden" name="sortString" value="user"/>
                                                <button type="submit" class="btn btn-default">Sort User</button>
                                </form>
                </td>
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