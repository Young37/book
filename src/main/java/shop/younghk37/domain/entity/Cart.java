package shop.younghk37.domain.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Cart {
	private Long cart_num;
	private Long user_num;
	private Date cart_date;
}
