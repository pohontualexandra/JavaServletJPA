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
        <a class="navbar-brand">Sign Up</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a></li>
                <li class="nav-item"><a class="nav-link" href="/login">Log In</a></li>
            </ul>
        </div>
    </div>
</nav>
<body class="d-flex flex-column h-100">
<main class="flex-shrink-0">
    <section class="py-5">
        <div class="container px-5 my-5">
            <div class="text-center mb-5">
                <h1 class="fw-bolder">Grocery Shop</h1>
            </div>
                <div class="d-flex justify-content-center align-items-center">
                    <div class="card" style="width: 20rem;">
                      <div class="card-body">
                        <h5 class="card-title text-center mb-4">Registration Form</h5>
                        <form action="register" method="post">
                          <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" name="username" placeholder="Username" required>
                          </div>
                          <div class="form-group">
                              <label for="firstName">First Name</label>
                              <input type="text" class="form-control" name="firstName" placeholder="First Name" required>
                            </div>
                          <div class="form-group">
                            <label for="lastName">Last Name</label>
                            <input type="text" class="form-control" name="lastName" placeholder="Last Name" required>
                          </div>
                          <div class="form-group">
                              <label for="email">Email</label>
                              <input type="email" class="form-control" name="email" placeholder="Email" required>
                            </div>
                            <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" name="address" placeholder="Address" required>
                          </div>
                          <div class="form-group mt-3">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Password" required>
                          </div>
                        <div class="text-center mt-4">
                          <button type="submit" class="btn btn-primary w-100">Sign Up</button>
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