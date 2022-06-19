<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%
	List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("list");
	int count=(int)request.getAttribute("count");
	int currentPage=(int)request.getAttribute("currentPage");
	int lastPage=(int)request.getAttribute("lastPage");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="container">
	<h1>CashbookListByTag:(<%=count %>)</h1>
	  <table class="table table-bordered table-striped">
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
		<c:if test="${currentPage!=1}">
			<a href="${pageContext.request.contextPath}/CashbookListByTagController?currentPage=${currentPage-1}&tag=<%=list.get(0).get("tag") %>">이전</a>
		</c:if>
	<c:if test= "${currentPage != lastPage}">
		<a href="${pageContext.request.contextPath}/CashbookListByTagController?currentPage=${currentPage+1}&tag=<%=list.get(0).get("tag") %>">다음</a>
	</c:if>

</body>
</html>