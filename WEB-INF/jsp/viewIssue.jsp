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


    <table id="issueDiv" class="table table-bordered table-striped">
        <c:set var="current" value="${requestScope.issue}"/>
        <tbody>
        <tr>
            <td>Issue #</td>
            <td><c:out value="${current.getIssueID()}"/></td>
        </tr>
        <tr>
            <td>Status</td>
            <td><c:out value="${current.getState()}"/></td>
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
            <td><c:out value="${current.getUsername()}"/></td>
        </tr>
        <tr>
            <td>Content</td>
            <td><c:out value="${current.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td>
            <c:forEach var="currentComment" items="${current.getComments()}">
                <div class="panel panel-default">
                    <div class="panel-heading">User: <c:out value="${currentComment.getUsername()}"/> Date: <c:out value="${currentComment.getSubmissionDateTime()}"/></div>
                    <div class="panel-body"><c:out value="${currentComment.getContent()}"/> </div>
                </div>
            </c:forEach>
            </td>
        </tr>
        </tbody>
    </table>
<div class="container">
    <form action="AddComment" method="POST">
        <div class="row">
            <div class="form-group">
                <label for="commentContent" class="col-sm-2 control-label">Add comment</label>
                <div class="col-sm-5">
                    <div class="input-group">
                        <textarea class="form-control custom-control" name="commentContent" rows="3" cols="50" style="resize:none" id="commentContent" placeholder="Add a comment..."></textarea>
                    </div>
                </div>
            </div>
        </div>
        <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
        <div class="row">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Add comment</button>
                </div>
            </div>
        </div>
    </form>
</div>


<jsp:include page="includes/bootStrapCoreJS.jsp" />
</body>
</html>