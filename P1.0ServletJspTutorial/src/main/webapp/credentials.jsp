<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
    </style>
    <body>
        <div class="container">
            <h2 class="text-center">Credentials</h2>
            <h1>
            <%
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            %>
            Your credentials: <%= name %> <%= surname %>
            </h1>
        </div>
    </body>
</head>