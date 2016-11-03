<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="includes/defaultHead.jsp" />

    <link href="css/homepage.css" rel="stylesheet">
</head>
    <body>
        <jsp:include page="includes/header.jsp" />


        <div class="container">
            <div class="starter-template">
                <h1>Dashboard</h1>
                <p class="lead">Select an action.</p>
            </div>
        </div><!-- /.container -->

        <div class="col-sm-9 col-sm-offset-3 col-md-8 col-md-offset-4 main">
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="ReportIssue"><img src="resources/report-issue.jpeg" width="200" height="200" alt="Report Issue"></a>
                    <h4>Report Issue</h4>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="KnowledgeBase"><img src="resources/knowledge-base.png" width="200" height="200" alt="Knowledge Base"></a>
                    <h4>Knowledge Base</h4>
                </div>
            </div>
            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <c:choose>
                        <c:when test="${user.isStaff()}">
                            <a href="ReportedIssues"><img src="resources/view-issues.jpeg" width="200" height="200" alt="View Reported Issues"></a>
                            <h4>View Reported Issues</h4>
                        </c:when>
                        <c:otherwise>
                            <a href="ReportedIssues"><img src="resources/view-issues.jpeg" width="200" height="200" alt="View Current Issues"></a>
                            <h4>View Current Issues</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <a href="index.jsp"><img src="resources/logout.png" width="200" height="200" alt="Generic placeholder thumbnail"></a>
                    <h4>Logout</h4>
                </div>
            </div>
        </div>

        <jsp:include page="includes/bootStrapCoreJS.jsp" />
    </body>
</html>
