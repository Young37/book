package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookCart {
	private Long book_basket_num;
	private Long book_num;
	private Long basket_num;
	private Integer book_basket_amount;
	private Integer book_basket_price;
}
