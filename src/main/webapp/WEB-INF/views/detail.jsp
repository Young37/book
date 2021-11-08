<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav>
		<a href="/">목록</a>
	</nav>
	<br><br>
	
	
	
	<main>
		도서번호 : ${book.book_num}<br>
		도서명 : ${book.book_name}<br>
		재고량 : ${book.book_stock}<br>
		판매가 : ${book.book_price}<br>
			
		<form style="display:inline" action="addToCart" method="post" >
			<input type="hidden" name="book_num" value="${book.book_num}">
			<input type="submit" value="장바구니에 추가">
		</form>
		
		<form style="display:inline" action="buy" method="post" >
			<input type="hidden" name="book_num" value="${book.book_num}">
			<input type="submit" value="바로 구매">
		</form>
		<br>
		<c:if test="${sessionScope.id == 'admin' }">
		<form style="display:inline" action="modifyBook" method="get" >
			<input type="hidden" name="book_num" value="${book.book_num}">
			<input type="submit" value="책 정보 수정">
		</form>
		</c:if>
		
		<c:if test="${sessionScope.id == 'admin' }">
		<form style="display:inline" action="deleteBook" method="post">
			<input type="hidden" name="book_num" value="${book.book_num}">
			<input type="submit" value="책 삭제">
		</form>
		<br>
		</c:if>
		
		
		<br>
	</main>
</body>
</html>