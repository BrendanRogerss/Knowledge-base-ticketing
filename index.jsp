<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="WEB-INF/jsp/includes/defaultHead.jsp" />
</head>

<body>

    <!--index.jsp navigation header-->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">UoN IT Services</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="About">About</a></li>
                    <li><a href="Contact">Contact</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
    <!-- Display any errors with logging in-->
    <c:if test="${sessionScope.error != null}">
        <div class="alert alert-danger">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error!</strong> <c:out value="${sessionScope.error}"/>
        </div>
    </c:if>
    <!-- index.jsp navigation header end -->

    <div class="container">
        <div class="starter-template">
            <h1>Login</h1>
            <p class="lead">Enter user details to login as an existing user.</p>
        </div>
    </div><!-- /.container -->

    <!--Logon form-->
    <div class="container">
        <form name="loginForm" action="Authentication" method="POST">
            <div class="row">
                <div class="form-group center-block col-md-4" style="float: none">
                    <label for="user">Username</label>
                    <input type="text" class="form-control" id="user" placeholder="Username" name="username">
                </div>
            </div>
            <div class="row">
                <div class="form-group center-block col-md-4" style="float: none">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                </div>
                <button type="submit" class="btn btn-default center-block col-md-2" style="float: none" onclick="return validateLogin()">Login</button>
            </div>
        </form>
    </div>

    <jsp:include page="WEB-INF/jsp/includes/bootStrapCoreJS.jsp" />
    <script src="js/validate.js"></script>
</body>
</html>