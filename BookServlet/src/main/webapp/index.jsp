
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Book Store</title>
<link rel="stylesheet" href="index.css">
</head>
<body class="container-fluid card" style="width:40rem;">
<h2 class="title">Book Store</h2>
<form action="register" method="post">
    <table class ="table table-hover">
        <tr>
            <td>Book title</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>Book author</td>
            <td><input type="text" name="author"></td>
        </tr>
        <tr>
            <td>Book price</td>
            <td><input type="text" name="price"></td>
        </tr>
        <tr>
            <td><input type ="reset" value="cancel"></td>
            <td><input type="submit" value="register"></td>
        </tr>
    </table>
    <a href="bookList">Book List Preview</a>
    <br>
    <p>${message}</p>
</form>
</body>
</html>
