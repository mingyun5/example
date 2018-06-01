<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 변경</title>
<script src="/webjars/jquery/1.11.0/jquery.js"></script>
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
	<form action="/updateServlet" method="post" onsubmit="return check()">
	<input type="hidden" name="idx" value="${prevTodo.idx }">
	<table>
		<thead>
			<tr>
				<th colspan="2">일정 변경</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th >할 일</th>
				<td>
					<input type="text" name="content" value="${ prevTodo.content}" id="content">
				</td>
			</tr>
			<tr>
				<th>목표 날짜</th>
				<td>
					<input type="date" name="date" value="${ prevTodo.targetDate}" id="date">
				</td>
			</tr>
			<tr>
				<th>해결 여부</th>
				<td>
					<input type="radio" name="done" value="true" id="rad1"><label for="rad1">해결</label>&nbsp;
					<input type="radio" name="done" value="false" id="rad2"><label for="rad2">미해결</label>
				</td>
			</tr>
			<tr>
				<td>카테고리: </td> 
				<td>
					<select id="category" name="category" style="width:150px">
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
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
	<br>
	${error }
</form>
	<script>
		$("#category").val("${prevTodo.category}").prop("selected", true);
		$('input:radio[name="done"][value="${prevTodo.done}"]').prop('checked', true);
	</script>
</body>
</html>