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
</head>
<body>
    <div class="container">
        <h2 class="text-center">Your Data</h2>
        <form action="credentials.jsp" method="POST">
            <div class="form-group">
                <label for="name">Enter your name:</label>
                <input type="string" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="surname">Enter your surname:</label>
                <input type="string" class="form-control" id="surname" name="surname" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</body>
</html>

