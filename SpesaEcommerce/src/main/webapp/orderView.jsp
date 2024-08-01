<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="http://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <link href="css/styles.css" rel="stylesheet">
    <title>Grocery Shop</title>
</head>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container px-5">
        <a class="navbar-brand">Groceries</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="orders-history">Order History</a></li>
                <li class="nav-item"><a class="nav-link" href="products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="logout">Log Out</a></li>
            </ul>
        </div>
    </div>
</nav>
<body>
<main>
    <section class="h-100 h-custom" style="background-color: #eee;">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="card border-top border-bottom border-3" style="border-color: #f37a27 !important;">
            <div class="card-body p-5">
                <h5 class="mb-3"><a href="products" class="text-body">
                   <i class="fas fa-long-arrow-alt-left me-2">
                   </i>Continue shopping</a>
                </h5>
                <br>
                <br>

              <p class="lead fw-bold mb-5" style="color: #f37a27;">Order Completed</p>

              <div class="row">
                <div class="col mb-3">
                  <p class="small text-muted mb-1">Order Date</p>
                  <p>${currentOrder.orderDate}</p>
                </div>
              </div>
              <c:forEach var="product" items="${orderItems}" varStatus="status">
              <div class="mx-n5 px-5 py-4" style="background-color: #f2f2f2;">
                <div class="row">
                  <div class="col-md-8 col-lg-9">
                    <p>${product.productName}</p>
                  </div>
                  <div class="col-md-8 col-lg-9">
                    <p>Quantity: ${product.quantity}</p>
                  </div>
                  <div class="col-md-4 col-lg-3">
                   <p>Total: &#8364 <fmt:formatNumber value="${product.unitPrice * product.quantity}" type="number" minFractionDigits="2" maxFractionDigits="2"/></p>
                  </div>
                  <div class="col-md-4 col-lg-3">
                    <p>Unit price: &#8364 ${product.unitPrice}</p>
                  </div>

                </div>
              </div>
              </c:forEach>

              <div class="row my-4">
                <div class="col-md-4 offset-md-8 col-lg-3 offset-lg-9">
                  <p class="lead fw-bold mb-0" style="color: #f37a27;">Order total: ${currentOrder.totalPrice}</p>
                </div>
              </div>
            </div>
        </div>
      </div>
    </section>
</main>

</body>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</html>