<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="index.css">
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Login Insegnante</h2>
<form action="loginInsegnante" method="post">
<table class="table table-hover">
<tr>
    <td>Codice personale insegnante</td>
    <td><input type="text" name="codice_insegnante"></td>
</tr>
<tr>
    <td>Password</td>
    <td><input type="password" name="password"></td>
</tr>
<tr>
    <td><input type="reset" value="Reset"></td>
    <td><input type="submit" value="LogIn"></td>
</tr>
</table>
<br>
<br>
<p>Non sei ancora registrato?</p>
<a href="registrazioneInsegnante.jsp" class="button btn-success me-3">Registrati</a>
<br>
<a href="index.jsp" class="button btn-success me-3">Home</a>
<br>
<p>${message}</p>
</form>
</body>
</html>