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
	public static class SignIn {//로그인
		private String id;
		private String password;
	}


}
	