<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
</head>
<body>
	<div>${sessionMemberId}님의 회원정보 <a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a></div>
	<form method="post" action="<%=request.getContextPath()%>/UpdateMemberController">
			<table border="1">
				<tr>
					<td>memberId</td>
					<td><input type="text" name="memberId" value="${member.memberId}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="pw"  name="memberPw" >
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="pwConfirm"   name="memberPw" ></td>
					
				</tr>
				<tr><td><span id="pwHelper" class="helper"></span></td></tr>
				<tr>
					<td>createDate</td>
					<td>${member.createDate}</td>
				</tr>
			</table>
		<button type="submit">수정</button>			
	</form>
</body>
<script>
$('#pwConfirm').blur(function(){					//비밀번호 유효성 검사
	if($('#pw').val() != $('#pwConfirm').val()) {
		$('#pwHelper').text('pw가 일치하지 않습니다');
		$('#pw').focus();
	} else {
		$('#pwHelper').text('');
	}
});



</script>
</html>