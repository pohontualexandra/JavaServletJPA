<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <a class="navbar-brand">Dashboard</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products">Home</a></li>
            </ul>
        </div>
    </div>
</nav>
<body class="d-flex flex-column h-100">
<main class="flex-shrink-0">
    <section class="py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder">ADMIN</h1>
            </div>
                <div class="d-flex justify-content-center align-items-center">

                      <div class="card-body">
                        <h5 class="card-title text-center mb-4">Modify Products</h5>
                          <table class="table table-bordered">
                              <thead>
                                  <tr>
                                      <th>ID</th>
                                      <th>Product Name</th>
                                      <th>Description</th>
                                      <th>Image URL</th>
                                      <th>Price</th>
                                      <th>Stock</th>
                                      <th>Actions</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <c:forEach var="product" items="${productsList}" varStatus="status">
                                      <tr>
                                          <form action="modify-product" method="post">
                                              <td><input type="hidden" name="productId" value="${product.productId}">${product.productId}</td>
                                              <td><input type="text" name="name" value="${product.name}"></td>
                                              <td><input type="text" name="description" value="${product.description}"></td>
                                              <td><input type="text" name="imageUrl" value="${product.imageUrl}"></td>
                                              <td><input type="number" name="price" value="${product.price}" step="0.01" min="0"></td>
                                              <td><input type="number" name="stock" value="${product.stock}" min="0"></td>
                                              <td>
                                                  <input type="submit" class="btn btn-sm btn-primary" value="Update">
                                              </td>
                                          </form>
                                          <td>
                                              <form action="delete-product" method="get">
                                                  <input type="hidden" name="productId" value="${product.productId}">
                                                  <input type="submit" class="btn btn-sm btn-danger" value="Delete">
                                              </form>
                                          </td>
                                      </tr>
                                  </c:forEach>
                              </tbody>
                          </table>
                        </div>
                </div>
                ${message}
        </div>
        <div class="container px-5 my-5">
                <div class="d-flex justify-content-center align-items-center">
                        <div class="card-body">
                            <h5 class="card-title text-center mb-4">Insert Products</h5>
                              <form action="add-product" method="post">
                                <div class="form-group">
                                  <label for="name">Product Name</label>
                                  <input type="text" class="form-control" name="name" placeholder="Name" required>
                                </div>
                                <div class="form-group">
                                    <label for="description">Description</label>
                                    <input type="text" class="form-control" name="description" placeholder="Description" required>
                                  </div>
                                <div class="form-group">
                                  <label for="imageUrl">Image URL</label>
                                  <input type="text" class="form-control" name="imageUrl" placeholder="Image Url" required>
                                </div>
                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" class="form-control" name="price" placeholder="Price" required step="0.01">
                                  </div>
                                  <div class="form-group">
                                  <label for="stock">Stock</label>
                                  <input type="text" class="form-control" name="stock" placeholder="Stock" required>
                                </div>
                              <div class="text-center mt-4">
                                <button type="submit" class="btn btn-primary w-100">Save Product</button>
                              </div>
                              </form>
                            </div>
                </div>
                ${message}
            </div>

    </section>
</main>
</body>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</html>