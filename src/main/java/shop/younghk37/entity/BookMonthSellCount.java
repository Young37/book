package com.dsu2021.pj.entity;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookMonthSellCount {
	private Long sell_year_month;
	private Long book_num;
	private Date sell_count;
}
