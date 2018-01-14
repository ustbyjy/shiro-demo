<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h1>Login</h1>
<form action="/shiro/login" method="post">
    username：<input type="text" name="username"><br>
    password：<input type="text" name="password"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>