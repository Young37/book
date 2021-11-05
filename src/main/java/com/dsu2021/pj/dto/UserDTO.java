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
	public static class SignUpReq{
		//필수
		private Long userNum ;
		private String name;
		private String id;
		private String password;
		//선택
		private String defaultAddr;
		private String detailAddr;
		private String zipCode;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SignUpRes{
		private String errorMsg;
	}

}
	