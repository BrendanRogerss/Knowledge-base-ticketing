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

    <!--Setting the title and lead text-->
    <div class="container">
        <div class="starter-template">
            <c:choose>
                <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}">
                    <c:choose>
                        <c:when test="${user.isStaff()}">
                            <!--If user is staff, page is reportedIssues-->
                            <h1>View Reported Issues</h1>
                            <p class="lead">View issues reported by you, and all the unresolved issues in the system.</p>
                        </c:when>
                        <c:otherwise>
                            <!--If user is not staff, page is currentIssues-->
                            <h1>View Current Issues</h1>
                            <p class="lead">View all issues reported by you in the system.</p>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                    <!--If page is knowledgeBase-->
                    <h1>View Knowledge Base</h1>
                    <p class="lead">View all the issues in the knowledge base.</p>
                </c:when>
                <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}">
                    <!--If page is completedIssues-->
                    <h1>View Completed Issues</h1>
                    <p class="lead">View all completed and resolved issues in the system.</p>
                </c:when>
            </c:choose>
        </div>
    </div><!-- /.container -->

    <!--Creating the issue tables-->
    <table id="issueDiv" class="table table-bordered table-hover">
        <thead>
            <!--Setting the sorting buttons according to current page-->
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                    </c:choose>
                    <input type="hidden" name="sortString" value="issueID"/>
                    <button type="submit" class="btn btn-default">IssueID</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                    </c:choose>
                    <input type="hidden" name="sortString" value="title"/>
                    <button type="submit" class="btn btn-default">Title</button>
                    </form>
                </td>
                <c:if test="${!sessionScope.currentPage.equals('knowledgeBase')}">
                    <td>
                        <c:choose>
                            <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                            <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                        </c:choose>
                        <input type="hidden" name="sortString" value="state"/>
                        <button type="submit" class="btn btn-default">Status</button>
                        </form>
                    </td>
                </c:if>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                    </c:choose>
                    <input type="hidden" name="sortString" value="category"/>
                    <button type="submit" class="btn btn-default">Category</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                    </c:choose>
                    <input type="hidden" name="sortString" value="reportDateTime"/>
                    <button type="submit" class="btn btn-default">Reported date</button>
                    </form>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${sessionScope.currentPage.compareTo(\"reportedIssues\")==0}"><form action="ReportedIssues" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}"><form action="KnowledgeBase" method="POST"></c:when>
                        <c:when test="${sessionScope.currentPage.compareTo(\"completedIssues\")==0}"><form action="CompletedIssues" method="POST"> </c:when>
                    </c:choose>
                    <input type="hidden" name="sortString" value="username"/>
                    <button type="submit" class="btn btn-default">User</button>
                    </form>
                </td>
                <c:if test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                    <td>
                        <form action="KnowledgeBase" method="POST">
                            <input type="hidden" name="sortString" value="resolvedDateTime"/>
                            <button type="submit" class="btn btn-default">Resolution date</button>
                        </form>
                    </td>
                </c:if>
                <c:if test="${user.isStaff()}">
                    <td>Add to Knowledge Base</td>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <!--Populating each cell with the issue data-->
            <c:forEach var="current" items="${requestScope.list}">
            <tr>
                <!--Checking for issues that are created by the current user and have a notification-->
                <c:choose>
                    <c:when test="${current.getUsername().equals(user.username) && current.hasNotification()}">
                        <!--Add notification badge to issue next to issue number-->
                        <td><span class="label label-info">New</span><a style="font-weight: bold" href="Issue?issueID=<c:out value="${current.getIssueID()}"/>"><c:out value="${current.getIssueID()}"/></a></td>
                    </c:when>
                    <c:otherwise>
                        <!--Add issue number without notification badge-->
                        <td><a style="font-weight: bold" href="Issue?issueID=<c:out value="${current.getIssueID()}"/>"><c:out value="${current.getIssueID()}"/></a></td>
                    </c:otherwise>
                </c:choose>
                <td><c:out value="${current.getTitle()}"/></td>
                <c:if test="${!sessionScope.currentPage.equals('knowledgeBase')}">
                    <!--Show the state column if the current page is not knowledgeBase-->
                    <td><c:out value="${current.getState()}"/></td>
                </c:if>
                <td><c:out value="${current.getCategory()}"/></td>
                <td><c:out value="${current.getReportedDateTime()}"/></td>
                <td><c:out value="${current.getUsername()}"/></td>
                <c:if test="${sessionScope.currentPage.compareTo(\"knowledgeBase\")==0}">
                    <!--Add resolved date and time column if the current page is knowledgeBase-->
                    <td><c:out value="${current.getResolvedDateTime()}"/></td>
                </c:if>
                <!--Setting add/remove from knowledge base buttons-->
                <c:choose>
                    <c:when test="${user.isStaff()}">
                        <c:choose>
                            <c:when test="${!current.getState().equals('KnowledgeBase')}">
                                <c:choose>
                                    <c:when test="${current.getState().equals('Resolved')}">
                                        <c:choose>
                                            <c:when test="${sessionScope.currentPage.equals('knowledgeBase')}">
                                                <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=KnowledgeBase&path=knowledgeBase"><img src="resources/plus.jpg" width="22" height="22" /></a></td>
                                            </c:when>
                                            <c:when test="${sessionScope.currentPage.equals('reportedIssues')}">
                                                <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=KnowledgeBase&path=reportedIssues"><img src="resources/plus.jpg" width="22" height="22" /></a></td>
                                            </c:when>
                                            <c:when test="${sessionScope.currentPage.equals('completedIssues')}">
                                                <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=KnowledgeBase&path=completedIssues"><img src="resources/plus.jpg" width="22" height="22" /></a></td>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <td><img src="resources/yellow-exclamation-mark.jpg" width="22" height="22" /></td>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${sessionScope.currentPage.equals('knowledgeBase')}">
                                        <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=Resolved&path=knowledgeBase"><img src="resources/minus.jpg" width="22" height="22" /></a></td>
                                    </c:when>
                                    <c:when test="${sessionScope.currentPage.equals('reportedIssues')}">
                                        <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=Resolved&path=reportedIssues"><img src="resources/minus.jpg" width="22" height="22" /></a></td>
                                    </c:when>
                                    <c:when test="${sessionScope.currentPage.equals('completedIssues')}">
                                        <td><a href="ChangeIssueState?issueID=<c:out value="${current.getIssueID()}"/>&state=Resolved&path=completedIssues"><img src="resources/minus.jpg" width="22" height="22" /></a></td>
                                    </c:when>
                                </c:choose>
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