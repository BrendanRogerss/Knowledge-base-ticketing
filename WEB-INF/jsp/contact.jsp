<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <jsp:include page="includes/defaultHead.jsp" />
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
                <a class="navbar-brand" href="index.jsp">UoN IT Services</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="About">About</a></li>
                    <li class="active"><a href="Contact">Contact</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>
    <!-- index.jsp navigation header end -->


    <div class="container">
        <div class="starter-template">
            <h1>Contact us</h1>
            <p class="lead" style="margin-top: 2em">Brendan Rogers - c3208972</p>
            <p class="lead" style="margin-top: 2em">Daniel McKinnell - c3234953</p>
            <p class="lead" style="margin-top: 2em">Jack Newley - c3206280</p>
            <p class="lead" style="margin-top: 2em">James Arthur - c3203500</p>
        </div>
    </div><!-- /.container -->

    <jsp:include page="includes/bootStrapCoreJS.jsp" />
</body>
</html>