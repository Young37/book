package com.dsu2021.pj.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dsu2021.pj.dto.UserDTO.AddBookReq;
import com.dsu2021.pj.dto.UserDTO.ModifyBookReq;
import com.dsu2021.pj.dto.UserDTO.SignInReq;
import com.dsu2021.pj.dto.UserDTO.SignUpReq;
import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.BookCart;
import com.dsu2021.pj.entity.Card;
import com.dsu2021.pj.entity.Cart;
import com.dsu2021.pj.entity.User;
import com.dsu2021.pj.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public boolean dupleCheckUser(SignUpReq req) {
		User users[] = userMapper.selectUserByUserNumORID(new User(req.getUserNum(),null,req.getId(),null));
		if (users.length == 0)
			return false;
		else{
			return true;
		}
	}
	
	public User getUserById(String id) {
		User user = userMapper.getUserById(id);
		return user;
	}

	public Book[] getBookList(String book_name) {
		Book[] bookList = userMapper.getBookList(book_name);
		return bookList;
	}
	
	public Book getBookByBookNum(String book_num) {
		Book book = userMapper.getBookByBookNum(book_num);
		return book;
	}
	
	public BookCart[] getBookCartsByCartNum(Long cart_num) {
		BookCart[] bookCarts = userMapper.getBookCartsByCartNum(cart_num);
		return bookCarts;
	}
	
	public Cart getCart(String id) {
		User user = userMapper.getUserById(id);
		Cart cart = userMapper.getCart(user.getUser_num());
		return cart;
	}
	
	public Card[] getCardList(String id) {
		User user = userMapper.getUserById(id);
		Card[] cardList = userMapper.getCardList(user.getUser_num());
		return cardList;
	}
	
	public void deleteBookByBookNum(String book_num) {
		userMapper.deleteBookByBookNum(book_num);
	}
	
	public void modifyBook(ModifyBookReq req) {
		userMapper.modifyBook(new Book(req.getBook_num(),req.getBook_name(),req.getBook_stock(),req.getBook_price()));
	}
	
	public void addUser(User user) {
		userMapper.insertUser(user);
	}
	
	public void addAddress(Address address) {
		userMapper.insertAddress(address);
	}
	
	public boolean validCheckUser(SignInReq req) {
		User user = userMapper.selectUserByIDAndPassword(new User(null,null,req.getId(),req.getPassword()));
		
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void addBook(AddBookReq req) {
		userMapper.insertBook(new Book(null,req.getBook_name(),req.getBook_stock(),req.getBook_price()));
	}
	
	
	public void buyWithCart(String id, Long user_num, BookCart[] bookCarts) {
		//없을경우 예외처리 + 카드 주소가 하나가 아닐경우 처리
		
		User user = userMapper.getUserById(id);
		Address[] address = userMapper.getAddressByUserNum(user_num);
		Card[] cards = userMapper.getCardsByUserNum(user_num);
		
		
		Integer order_total = 0;
		for( int i = 0 ; i < bookCarts.length ; i++ ) {
			order_total += bookCarts[i].getBook_cart_price();
		}
		
		userMapper.createOrder(
		null,
		user_num,
		null,
		order_total,
		address[0].getZip_code(),
		address[0].getDefault_addr(),
		address[0].getDetail_addr(),
		cards[0].getCard_num(),
		cards[0].getCard_valid_date(),
		cards[0].getCard_type()
		);
		
		//Order order = userMapper.getLatestOrderByUserNum();
	}
	
	public void addToCart(String book_num,Integer book_cart_amount, String id) {
		
		User user = userMapper.getUserById(id);
		
		Cart cart = userMapper.getCart(user.getUser_num());
		
		if( cart == null) {
			userMapper.createCart(user.getUser_num());
			cart = userMapper.getCart(user.getUser_num());
		}
		
		Integer price = userMapper.checkBookPriceWithBookNum(book_num);
		
		userMapper.addToCart(new BookCart(null,Long.parseLong(book_num), cart.getCart_num(),book_cart_amount,price*book_cart_amount));
	}
	
	
	public void addCard(Long card_num, Long user_num, Date card_valid_date, String card_type) {
		Card card = userMapper.getCardByCard_num(card_num);
		if (card == null) {
			
			
			userMapper.addCard(new Card(card_num,user_num,card_valid_date,card_type));
			
			
		}
	}
	
	
	
}
