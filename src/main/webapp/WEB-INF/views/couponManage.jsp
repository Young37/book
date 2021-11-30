<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="admin">관리 페이지</a>
		<a href="/">책 목록</a>
	</nav>
	<main>
		<c:if test="${sessionScope.id == 'admin'}">
			<form style="display:inline" action="addCoupon" method="post" >
				쿠폰 이름 : <input type="text" name="coupon_name" value=""><br>
				할인률 : <input type="number" name="coupon_discount_rate" value=""><br>
				할인금액 : <input type="number" name="coupon_discount_price" value=""><br>
				쿠폰 만료일 : <input name = "coupon_expire_date" type = "date"><br>
				<input type="submit" value="쿠폰 추가">
			</form>
			<br>
			<form style="display:inline" action="addCouponToUser" method="post" >
				쿠폰 번호 : <input type="number" name="coupon_num" value=""><br>
				유저 번호 : <input type="number" name="user_num" value=""><br>
				<input type="submit" value="쿠폰 발급">
			</form>
			
		</c:if>
	</main>
	
	<br>
</body>
</html>