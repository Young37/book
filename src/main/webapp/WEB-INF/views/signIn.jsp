<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>UserSignIn</title>
</head>
<body>
	<div align="center">
		<label>로그인</label>
	</div>
	<form action="/signIn" method="post" class="form-horizontal">
		<div class="page-header">
			<div class="form-group text-center">
				<h3>로그인</h3>
			</div>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form role="form">
				<div class="form-group-lg">
					<label for="inputEmail3" class="col-sm-2 control-label">ID:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="userId"
							placeholder="ID">
					</div>
				</div>
				<div class="form-group-lg">
					<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="userPw"
							placeholder="Password">
					</div>
				</div>
				<div class="form-group">
					<div align="center">
						<input class="btn btn-default" type="reset" value="초기화"> <input
							class="btn btn-default" name="login" type="submit" value="로그인">
					</div>
				</div>
				<div class="form-group text-center">
					아직 회원이 아니라면?<a href="signUp">회원가입</a>
				</div>
				<div class="form-group text-center">
					<a href="/">HOME</a>
				</div>
		</div>
	</form>

</body>
</html>