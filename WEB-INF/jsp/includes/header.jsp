<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="Models.User" scope="session"/>

<!--Main header bar-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="HomePage">UoN IT Services</a>
        </div>
        <div id="navbar" class ="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <!--Setting active header tabs-->

                <!--If active tab is homepage-->
                <c:choose>
                    <c:when test="${sessionScope.currentPage.equals('homepage')}">
                        <li class="active"><a href="HomePage">Home</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="HomePage">Home</a></li>
                    </c:otherwise>
                </c:choose>

                <!--If active tab is reportIssue-->
                <c:choose>
                    <c:when test="${sessionScope.currentPage.equals('reportIssue')}">
                        <li class="active"><a href="ReportIssue">Report Issue</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="ReportIssue">Report Issue</a></li>
                    </c:otherwise>
                </c:choose>

                <!--If active tab is knowledgeBase-->
                <c:choose>
                    <c:when test="${sessionScope.currentPage.equals('knowledgeBase')}">
                        <li class="active"><a href="KnowledgeBase">Knowledge Base</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="KnowledgeBase">Knowledge Base</a></li>
                    </c:otherwise>
                </c:choose>

                <!--If active tab is reportedIssues or completedIssues-->
                <c:choose>
                    <!--If user is staff-->
                    <c:when test="${user.isStaff()}">
                        <c:choose>
                            <!--If active tab is reportedIssues-->
                            <c:when test="${sessionScope.currentPage.equals('reportedIssues')}">
                                <li class="active"><a href="ReportedIssues">View Reported Issues</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="ReportedIssues">View Reported Issues</a></li>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <!--If active tab is completedIssues-->
                            <c:when test="${sessionScope.currentPage.equals('completedIssues')}">
                                <li class="active"><a href="CompletedIssues">View Completed Issues</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="CompletedIssues">View Completed Issues</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <!--If user is not staff-->
                    <c:otherwise>
                        <c:choose>
                            <!--If active tab is currentIssues-->
                            <c:when test="${sessionScope.currentPage.equals('reportedIssues')}">
                                <li class="active"><a href="ReportedIssues">View Current Issues</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="ReportedIssues">View Current Issues</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>

                <!--Adds notification bell-->
                <li><a href="ReportedIssues"><img alt="notifications" src="resources/bell.png" width="20" height="20"/> <span class="badge"><c:out value="${sessionScope.notificationCount}"/></span></a></li>
                <li><a href="Logout">Logout</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<!--Success message-->
<c:if test="${sessionScope.success != null}">
    <div class="alert alert-success">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Success!</strong> <c:out value="${sessionScope.success}"/>
    </div>
</c:if>

<!--Error message-->
<c:if test="${sessionScope.error != null}">
    <div class="alert alert-danger">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        <strong>Error!</strong> <c:out value="${sessionScope.error}"/>
    </div>
</c:if>
