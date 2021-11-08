package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cart {
	private Long basket_num;
	private Long user_num;
	private Date basket_date;
}
