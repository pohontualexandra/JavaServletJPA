<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="ISO-8859-1">
<title>Gestione Voti</title>
<link rel="stylesheet" href="index.css">
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Inserimento Voti</h2>
<p>Codice personale user: <c:out value="${sessionScope.codice_insegnante}"/></p>
<form action="gestioneVoti" method="post">
<table class="table table-hover">
<tr>
    <td>Materia</td>
    <td><input type="text" name="materia"></td>
</tr>
<tr>
    <td>Data esame</td>
    <td><input type="date" name="dataEsame"></td>
</tr>
<tr>
    <td>Voto</td>
    <td><input type="text" name="voto"></td>
</tr>
<tr>
    <td>Matricola Studente</td>
    <td><input type="text" name="matricola"></td>
</tr>
<tr>
    <td>Codice Insegnante</td>
    <td><input type="hidden" name="codiceInsegnante" value="${sessionScope.codice_insegnante}"></td>
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
<p>${success}</p>
<a href="index.jsp">Home</a>
<br>
<a href="logOutInsegnante">Log Out</a>
<br>
</body>
</html>