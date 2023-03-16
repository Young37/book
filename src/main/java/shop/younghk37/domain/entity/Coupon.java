package shop.younghk37.domain.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Coupon {
	private Long coupon_num;
	private String coupon_name;
	private Integer coupon_discount_rate;
	private Integer coupon_discount_price;
	private Date coupon_expire_date;
}
