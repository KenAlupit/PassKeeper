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
<h1>Username: ${userSession.username}</h1>
<a href ="logout">Logout</a>
<table border="1">
    <thead>
    <tr>
        <th>Platform</th>
        <th>Username</th>
        <th>Password</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="info" items="${passwordInfoList}">
        <tr>
            <td>${info.name}</td>
            <td>${info.username}</td>
            <td>${info.password}</td>
            <td><a href="showFormForUpdate/${info.passwordId}">Update</a>
                <a href="deletePasswordInfo/${info.passwordId}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="showNewPasswordInfoForm">Add New Password Info</a>
</body>

</html>