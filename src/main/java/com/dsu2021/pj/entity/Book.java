package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Book {
	private Long book_num;
	private String book_name;
	private Integer book_stock;
	private Integer book_price;
	private Integer book_point_rate;
}
