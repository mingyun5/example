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
	<h2>직원 정보 업데이트</h2>
	${error }
	<form action="/UpdateProc" method="post">  
		<input type='hidden' name="id" value="${emp.id }" />
		<table>
			<tr>
				<td>Name: </td><td><input type="text" name="name" value="${emp.name }"/></td>
			</tr>
			<tr>
				<td>Password: </td><td><input type="password" name="password"/></td>
			</tr>
			<tr>
				<td>Email: </td><td><input type="email" name="email" value="${emp.email }" /></td>
			</tr>
			<tr>
				<td>Country: </td>
				<td>
					<select id="country" name="country" style="width:150px">
						<option value="Korea">한국</option>
						<option value="USA">USA</option>
						<option value="UK">UK</option>
						<option value="Misc">기타</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type='submit' value="정보변경"/>
			</tr>
		</table>
	</form>
</body>
<script src="webjars/jquery/3.2.1/jquery.min.js"></script>
<script>
	$("#country").val("${emp.country}").prop("selected",true);
</script>
</html>