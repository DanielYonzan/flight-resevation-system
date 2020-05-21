<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
<h2>Login</h2>

<form:form action="login" method="post">
<pre>
User Name: <input type="text" name="email" placeholder="Enter email"> <br/>
Password: <input type="password" name="password"> <br/><br/>
<input type="submit" name="login">
<br/>
${msg}

</pre>
</form:form>

</body>
</html>