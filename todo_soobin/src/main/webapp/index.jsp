<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	function check() {
		if($("#id").val() == "") {
			alert("아이디를 설정해주세요!");
			$("#id").focus();
			return false;
		} 
		if ($("#password").val() == "") {
			alert("비밀번호를 설정해주세요!");
			$("#password").focus();
			return false;
		}
		if ($("#name").val() == "") {
			alert("성함을 입력해주세요!!");
			$("#name").focus();
			return false;
		}
		if(!$(':input:radio[name=gender]:checked').val()) {
		    alert("성별을 선택해주세요.");
		    $("#gender").focus();
		    return false;
		}
	}
</script>
</head>
<body>
<jsp:include page="link.html" flush="false"/>
	<form action="/joinServlet" method="post" onsubmit="return check()">
	<table>
		<thead>
			<tr>
				<th colspan="2">회원가입</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th >아이디</th>
				<td>
					<input type="text" name="id" id="id">
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="Password" name="password" id="password">
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" name="name" id="name">
				</td>
			</tr>
			<tr>
				<th >성별</th>
				<td>
					<input type="radio" name="gender" id="gender" value="male" id="chk1"><label for="chk1">남자</label>&nbsp;
					<input type="radio" name="gender" id="gender1" value="female" id="chk2"><label for="chk2">여자</label>&nbsp;
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<input type="submit" value="가입">
					<input type="reset" value="취소">
				</td>
			</tr>
		</tfoot>
	</table>
</form>
<br>
${error }${error2 }
</body>
</html>