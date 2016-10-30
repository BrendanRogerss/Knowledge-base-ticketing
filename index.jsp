<%@ taglib prefix="utils" uri="/WEB-INF/jsp/utils.tld" %>

<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UoN IT Services</title>
</head>
    <body>
        <form name="credentialsForm" action="HomePage" method="POST" >
            <label>Username:</label>
            <input type="text" name="firstname">

            <label>Password:</label>
            <input type="text" name="lastname">
            <input type="submit">

            <utils:NotLoggedIn />
        </form>
    </body>
</html>