<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book list</title>
</head>
<body>

	<main>

		
		
		<a href="login">login</a>
			<c:if test="${!empty sessionScope.id}">
		<a href="myPage">내 정보</a>
		</c:if>
		
		<!-- 목록 -->

			<table style="width:70%;border:1px solid black;text-align:center">
				<thead>
					<tr>
						<th>쿠폰번호</th><th>쿠폰이름</th><th>쿠폰할인율</th><th>쿠폰할인액수</th><th>쿠폰만료날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${!empty list}">
						<c:forEach var="li" items="${list}">
						<tr>
							<td>${li.coupon_num}</td>
							<td>${li.coupon_name}</td>
							<td>${li.coupon_discount_rate}</td>
							<td>${li.coupon_discount_price}</td>
							<td>${li.coupon_expire_date}</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>


		
	</main>
	
</body>
</html>