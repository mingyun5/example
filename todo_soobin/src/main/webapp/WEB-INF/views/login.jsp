<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�α���</title>
</head>
<%
	Date date = new Date();
%>
<body>
	<form action="/loginServlet" method="POST">
		Id: <input name="id" type="text"/><br>
		Password: <input type="password" name="password">
		<input type = "submit">
	</form>
<br>
${error}
<br>
Current date is <%= date %> 
</body>
</html>