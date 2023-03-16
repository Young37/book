package shop.younghk37.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookOrder {
	private Long book_order_num;
	private Long order_num;
	private Long book_num;
	private Integer book_order_amount;
	private Integer book_order_price;
}
