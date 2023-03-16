package shop.younghk37.infrastructure.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import shop.younghk37.domain.entity.Address;
import shop.younghk37.domain.entity.Book;
import shop.younghk37.domain.entity.BookCart;
import shop.younghk37.domain.entity.BookOrder;
import shop.younghk37.domain.entity.Card;
import shop.younghk37.domain.entity.Cart;
import shop.younghk37.domain.entity.Coupon;
import shop.younghk37.domain.entity.CouponUser;
import shop.younghk37.domain.entity.Order;
import shop.younghk37.domain.entity.User;
import shop.younghk37.domain.repository.UserRepository;

@Mapper
@Repository
public interface UserMapper extends UserRepository {
}