package com.dsu2021.pj.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String test;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SignUp{//회원가입
		//필수
		private Long userNum ;
		private String name;
		private String id;
		private String password;
		private int phoneNum;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
<<<<<<< HEAD
	public static class SignIn {//로그인
		private String id;
		private String password;
=======
	public static class SignInReq{
		//필수
		private String id;
		private String password;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SignUpRes{
		private String errorMsg;
>>>>>>> 88c527912bb93ef818e9537691451fd496ad2cdd
	}


}
	