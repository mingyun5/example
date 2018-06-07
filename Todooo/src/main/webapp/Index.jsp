
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	Date date = new Date();
%>
</head>
<jsp:include page="Link.html" flush="false" />
<body>
	<h1>Welcome TEST</h1>  
	<h2>테스트</h2>
	
	<footer>
		<%= date.toString() %>
	</footer>
</body>
</html>