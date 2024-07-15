<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
</head>
<body>
<div style="display: inline-block; text-align: right;">
    <!-- Login form -->
    <form action="register" method="post"> <!-- Form sends data to proc_login.jsp using HTTP POST method -->
        Username: <input type = "text" name = "username" style = "margin-bottom: 1%" required/> <!-- Username input field -->
        <br />
        Password: <input type = "text" name = "password" style = "margin-bottom: 1%" required/> <!-- Password input field -->
        <br />
        Confirm Password: <input type = "text" name = "confirmpassword" style = "margin-bottom: 1%" required/> <!-- Password input field -->
        <br />
        <div style="text-align: center;">
            <input type = "submit" value = "Create Account" /> <!-- Submit button to submit the form -->
            <input type="reset" value = "Clear"/> <!-- Reset button to clear the form inputs -->

            <c:if test="${not empty param.error}">
                <!-- Display error message in red color -->
                <p style="color: red;"><c:out value="${param.error}"/></p>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>