package com.dsu2021.pj.repository;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.BookCart;
import com.dsu2021.pj.entity.BookOrder;
import com.dsu2021.pj.entity.Card;
import com.dsu2021.pj.entity.Cart;
import com.dsu2021.pj.entity.Order;
import com.dsu2021.pj.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
// CREATE
	public void insertUser(User user);
	
	public void insertAddress(Address address);
	
	public void insertBook(Book book);
	
	public void addToCart(BookCart bookCart);
	
	public void createOrder(Order order);

	public void createCart(Long user_num);
	
	public void addCard(Card card);
	
	public void createBook_order(BookOrder bookOrder);
	
// READ
	
	public User[] selectUserByUserNumORID(User user);
	
	public User selectUserByIDAndPassword(User user);
	
	public Book[] getBookList(String book_name);
	
	public Book getBookByBookNum(Long book_num);
	
	public User getUserById(String id);
	
	public Cart getCart(Long user_num);
	
	public Card[] getCardList(Long user_num);

	public Integer checkBookPriceWithBookNum(Long book_num);
	
	public BookCart[] getBookCartsByCartNum(Long cart_num);
	
	public BookCart[] getBookCartsByBookNum(Long book_num);
	
	public Address[] getAddressByUserNum(Long user_num);
			
	public Card[] getCardsByUserNum(Long user_num);
	
	public Card getCardByCard_num(Long card_num);
	
	public Order getLatestOrderByUserNum(Long user_num);

	public Order[] getOrderList(Long user_num);
	
// PATCH
	
	public void modifyBook(Book book);
	
	public void addjustBookCartPrice(Long book_cart_num, Integer book_cart_price);
	
// PUT
	
	
	
// DELETE
	
	public void deleteBookByBookNum(String book_num);
	
	public void deleteBookCartByCartNum(Long cart_num);

	public void deleteCartByUserNum(Long user_num);
	
	public void deleteBookCartByBookCartNum(Long book_cart_num);
}
