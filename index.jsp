<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UoN IT Services</title>
</head>
    <body>
    <jsp:include page="WEB-INF/jsp/includes/header.jsp"/>
    <form name="credentialsForm" action="index.jsp" method="POST" onSubmit="return loginValidate()">
            <Label>Username:</Label>
            <input type="text" name="firstname">

            <Label>Password:</Label>
            <input type="text" name="lastname">
            <input type="submit">

            <utils:NotLoggedIn />
        </form>
    </body>
</html>