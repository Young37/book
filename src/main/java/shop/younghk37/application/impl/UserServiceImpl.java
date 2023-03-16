package shop.younghk37.application.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.younghk37.application.UserService;
import shop.younghk37.domain.repository.UserRepository;
import shop.younghk37.presentation.dto.UserDTO.AddBookReq;
import shop.younghk37.presentation.dto.UserDTO.ModifyBookReq;
import shop.younghk37.presentation.dto.UserDTO.SignInReq;
import shop.younghk37.presentation.dto.UserDTO.SignUpReq;
import shop.younghk37.domain.entity.Address;
import shop.younghk37.domain.entity.Book;
import shop.younghk37.domain.entity.BookCart;
import shop.younghk37.domain.entity.BookOrder;
import shop.younghk37.domain.entity.Card;
import shop.younghk37.domain.entity.Cart;
import shop.younghk37.domain.entity.Order;
import shop.younghk37.domain.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public boolean dupleCheckUser(SignUpReq req) {
		User users[] = userRepository.selectUserByUserNumORID(new User(req.getUserNum(),null,req.getId(),null,null,0));
		if (users.length == 0)
			return false;
		else{
			return true;
		}
	}

	public Address[] getAddressByUserNum	(Long user_num) {
		Address[] addressList = userRepository.getAddressByUserNum(user_num);
		return addressList;
	}

	public User getUserById(String id) {
		User user = userRepository.getUserById(id);
		return user;
	}

	public Book[] getBookList(String book_name) {
		Book[] bookList = userRepository.getBookList(book_name);
		return bookList;
	}

	public Book getBookByBookNum(Long book_num) {
		Book book = userRepository.getBookByBookNum(book_num);
		return book;
	}

	public BookCart[] getBookCartsByCartNum(Long cart_num) {
		BookCart[] bookCarts = userRepository.getBookCartsByCartNum(cart_num);
		return bookCarts;
	}

	public Cart getCart(String id) {
		User user = userRepository.getUserById(id);
		Cart cart = userRepository.getCart(user.getUser_num());
		return cart;
	}

	public Card[] getCardList(String id) {
		User user = userRepository.getUserById(id);
		Card[] cardList = userRepository.getCardList(user.getUser_num());
		return cardList;
	}

	public Order[] getOrderList(String id) {
		User user = userRepository.getUserById(id);
		Order[] orderList = userRepository.getOrderList(user.getUser_num());
		return orderList;
	}

	public BookOrder[] getBookOrderByOrderNum(Long order_num) {
		BookOrder[] bookOrderList = userRepository.getBookOrderByOrderNum(order_num);
		return bookOrderList;
	}

	public User getUserByUserNum(Long user_num) {
		User user = userRepository.getUserByUserNum(user_num);
		return user;
	}

	public void deleteBookByBookNum(String book_num) {
		userRepository.deleteBookByBookNum(book_num);
	}

	public void modifyBook(ModifyBookReq req) {
		userRepository.modifyBook(new Book(req.getBook_num(),req.getBook_name(),req.getBook_stock(),req.getBook_price(),req.getBook_point_rate()));
		BookCart[] bookCarts = userRepository.getBookCartsByBookNum(req.getBook_num());

		for ( int i = 0 ; i<bookCarts.length; i++) {
			if(req.getBook_stock() < bookCarts[i].getBook_cart_amount()) {
				userRepository.deleteBookCartByBookCartNum(bookCarts[i].getBook_cart_num());
			}else{
				Integer price = userRepository.checkBookPriceWithBookNum(req.getBook_num());
				userRepository.addjustBookCartPrice(bookCarts[i].getBook_cart_num()
						,price*bookCarts[i].getBook_cart_amount());
			}
		}
	}

	public void addUser(User user) {
		userRepository.insertUser(user);
	}

	public void addAddress(Address address) {
		userRepository.insertAddress(address);
	}

	public boolean validCheckUser(SignInReq req) {
		User user = userRepository.selectUserByIDAndPassword(new User(null,null,req.getId(),req.getPassword(),null,null));

		if(user == null) {
			return false;
		}else {
			return true;
		}
	}

	public void add1000point(User invite_user) {
		Integer newPoint = invite_user.getPoint() + 1000;
		userRepository.updatePoint(invite_user.getUser_num(),newPoint);
	}

	public void addBook(AddBookReq req) {
		System.out.println(req.getBook_point_rate());
		userRepository.insertBook(new Book(null,req.getBook_name(),req.getBook_stock(),req.getBook_price(),req.getBook_point_rate()));
	}

	public void buyWithCart(Integer point,String id, Long user_num, BookCart[] bookCarts) {
		if(bookCarts.length == 0 ) return;

		// 준비
		User user = userRepository.getUserById(id);
		Address[] addressList = userRepository.getAddressByUserNum(user_num);
		Card[] cardList = userRepository.getCardsByUserNum(user_num);
		if(addressList.length == 0) {
			System.out.println("주소를 등록해야 함");
			return;
		}else if(cardList.length == 0) {
			System.out.println("카드를 등록해야 함");
			return;
		}

		//재고 유효성 검사
		for (int i = 0 ; i < bookCarts.length ; i++) {
			Book book = userRepository.getBookByBookNum(bookCarts[i].getBook_num());
			Integer A = book.getBook_stock();
			Integer B = bookCarts[i].getBook_cart_amount();

			if( A < B ) {
				System.out.println("재고 초과");
				return;
			}
		}

		//재고 차감
		for (int i = 0 ; i < bookCarts.length ; i++) {
			Book book = userRepository.getBookByBookNum(bookCarts[i].getBook_num());
			Integer A = book.getBook_stock();
			Integer B = bookCarts[i].getBook_cart_amount();

			Integer newStock = A - B;

			userRepository.modifyBook(
					new Book(book.getBook_num(),
							book.getBook_name(),
							newStock,
							book.getBook_price(),
							book.getBook_point_rate()
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
			userRepository.updatePoint(user_num,newPoint);
			userRepository.createOrder(
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
			userRepository.createOrder(
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


		Order order = userRepository.getLatestOrderByUserNum(user_num);
		user = userRepository.getUserById(id);

		//주문상세 생성
		for(int i = 0 ; i < bookCarts.length; i++) {
			userRepository.createBook_order(new BookOrder(
					null, //book_order_num
					order.getOrder_num(), //order_num
					bookCarts[i].getBook_num(), //book_num
					bookCarts[i].getBook_cart_amount(),//book_order_amount
					bookCarts[i].getBook_cart_price()//book_order_price
			));
			Book book = userRepository.getBookByBookNum(bookCarts[i].getBook_num());
			if(book.getBook_point_rate() != null) {
				Integer newPoint = user.getPoint() + book.getBook_point_rate() * book.getBook_price()/100;
				userRepository.updatePoint(user_num,newPoint);
				user = userRepository.getUserById(id);
			}else if(book.getBook_point_rate() == null) {
				Integer newPoint = user.getPoint() + 10 * book.getBook_price()/100;
				userRepository.updatePoint(user_num,newPoint);
				user = userRepository.getUserById(id);
			}

		}

		//월별 판매 기록하기
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyyMM");
		Date now = new Date();
		String time = sdf.format(now);
		Integer monthSell = Integer.parseInt(time);



		//장바구니 비우기
		userRepository.deleteBookCartByCartNum(bookCarts[0].getCart_num());
		userRepository.deleteCartByUserNum(user_num);
	}

	public void addToCart(Long book_num,Integer book_cart_amount, String id) {

		User user = userRepository.getUserById(id);

		Cart cart = userRepository.getCart(user.getUser_num());

		if( cart == null) {
			userRepository.createCart(user.getUser_num());
			cart = userRepository.getCart(user.getUser_num());
		}

		Integer price = userRepository.checkBookPriceWithBookNum(book_num);

		userRepository.addToCart(new BookCart(null,book_num, cart.getCart_num(),book_cart_amount,price*book_cart_amount));
	}


	public void addCard(Long card_num, Long user_num, Date card_valid_date, String card_type) {
		Card card = userRepository.getCardByCard_num(card_num);
		if (card == null) {


			userRepository.addCard(new Card(card_num,user_num,card_valid_date,card_type));


		}
	}

	public void deleteOrder(Long order_num) {
		userRepository.deleteBookOrderByOrderNum(order_num);
		userRepository.deleteOrderByOrderNum(order_num);
	}



}