<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <title>Book List</title>
    <link rel="stylesheet" href="index.css">
    <p>Current User: <c:out value="${sessionScope.username}"/></p>
</head>
<body>
<div class="container">
    <h1>Book List</h1>
    <table class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${bookList}" var="book">

                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.price}</td>
                    <td><a href='editBook?id=${book.id}'>Edit</a></td>
                    <td><a href='deleteUrl?id=${book.id}'>Delete</a></td>
                </tr>

            </c:forEach>

            </tbody>
    </table>
    <br>
    <p>${message}</p>
    <br
    <
    <a href="index.jsp">Home</a>
    <br>
    <a href="logOut">Log Out</a>
    <br>

</div>

</body>
</html>
