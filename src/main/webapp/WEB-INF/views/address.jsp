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
					<th>배송지 일련번호</th><th>회원번호</th><th>배송지 우편번호</th><th>배송지 기본주소</th><th>배송지 상세주소</th>
			</thead>
			<tbody>
				<c:if test="${!empty list}">
					<c:forEach var="li" items="${list}">
					<tr>
						<td>${li.addr_num}</td>
						<td>${li.user_num}</td>
						<td>${li.zip_code}</td>
						<td>${li.default_addr}</td>
						<td>${li.detail_addr}</td>
					</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	
			<br>
			<hr>
			<b>주소 추가하기</b>
			<form method="post" action="addAddress">
				배송지 우편번호<input name = "zip_code" type = "number"><br>
				배송지 기본주소<input name = "default_addr" type = "text"><br>
				배송지 상세주소<input name = "detail_addr" type = "text"><br>
				<input type="submit" value="주소 추가">
			</form>
	
	</main>
	
</body>
</html>