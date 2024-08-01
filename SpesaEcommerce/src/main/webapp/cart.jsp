<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="http://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet">
    <title>Grocery Shop</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container px-5">
        <a class="navbar-brand">Groceries</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="orders-history">Orders</a></li>
                <li class="nav-item"><a class="nav-link" href="logout">Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>
<main>
    <section class="h-100 h-custom" style="background-color: #eee;">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-body p-4">
                            <h5 class="mb-3">
                                <a href="products" class="text-body">
                                    <i class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping
                                </a>
                            </h5>
                            <hr>
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <p class="mb-1">Shopping cart</p>
                            </div>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Product Name</th>
                                        <th>Quantity</th>
                                        <th>Unit Price</th>
                                        <th>Total Price</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="product" items="${carts}" varStatus="status">
                                        <tr>
                                            <td>${status.index + 1}</td>
                                            <td>${product.name}</td>
                                            <td>
                                                <form action="modify-quantity" method="post" class="d-flex align-items-center">
                                                    <input type="hidden" name="productId" value="${product.productId}">
                                                    <input type="hidden" name="cartId" value="${product.cartId}">
                                                    <input type="hidden" name="price" value="${product.unitPrice}">
                                                    <input type="number" name="quantity" id="quantity${status.index}" min="1" value="${product.quantity}" required class="form-control">
                                                    <input type="submit" class="btn btn-sm btn-secondary ms-2" value="Update">
                                                </form>
                                            </td>
                                            <td>&#8364; ${product.unitPrice}</td>
                                            <td>&#8364; ${product.totalPrice}</td>
                                            <td>
                                                <form action="delete-item" method="get">
                                                    <input type="hidden" name="cartId" value="${product.cartId}">
                                                    <input type="submit" class="btn btn-sm btn-danger" value="Delete">
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="card bg-primary text-white rounded-3">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between align-items-center mb-4">
                                        <h5 class="mb-0">Cart details</h5>
                                    </div>
                                    <hr class="my-4">
                                    <div class="d-flex justify-content-between">
                                        <p class="mb-2">Address</p>
                                        <p class="mb-2">${user.address}</p>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <p class="mb-2">First Name:</p>
                                        <p class="mb-2">${user.firstName}</p>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <p class="mb-2">Last Name:</p>
                                        <p class="mb-2">${user.lastName}</p>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <p class="mb-2">Order Total</p>
                                        <p class="mb-2">&#8364; ${orderTotal}</p>
                                    </div>
                                    <form action="place-order" method="post">
                                        <div class="d-flex justify-content-between">
                                            <input type="hidden" name="userId" value="${user.userId}">
                                            <input type="submit" class="btn btn-info btn-block btn-lg" value="Checkout">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
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
