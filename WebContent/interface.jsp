<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

<title>First assignment</title>

<!-- Style sheets -->
<link rel="stylesheet" type="text/css" href="interfaceStyle.css">
<link rel="stylesheet" type="text/css" href="lib\bootstrap-4.3.1-dist/css\bootstrap.css">
<link rel="stylesheet" type="text/css" href="lib\jquery-ui-1.12.1\jquery-ui.css">
<link rel="stylesheet" type="text/css" href="lib\croppie\croppie.css">


</head>
<body>

<!--Navbar-->
<nav class="navbar navbar-expand-lg" style="background-color: #EA0A8E;">
    <a class="navbar-brand" href="interface.jsp">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="nav navbar-nav">
        <li class="nav-item active">
          <a class="nav-link" href="createNewUser.html">Create New User<span class="sr-only">(current)</span></a>
        </li>
      </ul>
    </div>
</nav>

<!-- User Table -->
<table id="htmlTable" class="tableStyle">

</table>

<form>
    <input id="delteInput" type="text" placeholder="userId">
    <button id="getUserIdBtn" type="submit">get userId</button>
</form>

<!-- Java/Jquery Script -->	
<script type="text/javascript" src="lib\jquery\dist\jquery.js"></script>
<script type="text/javascript" src="lib\jquery-ui-1.12.1\jquery-ui.js"></script>
<script type="text/javascript" src="lib\bootstrap-4.3.1-dist/js\bootstrap.js"></script>
<script type="text/javascript" src="lib\croppie\croppie.js"></script>
<script type="text/javascript" src="interfaceJavascript.js"></script>
</body>
</html>