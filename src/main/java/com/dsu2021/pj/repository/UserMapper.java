package com.dsu2021.pj.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.BookCart;
import com.dsu2021.pj.entity.Cart;
import com.dsu2021.pj.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
// CREATE
	public void insertUser(User user);
	
	public void insertAddress(Address address);
	
	public void insertBook(Book book);
	
	public void addToCart(BookCart bookCart);

// READ
	public User[] selectUserByUserNumORID(User user);
	
	public User selectUserByIDAndPassword(User user);
	
	public Book[] getBookList(String book_name);
	
	public Book getBookByBookNum(String book_num);
	
	public User getUserById(String id);
	
	public Cart getCart(Long user_num);
	
	public Cart createCart(Long user_num);

	public Integer checkBookPriceWithBookNum(String book_num);
	
	public BookCart[] getBookCartsByBasketNum(Long basket_num);
	
// PATCH
	
	public void modifyBook(Book book);
	
// PUT
	
	
// DELETE
	
	public void deleteBookByBookNum(String book_num);
}
