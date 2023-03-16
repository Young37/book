package shop.younghk37.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CouponUser {
	private Long coupon_num;
	private Long user_num;
}
