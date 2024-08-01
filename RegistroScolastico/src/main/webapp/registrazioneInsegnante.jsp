<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>Registrazione Insegnante</title>
<link rel="stylesheet" href="index.css">
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Registrazione Insegnante</h2>
<form action="registrazioneInsegnante" method="post">
<table class="table table-hover">
<tr>
    <td>Codice insegnante</td>
    <td><input type="text" name="codiceInsegnante"></td>
</tr>
<tr>
    <td>Nome</td>
    <td><input type="text" name="nome"></td>
</tr>
<tr>
    <td>Cognome</td>
    <td><input type="text" name="cognome"></td>
</tr>
<tr>
    <td>Codice fiscale</td>
    <td><input type="text" name="codiceFiscale"></td>
</tr>
<tr>
    <td>Email</td>
    <td><input type="text" name="email"></td>
</tr>
<tr>
    <td>Password</td>
    <td><input type="password" name="password"></td>
</tr>
<tr>
    <td><input type="reset" value="Reset"></td>
    <td><input type="submit" value="Salva"></td>
</tr>
</table>
<br>
<p>${message}</p>
</form>
<br>
<p>${error}</p>
<a href="index.jsp">Home</a>
<br>
</body>
</html>