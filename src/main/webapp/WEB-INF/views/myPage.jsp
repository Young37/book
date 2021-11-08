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
	<nav><a href="">주소 보기</a></nav>
	<nav><a href="/">카드 보기</a></nav>
	<nav><a href="cart">장바구니 보기</a></nav>
	<nav><a href="/">주문 내역 보기</a></nav>
	
</body>
</html>