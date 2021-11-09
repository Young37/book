package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {
	private Long order_num;
	private Long user_num;
	private Date order_date;
	private Integer order_total;
	private String order_zip_code;
	private String order_default_addr;
	private String order_detail_addr;
	private Integer order_card_num;
	private Date order_card_valid_date;
	private String order_card_type;
}
