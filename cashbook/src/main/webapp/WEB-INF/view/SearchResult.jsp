<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<%
List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
<body class="container">
	<h1>검색결과</h1>
	  <table class="table table-bordered table-striped">

			<thead>
				<tr>
					<td>tag</td>
					<td>kind</td>
					<td>cash</td>
					<td>cashDate</td>
					<td>memo</td>
					
				</tr>
			</thead>
			<tbody>
			<%for(Map<String,Object> m : list){ %>
				<tr>
					<td><%=m.get("tag") %></td>
					<td><%=m.get("kind") %></td>
					<td><%=m.get("cash")%></td>
					<td><%=m.get("cashDate") %></td>
					<td><%=m.get("memo") %></td>
				</tr>
			<%} %>
		</tbody>
	</table>
</body>
</html>