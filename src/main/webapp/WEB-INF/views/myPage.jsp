<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 정보</title>
</head>
<body>
	<nav><a href="/">돌아가기</a></nav>
	<c:if test="${empty sessionScope.id}">
		${sessionScope.id}님
	</c:if>
	<nav><a href="/">주소 추가</a></nav>
	<br>
	<nav><a href="/">카드 추가</a></nav>
	
</body>
</html>