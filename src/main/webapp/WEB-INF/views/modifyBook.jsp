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
		

		<br>
		<c:if test="${sessionScope.id == 'admin' }">
		<form style="display:inline" action="modifyBook" method="post" >
			<input type="hidden" name="book_num" value="${book.book_num}">
			도서번호 : ${book.book_num}<br>
			도서명 : <input type="text" name="book_name" value="${book.book_name}"><br>
			재고량 : <input type="text" name="book_stock" value="${book.book_stock}"><br>
			판매가 : <input type="text" name="book_price" value=" ${book.book_price}"><br>
			적립률 : <input type="number" name="book_point_rate" value=" ${book.book_point_rate}"><br>
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