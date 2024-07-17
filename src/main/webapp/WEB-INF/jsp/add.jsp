<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home</title>
    <script>
        //dynamically change password field without refreshing the whole page
        function generatePassword() {
            fetch('/generate-password')
                .then(response => response.text())
                .then(password => {
                    document.getElementById('password').value = password;
                });
        }
    </script>
</head>
<body>
<form action="savePasswordInfo" method="post">
    <label>Name:</label>
    <input type="text" name="name"/>
    <label>Username:</label>
    <input type="text" name="username"/>
    <label>Password:</label>
    <input type="text" id="password" name="password"/>
    <button type="button" onclick="generatePassword()">Generate Password</button>
    <input type="hidden" name="passwordOwner" value="${session_username}" />
    <button type="submit">Save</button>
</form>
</body>
</html>
