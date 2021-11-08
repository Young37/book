<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form style="display:inline" action="addBook" method="post" >
			도서명 : <input type="text" name="book_name" value="${book.book_name}"><br>
			재고량 : <input type="text" name="book_stock" value="${book.book_stock}"><br>
			판매가 : <input type="text" name="book_price" value=" ${book.book_price}"><br>
			<input type="submit" value="책 추가">
		</form>
		</c:if>
	</main>
	
	<br>
</body>
</html>