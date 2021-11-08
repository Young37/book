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
		
		
		장바구니 생성일자 + 장바구니 번호
		
		<!-- 목록 -->
		
		<table style="width:70%;border:1px solid black;text-align:center">
			<thead>
				<tr>
					<th>장바구니 목록 일련번호</th><th>도서번호</th><th>장바구니 번호</th><th>수량</th><th>가격</th>
			</thead>
			<tbody>
				<c:forEach var="li" items="${list}">
				<tr>
					<td>${li.book_basket_num}</td>
					<td>${li.book_num}</td>
					<td>${li.basket_num}</td>
					<td>${li.book_basket_amount}</td>
					<td>${li.book_basket_price}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<input type="submit" value="장바구니로 구매하기">
		
		
	
	</main>
	
</body>
</html>