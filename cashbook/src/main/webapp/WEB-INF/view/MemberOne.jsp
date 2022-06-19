<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	<style>
		.right{
			float:right;
		}
	</style>
</head>
<body class="container">
	<div id="right">${sessionMemberId}님의 회원정보 <a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a></div>
		<table class="table table-bordered table-striped">
			<tr>
				<td>memberId</td>
				<td>${member.memberId}</td>
			</tr>
			<tr>
				<td>createDate</td>
				<td>${member.createDate}</td>
			</tr>

		</table>
		<div>
			<a href="<%=request.getContextPath()%>/UpdateMemberController">비밀번호수정</a>
			<a href="<%=request.getContextPath()%>/DeleteMemberController">회원탈퇴</a>
		</div>
</body>
</html>