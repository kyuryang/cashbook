<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

</head>
<body class="container">
	<h1>login</h1>	
	
	<form method="post" action="<%=request.getContextPath()%>/LoginController">
		  <table class="table table-bordered table-striped">
		<tr>
			<td>memberId</td>
			<td><input type="text" name ="memberId" value="guest"></td>
		</tr>
			<tr>
			<td>memberPw</td>
			<td><input type="password" name ="memberPw" value="1234"></td>
		</tr>
	</table>
		<button type = "submit"  class="btn btn-light">로그인</button>
		<a href="<%=request.getContextPath() %>/InsertLoginController"><button type="button" class="btn btn-light">회원가입</button></a>
	</form>
</body>
</html>