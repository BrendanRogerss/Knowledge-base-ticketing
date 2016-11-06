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
<c:set var="user" value="${sessionScope.user}"/>

    <table id="issueDiv" class="table table-bordered table-striped">
        <c:set var="current" value="${requestScope.issue}"/>
        <tbody>
        <tr>
            <td class="col-md-2">Issue #</td>
            <td><c:out value="${current.getIssueID()}"/></td>
        </tr>
        <tr>
            <td>Title</td>
            <td><c:out value="${current.getTitle()}"/></td>
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
            <td>Error message</td>
            <td><c:out value="${current.getErrorMessage()}"/></td>
        </tr>
        <c:if test="${current.getState().compareTo(\"KnowledgeBase\") == 0}">
            <tr>
                <td>Resolved Date</td>
                <td><c:out value="${current.getResolvedDateTime()}"/></td>
            </tr>
        </c:if>
        <tr>
            <td>Problem description</td>
            <td><c:out value="${current.getDescription()}"/></td>
        </tr>
        <!--Display comments-->
        <!--TODO maybe when the issue is requested, if its knowledge base dont add comments in servlet-->
        <c:if test="${current.getState().compareTo(\"KnowledgeBase\") != 0}">
        <tr>
            <td>Comments</td>
            <td>
            <c:forEach var="currentComment" items="${current.getComments()}">
                <c:choose>
                    <c:when test="${currentComment.getCommentType().compareTo(\"Accepted\") == 0}">
                <div class="panel panel-success">
                    </c:when>
                        <c:when test="${currentComment.getCommentType().compareTo(\"Proposed\") == 0}">
                <div class="panel panel-info">
                    </c:when>
                        <c:when test="${currentComment.getCommentType().compareTo(\"Rejected\") == 0}">
                <div class="panel panel-danger">
                    </c:when>
                    <c:otherwise>
                        <div class="panel panel-default">
                    </c:otherwise>
                </c:choose>
                    <div class="panel-heading">
                        <div>
                            User: <c:out value="${currentComment.getUsername()}"/> Date: <c:out value="${currentComment.getSubmissionDateTime()}"/>
                            <c:if test="${user.getUsername().compareTo(current.getUsername()) == 0 && currentComment.getCommentType().compareTo(\"Proposed\") == 0 && current.getState().compareTo('Resolved') != 0}">
                                <span style="margin-left: 2em; font-weight: bold"><a href="ChangeCommentType?commentID=<c:out value="${currentComment.getCommentID()}"/>&issueID=<c:out value="${current.getIssueID()}"/>&commentType=Accepted">Accept</a></span>
                                <span style="margin-left: 1em; font-weight: bold"><a href="ChangeCommentType?commentID=<c:out value="${currentComment.getCommentID()}"/>&issueID=<c:out value="${current.getIssueID()}"/>&commentType=Rejected">Reject</a></span>
                            </c:if>
                        </div>
                    </div>
                    <div class="panel-body"><c:out value="${currentComment.getContent()}"/> </div>
                </div>
            </c:forEach>
            </td>
        </tr>
        <!--Display Comments-->
        </c:if>
        </tbody>
    </table>


<c:if test="${user.isStaff() && current.getState().compareTo(\"New\") == 0}">
    <!--Start work on issue-->
    <div class="container table-width">
        <form action="ChangeIssueState" method="POST">
            <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
            <input type="hidden" name="state" value="In-Progress"/>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Set to In-Progress</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--Start work on issue-->
</c:if>
<c:if test="${user.isStaff() && (current.getState().compareTo(\"In-Progress\") == 0 || current.getState().compareTo(\"Waiting on Reporter\") == 0)}">
    <!--Waiting on third party-->
    <div class="container table-width">
        <form action="ChangeIssueState" method="POST">
            <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
            <input type="hidden" name="state" value="Waiting on Third Party"/>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Wait on third party</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--Waiting on third party-->
</c:if>


<c:if test="${user.isStaff() && (current.getState().compareTo(\"Resolved\") == 0)}">
    <!--Knowledge base-->
    <div class="container table-width">
        <!--TODO set this so it goes to the right place-->
        <form action="ChangeIssueState" method="POST">
            <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
            <input type="hidden" name="state" value="KnowledgeBase"/>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Add to knowledge base</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--Knowledge base-->
</c:if>

<c:if test="${user.isStaff() && (current.getState().compareTo(\"In-Progress\") == 0 || current.getState().compareTo(\"Completed\") == 0|| current.getState().compareTo(\"Waiting on Third Party\") == 0 || current.getState().compareTo(\"Waiting on Reporter\") == 0)}">
    <!--Propose solution-->
    <div class="container table-width">
        <form action="AddComment" method="POST">
            <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
            <input type="hidden" name="state" value="Completed"/>
            <input type="hidden" name="commentType" value="Proposed"/>
            <div class="row">
                <div class="form-group">
                    <label for="commentContent" class="col-sm-2 control-label">Propose Solution</label>
                    <div class="col-sm-5">
                        <div class="input-group">
                            <textarea class="form-control custom-control" id="proposeContent" name="commentContent" rows="3" cols="50" style="resize:none" placeholder="Propose a solution..."></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return validatePropose()">Propose solution</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!--Propose solution-->
</c:if>

<c:if test="${current.getState().compareTo(\"KnowledgeBase\") != 0 && current.getState().compareTo(\"Resolved\") != 0}">
    <!-- adding comments-->
    <div class="container table-width">
        <form action="AddComment" method="POST">
            <input type="hidden" name="issueID" value="<c:out value="${current.getIssueID()}"/>"/>
            <input type="hidden" name="state" value="Waiting on Reporter"/>
            <input type="hidden" name="commentType" value="Comment"/>
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
            <div class="row">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" onclick="return validateComment()">Add comment</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <!-- adding comments-->
</c:if>

<jsp:include page="includes/bootStrapCoreJS.jsp" />
<script src="js/validate.js"></script>

</body>
</html>