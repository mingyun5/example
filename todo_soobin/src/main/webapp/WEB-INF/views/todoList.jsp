<%@page import="org.webapp.dao.TodoDaoImpl"%>
<%@page import="org.webapp.dao.TodoService"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 리스트</title>
<script>
function check(){
	return confirm("삭제하시겠습니까?");
}
</script>
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
 <% TodoService service = new TodoDaoImpl(); 
	String id = (String)session.getAttribute("user_id");
 %>
</head>
 <jsp:include page="/link.html" flush="false"/>
<body>
	<c:if test="${!empty list }">
	<table text-align="center">
		<tr>
			<th>할 일</th>
			<th>목표 날짜</th>
			<th>생성 날짜</th>
			<th>해결 여부</th>
			<th>카테고리</th>
			<th colspan="2">삭제하기</th>
			<th colspan="2">수정하기</th>
		</tr>
		<c:forEach var="t" items="${list}" >
     	<tr>
     		<c:set value="${t.category }" var="category"/>
     		<th>${t.content }</th>
     		<td>${t.targetDate }</td>
     		<td>${t.createDate }</td>
     		<td>${t.done }</td>
     		<td>${t.categoryName }</td>
     		<td colspan="2"><a href="/deleteServlet?idx=${t.idx }&page=${page}" onclick="return check()">삭제하기</a></td>
 			<td colspan="2"><a href="/updateServlet?idx=${t.idx }">수정하기</a></td>
     	</tr>
  		</c:forEach>
	</table>
	<form action="/listTodoServlet" method="get">
		<table>
			<tr>
				<td>카테고리 선택</td> 
				<td>
					<input type="hidden" value="1" name="page">
				</td>
				<td>
					<select id="category" name="category" style="width:150px">
					<option value="">선택해주세요</option>
						<c:forEach var="c" items="${catList }">
							<option value="${c.category }">${ c.categoryName}</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="2">
					<input type="submit" value="이동">
				</td>
			</tr>
		</table>
	</form>
	<div>
		<% 
		int a=Integer.parseInt(request.getParameter("page"));
		int c = Integer.parseInt(request.getAttribute("cate").toString());
		int pagestart=1+((a-1)/10)*10;
		if(pagestart!=1){%>
		<a href="/listTodoServlet?category=${category}&page=<%=pagestart-1%>">
		<input type="button" value="☜"></a>
		<%}
		for(int i=pagestart;i<pagestart+10&&i<=service.maxpage(id,c);i++){ %>
		   <a href="/listTodoServlet?category=${category}&page=<%=i%>" class="pageidx"><%=i%></a>
		<%} 
		if(pagestart+10<service.maxpage(id,c)){%>
		<a href="/listTodoServlet?category=${category}&page=<%=pagestart+10%>">
		<input type="button" value="☞"></a>
		<%} %>
	</div>
	</c:if>
	${success }${error }
	<c:if test="${empty list }">
		<p>일정이 없습니다</p>
	</c:if>
<script>
    $("#category").val("${category}").prop("selected", true);
</script>
</body>
</html>