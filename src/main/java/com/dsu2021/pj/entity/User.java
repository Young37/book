package com.dsu2021.pj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
	private Long user_num;
	private String name;
	private String id;
	private String password;
	private Long invite_user_num;
	private Integer point;
}
