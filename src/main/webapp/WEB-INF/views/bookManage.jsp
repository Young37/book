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
		<a href="admin">나가기</a>
	</nav>
	<main>
		<form action="write" method="post"><br>
		<span>제목</span>
		<input style="width:256px;" type="text" name="title"><br><br>
		<textarea style="resize:none" rows="30" cols="40" name="content"></textarea>
		<input type="submit">
		</form>
	</main>
	
	<br>
	<span>접속 중 <span style="font-size:30px;color:green">${sessionScope.id}</span>님</span>
</body>
</html>