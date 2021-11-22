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
						<th>신용카드번호</th><th>회원번호</th><th>신용카드 유효기간</th><th>신용카드 종류</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list}">
						<c:forEach var="li" items="${list}">
						<tr>
							<td>${li.card_num}</td>
							<td>${li.user_num}</td>
							<td>${li.card_valid_date}</td>
							<td>${li.card_type}</td>
						</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			
			<br>
			<hr>
			<b>카드 추가하기</b>
			<form method="post" action="addCard">
			
				신용카드번호<input name = "card_num" type = "number"><br>
				신용카드 유효기간<input name = "card_valid_date" type = "date"><br>
				신용카드 종류<input name = "card_type" type = "text"><br>
				<input type="submit" value="카드 추가">
			</form>

		
	</main>
	
</body>
</html>