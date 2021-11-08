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
		<!-- 
		전달하는 parameter : field, pattern, page
		전달받는 parameter : field, pattern, page
		
		
		 -->
		
		
		<a href="login">login</a>
		<c:if test="${!empty sessionScope.id}">
		<a href="myPage">내 정보</a>
		</c:if>
		
		<!-- 검색 -->
		<form action="" method="get">
			<select name="field">
				<option value="writer_name">도서명</option>
			</select>
			<input type="text" name="pattern" value="${param.pattern}">
			<input type="submit" value = "검색">
		</form>
		
		<!-- 목록 -->
		
		<table style="width:70%;border:1px solid black;text-align:center">
			<thead>
				<tr>
					<th>도서번호</th><th>도서명</th><th>재고량</th><th>판매가</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="li" items="${list}">
				<tr>
					<td>${li.id}</td>
					<td>${li.writerName}</td>
					<td><a href="list/detail?id=${li.id}">${li.title}</a>&nbsp[${li.cmtCount}]</td>
					<td>${li.hit}</td>
					<td><fmt:formatDate pattern="yyyy.MM.dd" value="${li.regdate}"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 페이징 -->
		
		<c:set var="page" value="${ empty param.page ? 1 : param.page }"/>
		<c:set var="startNum" value="${page-(page-1)%10}" />
		
		 <br>  &nbsp  ${page} / ${!empty pageCount ? pageCount : 1} pages <br><br>
		
		<c:if test="${page==1}">
		<span style="cursor:pointer" onclick="alert('첫 페이지 입니다.')">이전</span>
		</c:if>
		<c:if test="${page!=1 && startNum==1}">
		<a href="?page=1&field=${param.field==null?'title':param.field}&pattern=${param.pattern==null?'':param.pattern}">이전</a>
		</c:if>
		<c:if test="${startNum!=1}">
		<a href="?page=${startNum-1}&field=${param.field==null?'title':param.field}&pattern=${param.pattern==null?'':param.pattern}"></a>
		</c:if>
		
		

		<ul style="list-style:none;margin:0; height:50px;padding:0;display:inline;">
			<c:forEach var="i" begin="0" end="4">
			<c:if test="${startNum+i <= pageCount}">
			<li style="display:inline;"><a style="${startNum+i==page?'color:orange':''}" href="?page=${startNum+i}&field=${param.field==null?'title':param.field}&pattern=${param.pattern==null?'':param.pattern}">${startNum+i}</a></li>
			</c:if>
			</c:forEach>
		</ul>
		
		<!-- 다음 버튼 -->
		
		<c:if test="${startNum+4<pageCount}">
		<a href="?page=${startNum+5}&field=${param.field==null?'title':param.field}&pattern=${param.pattern==null?'':param.pattern}">다음</a>
		</c:if>
		<c:if test="${startNum+4>=pageCount && page!=pageCount}">
		<a href="?page=${pageCount}&field=${param.field==null?'title':param.field}&pattern=${param.pattern==null?'':param.pattern}">다음</a>
		</c:if>
		<c:if test="${page==pageCount}">
		<span style="cursor:pointer;"onclick="alert('마지막 페이지 입니다.')">다음</span>
		</c:if>
		
	
	</main>
	
</body>
</html>