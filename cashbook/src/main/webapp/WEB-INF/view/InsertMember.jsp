<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method="post" action="<%=request.getContextPath() %>/InsertLoginController">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="memberName"> <td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="memberId"><button type="submit" name="checkId">중복 검사</button> <td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="memberPw"> <td>
			</tr>
			<tr>
				<td>비밀번호 확인 </td>
				<td><input type="password" name="checkPw"> <td>
			</tr>
		</table>
		<button type="submit" >제출</button>
	</form>
</body>
</html>