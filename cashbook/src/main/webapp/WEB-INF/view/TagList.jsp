<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>

	<h1>tag rank</h1>
	<div> 수입/지출별 검색</div>
	<div> 날짜별 검색</div>
	<form method="post" action = "<%=request.getContextPath() %>/SearchResultByTagController">
	<table>
		<tr>
			<td>
				 <input type = "radio" name = "kind" value = "지출">지출
       			 <input type = "radio" name = "kind" value = "수입">수입
       			 <input type="date" name="forwardDate" >
       			 ~
       			  <input type="date" name="backDate" >
       			  <button type="submit">검색</button>
			</td>
		</tr>
	</table>
	</form>
	<table border="1">
		<tr>
			<th>rank</th>
			<th>tag</th>
			<th>count</th>
		</tr>
		<%
			for(Map<String, Object> map : list) {
		%>
				<tr>
					<td><%=map.get("rank")%></td>
					<td><a href="<%=request.getContextPath()%>/CashbookListByTagController?tag=<%=map.get("tag")%>"><%=map.get("tag")%></a></td>
					<td><%=map.get("cnt")%></td>
				</tr>
		<%			
			}
		%>
	</table>
</body>
</html>