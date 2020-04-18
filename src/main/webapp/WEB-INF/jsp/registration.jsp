<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 17.03.2020
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>registration</title>
</head>
<body>
<h2>registration</h2>
<form method="post" action="/fs/registration">
    <p>Login:</p><input type="text" id="login" name="login" placeholder="Enter login">
    <p>Password:</p><input type="password" id="password" name="password" placeholder="Enter password">
    <p>Retype Password:</p><input type="password" id="password2" name="password2" placeholder="Enter password">
    <p>First name:</p><input type="text" id="firstName" name="firstName" placeholder="Enter first name">
    <p>Second name:</p><input type="text" id="secondName" name="secondName" placeholder="Enter second name"><br>
    <p><button type="submit">Submit</button><button type="reset">Reset</button></p>
</form>
</body>
</html>
