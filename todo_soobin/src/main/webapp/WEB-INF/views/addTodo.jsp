<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 추가</title>
<script>
	function check() {
		if($("#content").val() == "") {
			alert("일정을 등록해주세요!");
			$("#content").focus();
			return false;
		} 
		if ($("#date").val() == "") {
			alert("목표 날짜를 설정해주세요!");
			$("#date").focus();
			return false;
		}
	}
</script>
</head>
<jsp:include page="/link.html" flush="false"/>
<body>
	<form action="/addServlet" method="post" onsubmit="return check()">
	<table>
		<thead>
			<tr>
				<th colspan="2">일정 추가</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th >할 일</th>
				<td>
					<input type="text" name="content" id="content">
				</td>
			</tr>
			<tr>
				<th>목표 날짜</th>
				<td>
					<input type="date" name="date" id="date">
				</td>
			</tr>
			<tr>
				<td>카테고리: </td> 
				<td>
					<select name="category" style="width:150px">
						<option value="1">운동</option>
						<option value="2">친구</option>
						<option value="3">가족</option>
						<option value="4">공부</option>
						<option value="5">일</option>
					</select>
				</td>
			</tr>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="submit" value="일정 추가">
					<input type="reset" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
</body>
</html>