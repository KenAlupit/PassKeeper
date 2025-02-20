<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PassKeeper||Home</title>
     <link rel="icon" href="${pageContext.request.contextPath}/css/logo.png">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body class="tablebody">

    <div class="homepic">
        <img src="${pageContext.request.contextPath}/css/HomePic.png" alt="">
    </div>
    <div class="welcome-message">
        Welcome, <span class="uname">${userSession.username}</span>
    </div>
    <div class="table-wrapper">
        <div class="table-scroll">
    <table class="table">
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
                    <td><a href="deletePasswordInfo/${info.passwordId}" class="delete"><i class="fas fa-trash"></i><span>Delete</span></a>
                        <a href="showFormForUpdate/${info.passwordId}" class="edit"><i class="fas fa-pencil"></i><span>Edit</span></a></td>
                   </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</div>
<div class="btns">
    <a href="showNewPasswordInfoForm" class="addbtn">Add Account</a> <a href="logout" class="logoutbtn">Log Out</a>
</div>
  </body>
  </html>
