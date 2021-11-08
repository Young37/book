<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>UserSignUp</title>
</head>
<body>
	<div align="center">
		<label>회원가입</label>
	</div>
	<form action="/signUp" method = "post">
		<article class="container">
            <div class="page-header">
                <div class="col-md-6 col-md-offset-3">
                <h3>회원가입</h3>
                </div>
            </div>
            <div class="col-sm-6 col-md-offset-3">
                <form action= "singUp" method="post">
                    <div class="form-group">
                        <label>성명</label>
                        <input type="text" class="form-control" name = "userName" placeholder="이름을 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label>아이디</label>
                        <input type="text" class="form-control" name = "userId" placeholder="아이디를 입력해 주세요">
                    </div>
                    <div class="form-group">
                        <label>비밀번호</label>
                        <input type="password" class="form-control" name = "userPw" placeholder="비밀번호를 입력해주세요">
                    </div>
                    <div class="form-group">
                        <label>휴대폰 번호</label>
                        <input type="tel" class="form-control" name = "userNum" placeholder="휴대폰번호를 입력해 주세요">
                    </div>
                    <div class="form-group text-center">
						<div class="col-sm-offset-2 col-sm-10">
							<input class="btn btn-default" type="reset" value="초기화">
							<input class="btn btn-default" name="join" type="submit" value="가입하기" >
						</div>
					</div>
					<div class="form-group text-center">
                    	이미 회원가입이 되어있다면?<a href = "signIn">로그인</a>
                    </div>
                    <div class="form-group text-center">
                    	<a href="/">HOME</a>
           			</div>


		<!-- <div>
			NAME: <input type ="text" name = "userName"><br>
			ID: <input type ="text" name = "userId"/><br>
			PW: <input type ="password" name = "userPw"><br>
			NUM: <input type ="tel" name = "userNum"><br>

			<input type = "reset" value = "초기화">
			<input name= "join" type= "submit" value= "가입"><br>
		</div>
		<a href = "SignInController">로그인</a> -->
	</form>
</body>
</html>