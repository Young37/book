package shop.younghk37.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookCart {
	private Long book_cart_num;
	private Long book_num;
	private Long cart_num;
	private Integer book_cart_amount;
	private Integer book_cart_price;
}
