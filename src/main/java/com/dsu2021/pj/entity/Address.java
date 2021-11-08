package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {
	private Long addr_num;
	private Long user_num;
	private String zip_code;
	private String default_addr;
	private String detail_addr;

}
