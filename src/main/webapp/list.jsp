<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>List</title>
</head>
<body>
<h1>List</h1>

Welcome：<shiro:principal />
<br><br>

<shiro:hasRole name="admin">
    <a href="admin.jsp">Admin Page</a><br><br>
</shiro:hasRole>

<shiro:hasRole name="user">
    <a href="user.jsp">User Page</a><br><br>
</shiro:hasRole>

<a href="shiro/testShiroAnnotation">TestShiroAnnotation</a><br><br>

<a href="shiro/logout">Logout</a><br><br>
</body>
</html>