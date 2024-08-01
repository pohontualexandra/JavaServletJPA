<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="style.css">
  <title>Servlet</title>
</head>
<body>
 <h2>Welcome to the Servlet</h2>
  <a class=link href="/QuickServlet/quickservlet">Clicca qui per visualizzare tutti gli user nel Db</a>
  <br>
  <br>
  <p><b>Insert name and password of a user to be put in the database</b></p>
  <form action="insert" method="POST">
  <label>Username</label>
  <input type="text" name="username" placeholder="username"></input>
  <br>
  <br>
  <label>Password</label>
  <input type="text" name="password" placeholder="password"/>
  <br>
  <input type="submit" value="Submit"/>
  </form>

  <br>
  <br>
  <p><b>Insert the id of an account you would like to update</b></p>
  <form action="modify" method="Post">
    <label>ID</label>
    <input type="number" placeholder="id" min=0 name="id"></input>
    <br>
    <br>
    <p><b>Insert the new Username and Password</b></p>
    <label>New Username</label>
    <input type="text" placeholder="username" name="modusername"></input>
    <br>
    <br>
    <label>New Password</label>
    <input type="text" placeholder="password" name="modpassword"></input>
    <input type="submit" value="modify"></input>
  </form>


</body>
</html>
