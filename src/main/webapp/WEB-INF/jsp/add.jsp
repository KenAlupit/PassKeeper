<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
</head>
<body>
<form action="savePasswordInfo" method="post">
    <label>Name:</label>
    <input type="text" name="name"/>
    <label>Username:</label>
    <input type="text" name="username"/>
    <label>Password:</label>
    <input type="text" name="password" />
    <input type="hidden" name="passwordOwner" value="${session_username}" />
    <input type="hidden" name="saltBase64" value="Von" />
    <button type="submit">Save</button>
</form>
</body>
</html>
