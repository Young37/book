<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> register </title>
<link href="/css/mainpage.css" type="text/css" rel="stylesheet" />
<style>
a { text-decoration: none;}
</style>
</head>
<body>
	
	<aside>
			<nav><a href="signUp">Sign Up</a></nav>
			<nav><a href="/">돌아가기</a></nav>
	</aside>
	
	<main>
	
		<c:if test="${empty sessionScope.id}">
			<br>
			<span>로그인</span>
			<form action="signIn" method="post">
				<input type="text" name="id" ><br>
				<input type="password" name="password"><br>
				<input type="submit">
			</form>
		</c:if>
		
		<c:if test="${!empty sessionScope.id}">
			<form action="signOut" method="post"><input type="submit" value="logOut"></form>
			<span>접속 중 <span style="font-size:30px;color:green">${sessionScope.id}</span>님</span>
		</c:if>
		
		<c:if test="${sessionScope.id.equals('admin') }">
			<br><br><a href="admin" >관리 페이지</a>
		</c:if>
		
	</main>

</body>
</html>