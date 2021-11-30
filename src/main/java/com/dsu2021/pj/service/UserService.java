package com.dsu2021.pj.service;

import java.text.SimpleDateFormat;
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
import com.dsu2021.pj.entity.BookOrder;
import com.dsu2021.pj.entity.Card;
import com.dsu2021.pj.entity.Cart;
import com.dsu2021.pj.entity.Order;
import com.dsu2021.pj.entity.User;
import com.dsu2021.pj.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public boolean dupleCheckUser(SignUpReq req) {
		User users[] = userMapper.selectUserByUserNumORID(new User(req.getUserNum(),null,req.getId(),null,null,0));
		if (users.length == 0)
			return false;
		else{
			return true;
		}
	}
	
	public Address[] getAddressByUserNum	(Long user_num) {
		Address[] addressList = userMapper.getAddressByUserNum(user_num);
		return addressList;
	}
	
	public User getUserById(String id) {
		User user = userMapper.getUserById(id);
		return user;
	}

	public Book[] getBookList(String book_name) {
		Book[] bookList = userMapper.getBookList(book_name);
		return bookList;
	}
	
	public Book getBookByBookNum(Long book_num) {
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
	
	public Order[] getOrderList(String id) {
		User user = userMapper.getUserById(id);
		Order[] orderList = userMapper.getOrderList(user.getUser_num());
		return orderList;
	}
	
	public BookOrder[] getBookOrderByOrderNum(Long order_num) {
		BookOrder[] bookOrderList = userMapper.getBookOrderByOrderNum(order_num);
		return bookOrderList;
	}
	
	public User getUserByUserNum(Long user_num) {
		User user = userMapper.getUserByUserNum(user_num);
		return user;
	}
	
	public void deleteBookByBookNum(String book_num) {
		userMapper.deleteBookByBookNum(book_num);
	}
	
	public void modifyBook(ModifyBookReq req) {
		userMapper.modifyBook(new Book(req.getBook_num(),req.getBook_name(),req.getBook_stock(),req.getBook_price()));
		BookCart[] bookCarts = userMapper.getBookCartsByBookNum(req.getBook_num());
		
		for ( int i = 0 ; i<bookCarts.length; i++) {
			if(req.getBook_stock() < bookCarts[i].getBook_cart_amount()) {
				userMapper.deleteBookCartByBookCartNum(bookCarts[i].getBook_cart_num());
			}else{
				Integer price = userMapper.checkBookPriceWithBookNum(req.getBook_num());
				userMapper.addjustBookCartPrice(bookCarts[i].getBook_cart_num()
						,price*bookCarts[i].getBook_cart_amount());
			}
		}
	}
	
	public void addUser(User user) {
		userMapper.insertUser(user);
	}
	
	public void addAddress(Address address) {
		userMapper.insertAddress(address);
	}
	
	public boolean validCheckUser(SignInReq req) {
		User user = userMapper.selectUserByIDAndPassword(new User(null,null,req.getId(),req.getPassword(),null,null));
		
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void add1000point(User invite_user) {
		Integer newPoint = invite_user.getPoint() + 1000;
		userMapper.updatePoint(invite_user.getUser_num(),newPoint);
	}
	
	public void addBook(AddBookReq req) {
		userMapper.insertBook(new Book(null,req.getBook_name(),req.getBook_stock(),req.getBook_price()));
	}
	
	public void buyWithCart(Integer point,String id, Long user_num, BookCart[] bookCarts) {
		if(bookCarts.length == 0 ) return;
		
		// 준비
		User user = userMapper.getUserById(id);
		Address[] addressList = userMapper.getAddressByUserNum(user_num);
		Card[] cardList = userMapper.getCardsByUserNum(user_num);
		if(addressList.length == 0) {
			System.out.println("주소를 등록해야 함");
			return;
		}else if(cardList.length == 0) {
			System.out.println("카드를 등록해야 함");
			return;
		}
		
		//재고 유효성 검사
		for (int i = 0 ; i < bookCarts.length ; i++) {
			Book book = userMapper.getBookByBookNum(bookCarts[i].getBook_num());
			Integer A = book.getBook_stock();
			Integer B = bookCarts[i].getBook_cart_amount();
			
			if( A < B ) {
				System.out.println("재고 초과");
				return;
			}
		}
		
		//재고 차감
		for (int i = 0 ; i < bookCarts.length ; i++) {
			Book book = userMapper.getBookByBookNum(bookCarts[i].getBook_num());
			Integer A = book.getBook_stock();
			Integer B = bookCarts[i].getBook_cart_amount();
			
			Integer newStock = A - B;
			
			userMapper.modifyBook(
					new Book(book.getBook_num(),
							book.getBook_name(),
							newStock,
							book.getBook_price()
							)
			);
		}
		
		//주문 총액 계산
		Integer order_total = 0;
		for( int i = 0 ; i < bookCarts.length ; i++ ) {
			order_total += bookCarts[i].getBook_cart_price();
		}
		
		// null 안됨. 0미만 안됨. 포인트 있어야 됨.
		if( point != null  && point >= 0 && user.getPoint() >= point) {
			
			//포인트 값이 정상적일 때 주문 생성
			Integer newPoint = user.getPoint() - point;
			userMapper.updatePoint(user_num,newPoint);
			userMapper.createOrder(
					new Order(
							null, // order_num
							user_num, // user_num
							null, // order_date
							order_total, // order_total
							addressList[0].getZip_code(), // order_zip_code
							addressList[0].getDefault_addr(), // order_default_addr
							addressList[0].getDetail_addr(), // order_detail_addr
							cardList[0].getCard_num(), // order_card_num
							cardList[0].getCard_valid_date(), // order_card_valid_date
							cardList[0].getCard_type(),// order_card_type
							point
					)
			);
		}else {
			
			//포인트 값이 비정상적일 때 주문 생성
			userMapper.createOrder(
					new Order(
							null, // order_num
							user_num, // user_num
							null, // order_date
							order_total, // order_total
							addressList[0].getZip_code(), // order_zip_code
							addressList[0].getDefault_addr(), // order_default_addr
							addressList[0].getDetail_addr(), // order_detail_addr
							cardList[0].getCard_num(), // order_card_num
							cardList[0].getCard_valid_date(), // order_card_valid_date
							cardList[0].getCard_type(),// order_card_type
							0
					)
			);
		}
		
		
		Order order = userMapper.getLatestOrderByUserNum(user_num);
		
		//주문상세 생성
		for(int i = 0 ; i < bookCarts.length; i++) {
			userMapper.createBook_order(new BookOrder(
					null, //book_order_num
					order.getOrder_num(), //order_num
					bookCarts[i].getBook_num(), //book_num
					bookCarts[i].getBook_cart_amount(),//book_order_amount
					bookCarts[i].getBook_cart_price()//book_order_price
			));
		}
		
		//월별 판매 기록하기
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMM");
		Date now = new Date();
		String time = sdf.format(now);
		Integer monthSell = Integer.parseInt(time);
		
		
		
		//장바구니 비우기
		userMapper.deleteBookCartByCartNum(bookCarts[0].getCart_num());
		userMapper.deleteCartByUserNum(user_num);
	}
	
	public void addToCart(Long book_num,Integer book_cart_amount, String id) {
		
		User user = userMapper.getUserById(id);
		
		Cart cart = userMapper.getCart(user.getUser_num());
		
		if( cart == null) {
			userMapper.createCart(user.getUser_num());
			cart = userMapper.getCart(user.getUser_num());
		}
		
		Integer price = userMapper.checkBookPriceWithBookNum(book_num);
		
		userMapper.addToCart(new BookCart(null,book_num, cart.getCart_num(),book_cart_amount,price*book_cart_amount));
	}
	
	
	public void addCard(Long card_num, Long user_num, Date card_valid_date, String card_type) {
		Card card = userMapper.getCardByCard_num(card_num);
		if (card == null) {
			
			
			userMapper.addCard(new Card(card_num,user_num,card_valid_date,card_type));
			
			
		}
	}
	
	public void deleteOrder(Long order_num) {
		userMapper.deleteBookOrderByOrderNum(order_num);
		userMapper.deleteOrderByOrderNum(order_num);
	}
	
	
	
}
