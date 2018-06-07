<%@page import="Todooo.Dao.EmployeeDaoImpl"%>
<%@page import="Todooo.Dao.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	EmployeeService service = new EmployeeDaoImpl();
	String id = (String)session.getAttribute("user_id");
%>
</head>
<body>
	<jsp:include page="/Link.html" flush="false" />
	<h2>직원 리스트 (페이지)</h2>
	
	${error }
	<table width="85%" align="center" border = 1>
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
	<div style="text-align:center">
		<c:forEach begin="1" end="10" var="x">
			<a href="/ListPageEmp?page=${x }">${x }</a>
		</c:forEach>
	</div> 
	
<%-- 	<div>
		<%
			int pageList = Integer.parseInt(request.getParameter("page"));
			int pagestart = 1 + ((pageList -1)/ 10) * 10;
			if (pagestart != 1) { %>
			<a href = "/ListPageEmp?page=${page }&page=<%=pagestart-1 %>">
			<input type = "button" value= "☜"></a>
			<% }
			for (int i=pagestart; i<pagestart + 10 && i<=service.maxpage(id, pageList); i++) { %>
			<a href="/ListPageEmp?page=${page }&page=<%=i %>" class="pageidx"><%=i %></a>
			<% } 
			if (pagestart + 10 < service.maxpage(id, pageList)) { %>
			<a href="/ListPageEmp?page=${page }&page=<%=pagestart + 10 %>">
			<input type="button" value="☞"></a>	
			<% } %>	
	</div> --%>
 	
</body>
</html>