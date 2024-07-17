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
<h2>Update Password</h2>
<form action="../showFormForUpdate/${passwordInfo.passwordId}" method="post">
    <input type="hidden" name="id" value="${passwordInfo.passwordId}" />
    <label>Name:</label>
    <input type="text" name="name" value="${passwordInfo.name}" />
    <label>Username:</label>
    <input type="text" name="username" value="${passwordInfo.username}" />
    <label>Password:</label>
    <input type="text" name="password" value="${passwordInfo.password}" />
    <input type="hidden" name="passwordOwner" value="${passwordInfo.passwordOwner}" />
    <button type="submit">Save</button>
</form>
</body>
</html>
