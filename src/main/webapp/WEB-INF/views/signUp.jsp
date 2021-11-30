<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
span {width:85px;display:inline-block}
</style>
</head>
<body>
	<nav><a href="login">돌아가기</a></nav>
	

	
	${ signUpRes.errorMsg == null ? '':signUpRes.errorMsg }<br>
	<form  action="signUp" method="post">
		<span>회원번호 : </span><input type="number" name="userNum" placeholder="필수"><br>
		<span>이름 : </span><input type="text" name="name" placeholder="필수"><br>
		<span>아이디 : </span><input type="text" name="id" placeholder="필수"><br>
		<span>비밀번호 : </span><input type="password" name="password" placeholder="필수"><br>
		
		선택<br>
		<span>기본주소 : </span><input type="text" name="defaultAddr"><br>
		<span>상세주소 : </span><input type="text" name="detailAddr"><br>
		<span>우편번호 : </span><input type="text" name="zipCode"><br>
		<input type="submit">
		<br>
		추천인<br>
		<input name="invite_user_num" type="number">
		
		
	</form>

</body>
</html>