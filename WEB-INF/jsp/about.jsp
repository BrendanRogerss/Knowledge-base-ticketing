<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="includes/defaultHead.jsp" />
</head>

<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">UoN IT Services</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp">Home</a></li>
                    <li class="active"><a href="About">About</a></li>
                    <li><a href="Contact">Contact</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>

    <div class="container">
        <div class="starter-template">
            <h1>About UoN IT Services</h1>
            <p class="lead" style="margin-top: 2em">
                This website is built using the MVC architecture.
                Java Beans are the Models of the website used to store the session data.
                JSPs are the Views of the website used to display the data.
                Java Servlets are the Controllers of the website, used to manage the data between the Models and the Views.</p>
        </div>
    </div><!-- /.container -->

    <jsp:include page="includes/bootStrapCoreJS.jsp" />
</body>
</html>