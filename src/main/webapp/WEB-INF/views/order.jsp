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

		<c:if test="${!empty sessionScope.id}">
			<a href="myPage">돌아가기</a>
		</c:if>
		
		<br>
		
				
		<!-- 목록 -->
		
		<table style="width:70%;border:1px solid black;text-align:center">
			<thead>
				<tr>
					<th>주문번호</th><th>회원번호</th><th>주문일자</th><th>주문 총액</th><th>배송지 우편번호</th><th>배송지 기본주소</th><th>배송지 상세주소</th><th>신용카드 번호</th><th>신용카드 유효기간</th><th>신용카드 종류</th>
			</thead>
			<tbody>
				<c:if test="${!empty list}">
					<c:forEach var="li" items="${list}">
					<tr>
						<td>${li.order_num}</td>
						<td>${li.user_num}</td>
						<td>${li.order_date}</td>
						<td>${li.order_total}</td>
						<td>${li.order_zip_code}</td>
						<td>${li.order_default_addr}</td>
						<td>${li.order_detail_addr}</td>
						<td>${li.order_card_num}</td>
						<td>${li.order_card_valid_date}</td>
						<td>${li.order_card_type}</td>
					</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	

	
	</main>
	
</body>
</html>