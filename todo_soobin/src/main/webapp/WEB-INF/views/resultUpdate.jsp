<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
<style>
      table {
          border: 1px solid black;
          border-collapse: collapse;
          text-align: center;
          width: 600px;
          caption-side: bottom;
      }
      th {
          border: 1px solid black;
          background: grey;
          height: 30px;
      }
      td {
          border: 1px solid black;
          height: 30px;
      }
</style>
</head>
<jsp:include page="/link.html" flush="false"/>
<body>
	<!-- 수정전 -->
	<table>
		<tbody>
			<tr>
				<th >할 일</th>
				<td>${beforeTodo.content }</td>
			</tr>
			<tr>
				<th>목표 날짜</th>
				<td>${beforeTodo.targetDate }</td>
			</tr>
			<tr>
				<th>해결 여부</th>
				<td>${beforeTodo.done }</td>
			</tr>
			<tr>
				<td>카테고리: </td> 
				<td>${beforeTodo.categoryName }</td>
			</tr>
	</table>
	<br>
	<h3>수정 전</h3>
	<br><br><br><br>
	<!-- 수정후 -->
	<table>
		<tbody>
			<tr>
				<th >할 일</th>
				<td>${afterTodo.content }</td>
			</tr>
			<tr>
				<th>목표 날짜</th>
				<td>${afterTodo.targetDate }</td>
			</tr>
			<tr>
				<th>해결 여부</th>
				<td>${afterTodo.done }</td>
			</tr>
			<tr>
				<td>카테고리: </td> 
				<td>${afterTodo.categoryName }</td>
			</tr>
	</table>
	<br>
	<h3>수정 후</h3>
</body>
</html>






