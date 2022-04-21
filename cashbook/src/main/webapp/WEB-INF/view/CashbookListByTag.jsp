<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
    
    
<%
	List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>CashbookListByTag</h1>
	<table border="1">
		<thead>
			<tr>
				<th>tag</th>
				<th>kind</th>
				<th>cash</th>
				<th>cashDate</th>
				<th>memo</th>
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