<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UoN IT Services</title>
</head>
    <body>
        <form name="credentialsForm" action="index.jsp" method="POST" onSubmit="return loginValidate()">
          <Label>Username:</Label>
          <input type="text" name="firstname">
          <Label>Password:</Label>
          <input type="text" name="lastname">
          <input type="submit">
        </form>
    </body>
</html>
