<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/Link.html" flush="false" />
	<h2>직원 리스트</h2>
	${error }
	<table border = 1>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Country</th>
		</tr>
		<c:forEach items='${list }' var = 'emp'>
		<tr>
			<td>${emp.id }</td>
			<td>${emp.name }</td>
			<td>${emp.email }</td>
			<td>${emp.country }</td>
			<td><a href="/UpdateEmp?id=${emp.id }">Update</a></td>
			<td><a href="/DeleteEmp?id=${emp.id }">Delete</a></td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>