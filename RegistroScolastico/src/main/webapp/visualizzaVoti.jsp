<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <title>Book List</title>
    <p>Matricola studente: <c:out value="${sessionScope.matricola}"/></p>
    <link rel="stylesheet" href="index.css">
</head>
<body>
<div class="container">
    <h1>Lista Voti</h1>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Materia</th>
                <th>Data Esame</th>
                <th>Voto</th>
                <th>Matricola Studente</th>
                <th>Codice Insegnante</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${voti}" var="voto">
                <tr>
                    <td>${voto.materia}</td>
                    <td>${voto.dataEsame}</td>
                    <td>${voto.voto}</td>
                    <td>${voto.matricolaStudente}</td>
                    <td>${voto.codiceInsegnante}</td>
                </tr>

            </c:forEach>

            </tbody>
    </table>
    <br>
    <p>${message}</p>
    <br>
    <a href="index.jsp">Home</a>
    <br>
    <a href="logOutStudente">Log Out</a>
    <br>
</div>
</body>
</html>
