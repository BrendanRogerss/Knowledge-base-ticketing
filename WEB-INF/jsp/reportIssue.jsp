<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="includes/defaultHead.jsp" />
</head>

<body>

<div class="container">
    <div class="starter-template">
        <h1>Login</h1>
        <p class="lead">Enter user details to login as an existing user.</p>
        <p><utils:NotLoggedIn /></p>
    </div>
</div><!-- /.container -->

<div class="container">
    <form action="HomePage" method="POST">
        <div class="row">
            <div class="form-group center-block col-md-4" style="float: none">
                <label for="user">Username</label>
                <input type="text" class="form-control" id="user" placeholder="Username">
            </div>
            <div class="form-group center-block col-md-4" style="float: none">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default center-block col-md-2" style="float: none">Submit</button>
        </div>
    </form>
</div>


<jsp:include page="includes/bootStrapCoreJS.jsp" />

</body>

</html>