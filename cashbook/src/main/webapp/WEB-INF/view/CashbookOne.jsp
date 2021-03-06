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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<style>
	.bt{
		float:center;
		align:center;
		text
	}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="container">
	<h1>cashbook상세보기</h1>
		  <table class="table table-bordered table-striped">
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
		<ul class="pagination justify-content-center ">
			<li><a  href="<%=request.getContextPath() %>/UpdateCashbookController?cashbookNo=<%=list.get(0).getCashbookNo()%>"><button type="button" class="btn btn-light">수정</button></a></li>
			<li><a  href="<%=request.getContextPath() %>/DeleteCashbookController?cashbookNo=<%=list.get(0).getCashbookNo()%>"><button type="button" class="btn btn-light">삭제</button></a></li>
			<li><a  href="<%=request.getContextPath() %>/CashbookListByMonthController"><button type="button" class="btn btn-light">돌아가기</button></a></li>
		</ul>
</body>
</html>