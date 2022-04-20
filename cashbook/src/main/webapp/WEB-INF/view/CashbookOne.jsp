<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.*" %>
<%@ page import="vo.*" %>

<%
	List<Cashbook> list = (List<Cashbook>)request.getAttribute("list");			//컨트롤러에 setAttribute에 저장한 list를 list에 저장

	for(Cashbook c : list){
		System.out.println(c +"<-- viewOne");									//디버깅

	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>cashbookOne</h1>
		<table border="1">
			<% for(Cashbook c : list){ %>
			<tr>
				<td>cashbookNo</td>
				<td><input type="text" name="cashbookNo" value="<%=c.getCashbookNo()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>cash</td>
				<td><input type="number" name="cash" value="<%=c.getCash()%>" readonly="readonly"></td>
			</tr>
			<tr>
				<td>kind</td>
				<td><%=c.getKind()%></td>
			</tr>
			<tr>
				<td>cashDate</td>
				<td><%=c.getCashDate() %>
			</tr>
			<tr>
				<td>memo</td>
				<td><%=c.getMemo() %>
			</tr>
			<%} %>
		</table>
		<a href="<%=request.getContextPath() %>/UpdateCashbookController?cashbookNo=<%=list.get(0).getCashbookNo()%>">수정</a>
		<a href="<%=request.getContextPath() %>/DeleteCashbookController?cashbookNo=<%=list.get(0).getCashbookNo()%>">삭제</a>
</body>
</html>