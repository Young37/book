package com.dsu2021.pj.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Card {
	private Long card_num;
	private Long user_num;
	private Date card_valid_date;
	private String card_type;
}
