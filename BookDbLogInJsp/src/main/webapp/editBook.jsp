
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Edit Book Record</title>
<link rel="stylesheet" href="index.css">
<p>Current User: <c:out value="${sessionScope.username}"/></p>
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Edit Book Record</h2>
<form action="saveEdit" method="get">
    <table class ="table table-hover">
        <tr>
            <td>Book title</td>
            <td><input type="text" name="title" value="${title1}"></td>
        </tr>
        <tr>
            <td>Book author</td>
            <td>Book author</td>
            <td><input type="text" name="author" value="${author1}"></td>
        </tr>
        <tr>
            <td>Book price</td>
            <td><input type="text" name="price" value="${price1}"></td>
        </tr>
        <tr>
            <td><input type="reset" value="cancel"></td>
            <td><input type="submit" value="save"></td>
        </tr>
    </table>
    <a href="bookList">Book List</a>
    <br>
    <a href="index.jsp">Home</a>
    <br>
    <a href="logOut">Log Out</a>
    <br>
</form>
</body>
</html>
