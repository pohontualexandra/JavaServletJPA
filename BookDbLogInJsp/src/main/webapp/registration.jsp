<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>User Registration</title>
<link rel="stylesheet" href="index.css">
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Registration</h2>
<form action="registerUser" method="post">
<table class="table table-hover">
<tr>
    <td>Username</td>
    <td><input type="text" name="username"></td>
</tr>
<tr>
    <td>Email</td>
    <td><input type="email" name="email"></td>
</tr>
<tr>
    <td>Password</td>
    <td><input type="password" name="password"></td>
</tr>

<tr>
    <td>Country</td>
    <td><input type="text" name="country"></td>
</tr>
<tr>
    <td><input type="reset" value="Reset"></td>
    <td><input type="submit" value="Register"></td>
</tr>
</table>
<br>
<p>Already registered?</p>
<a href="login.jsp" class="button">Log In</a>
<p>${message}</p>
</form>
</body>
</html>