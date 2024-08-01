<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="css/styles.css" rel="stylesheet">
    <title>Grocery Shop</title>
</head>
<body class="d-flex flex-column h-100">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container px-5">
        <a class="navbar-brand">Groceries</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="cart-view">Cart</a></li>
                <li class="nav-item"><a class="nav-link" href="orders-history">Orders</a></li>
                <li class="nav-item"><a class="nav-link" href="admin-view">Admin</a></li>
                <c:choose>
                    <c:when test="${username==null}">
                        <li class="nav-item"><a class="nav-link" href="logout">Log In</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="logout">Log Out</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
<main class="flex-shrink-0">
    <section class="py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <p class="lead fw-normal text-muted mb-0">${logMessage}</p>
                <h1 class="fw-bolder">Grocery Shop</h1>
                <p class="lead fw-normal text-muted mb-0">Welcome, ${username}!</p>
                <p class="lead fw-normal text-muted mb-0">Choose your favorite products</p>
            </div>
            <div class="row gx-5">
                <c:forEach var="product" items="${productsList}" varStatus="status">
                    <div class="col-lg-6 mb-2">
                        <div class="position-relative mb-3">
                            <img class="img-fluid rounded-3 mb-3" src="${product.imageUrl}" alt="${product.name}"/>
                            <p>${product.description}</p>
                            <p>Price: $${product.price}</p>
                            <form action="cart" method="post">
                                <input type="hidden" name="productId" value="${product.productId}">
                                <input type="hidden" name="UserId" value="${userId}">
                                <input type="hidden" name="price" value="${product.price}">
                                <label for="quantity${status.index}">Quantity:</label>
                                <input type="number" name="quantity" id="quantity${status.index}" min="1" max="${product.stock}" value="1" required>
                                <button type="submit" class="btn btn-secondary">Add to Cart</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</main>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
