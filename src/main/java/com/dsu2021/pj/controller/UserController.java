package com.dsu2021.pj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.dsu2021.pj.dto.UserDTO;
import com.dsu2021.pj.dto.UserDTO.AddBookReq;
import com.dsu2021.pj.dto.UserDTO.ModifyBookReq;
import com.dsu2021.pj.dto.UserDTO.SignUpRes;
import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.BookCart;
import com.dsu2021.pj.entity.BookOrder;
import com.dsu2021.pj.entity.Card;
import com.dsu2021.pj.entity.Cart;
import com.dsu2021.pj.entity.CouponUser;
import com.dsu2021.pj.entity.Order;
import com.dsu2021.pj.entity.User;
import com.dsu2021.pj.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	//단순 페이지 이동
	
	@GetMapping("/")
	public String mainPage(String book_name ,Model model) {
		model.addAttribute("list",service.getBookList(book_name));
		model.addAttribute("book_name",book_name);
		return "bookList";
	}
	
	@GetMapping("login")
		public String registerPage() {
		return "login";
	}
	
	@GetMapping("signUp")
	public String signUpPage() {
		return "signUp";
	}
	
	@GetMapping("myPage")
	public String myPage(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "bookList";
		}
		return "myPage";
	}
	
	@GetMapping("detail")
	public String detailPage(Long book_num,HttpSession session, Model model) {
		model.addAttribute("book",service.getBookByBookNum(book_num));
		System.out.println("디테일");
		return "detail";
	}
	
	@GetMapping("modifyBook")
	public String modifyBookPage(Long book_num,HttpSession session, Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		model.addAttribute("book",service.getBookByBookNum(book_num));
		return "modifyBook";
	}
	
	
	@GetMapping("admin")
	public String adminPage (HttpSession session) {
		if(!session.getAttribute("id").equals("admin")) {
			return "login";
		}
		return "admin";
	}
	
	@GetMapping("bookManage")
	public String bookManage (HttpSession session) {
		if(!session.getAttribute("id").equals("admin")) {
			return "login";
		}
		return "bookManage";
	}
	
	@GetMapping("userManage")
	public String userManage (HttpSession session) {
		if(!session.getAttribute("id").equals("admin")) {
			return "login";
		}
		return "userManage";
	}
	
	@GetMapping("couponManage")
	public String couponManage (HttpSession session) {
		if(!session.getAttribute("id").equals("admin")) {
			return "login";
		}
		return "couponManage";
	}
	
	@GetMapping("addBook")
	public String addBookPage (HttpSession session, Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		return "addBook";
	}
	
	@GetMapping("cart")
	public String cart (HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		Cart cart = service.getCart((String)session.getAttribute("id"));
		
		if(cart != null) {
		model.addAttribute("cart_num",cart.getCart_num());
		model.addAttribute("cart_date",cart.getCart_date());
		model.addAttribute("list",service.getBookCartsByCartNum(cart.getCart_num()));
		}
		return "cart";
	}
	
	@GetMapping("card")
	public String card (HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		Card[] cardList = service.getCardList((String)session.getAttribute("id"));
		
		if(cardList.length != 0) {
			model.addAttribute("list",cardList);
		}
		return "card";
	}
	
	@GetMapping("order")
	public String order(HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		Order[] orderList = service.getOrderList((String)session.getAttribute("id"));
		
		if(orderList.length != 0) {
			model.addAttribute("list",orderList);
		}
		return "order";
	}
	
	@GetMapping("address")
	public String address(HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		User user = service.getUserById((String)session.getAttribute("id"));
		Address[] addressList = service.getAddressByUserNum(user.getUser_num());
		
		if(addressList.length != 0) {
			model.addAttribute("list",addressList);
		}
		
		return "address";
	}
	
	@GetMapping("coupon")
	public String coupon(HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		User user = service.getUserById((String)session.getAttribute("id"));
		//CouponUser[] couponUserList = service.getCouponUserByUserNum(user.getUser_num());
		
		//if(couponUserList.length != 0) {
		//	model.addAttribute("list",couponUserList);
		//}
		
		return "coupon";
	}
	
	@GetMapping("bookOrder")
	public String bookOrder(Long order_num,HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		BookOrder[] bookOrderList = service.getBookOrderByOrderNum(order_num);
		
		if(bookOrderList.length != 0) {
			model.addAttribute("list",bookOrderList);
		}
		
		return "bookOrder";
	}
	
	//요청 처리 + 페이지 이동
	
	@PostMapping("signUp")
	public String signUp(UserDTO.SignUpReq req, UserDTO.SignUpRes res, HttpSession session) {
		
		// 필수정보 입력 안했을 때
		// userNum --> null
		// name --> 공백
		// id --> 공백
		// password --> 공백
		if(req.getUserNum() == null || req.getName() == "" || req.getId() == "" || req.getPassword() == "") { //
			res.setErrorMsg("필수 입력값을 채우지 않았습니다.");
			return "signUp";
		}
		
		// userNum이 0이하이면 안됨
		else if(req.getUserNum() <= 0 ) {
			res.setErrorMsg("유효하지 않은 입력값입니다.");
			return "signUp";
		}
		
		// 여기까지 왔으면 정상 처리입니다. (userNum/id 중복처리는 남음)
		
		// CASE 1 : 주소 입력 전부 비워둔 경우
		else if((req.getDefaultAddr() == "" && req.getDetailAddr() == "" && req.getZipCode() == "")){
			if(service.dupleCheckUser(req))
			{
				res.setErrorMsg("이미 있는 회원번호 또는 아이디입니다."); // userNum/id 중복체크
				return "signUp";
			}
			
			User inviteUser = service.getUserByUserNum(req.getInvite_user_num()); //초대유저 존재하는지 가져오기
			if(inviteUser == null)
				service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword(),null,0));// 존재하지 않음
			else {
				service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword(),inviteUser.getUser_num(),0));
				service.add1000point(inviteUser);
			}
			
			session.setAttribute("id",req.getId());
			return "login";
		}
		// CASE 2 : 주소 입력 전부 채운 경우
		else if((!req.getDefaultAddr().equals("") && !req.getDetailAddr().equals("") && !req.getZipCode().equals(""))) {
			if(service.dupleCheckUser(req))
			{
				res.setErrorMsg("이미 있는 회원번호 또는 아이디입니다."); // userNum/id 중복체크
				return "signUp";
			}
			
			User inviteUser = service.getUserByUserNum(req.getInvite_user_num()); //초대유저 존재하는지 가져오기
			if(inviteUser == null)
				service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword(),null,0));// 존재하지 않음
			else {
				service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword(),inviteUser.getUser_num(),0));
				service.add1000point(inviteUser);
			}
			
			service.addAddress(new Address(null,req.getUserNum(),req.getZipCode(),req.getDefaultAddr(),req.getDetailAddr()));
			session.setAttribute("id",req.getId());
			return "login";
		}
		// CASE 3 : 주소를 일부만 입력
		else {
			res.setErrorMsg("주소를 일부만 입력하셨습니다.");
			return "signUp";
		}
	
	}
	
	@PostMapping("signIn")
	public String signIn(UserDTO.SignInReq req,HttpSession session) {
		
		if(service.validCheckUser(req)) {
			session.setAttribute("id",req.getId());
		}
		
		return "login";
		
	}
	
	@PostMapping("signOut")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@PostMapping("deleteBook")
	public String deleteBook(String book_num,HttpSession session,Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		service.deleteBookByBookNum(book_num);
		model.addAttribute("list",service.getBookList(""));
		model.addAttribute("book_name","");
		return "bookList";
	}
	
	@PostMapping("modifyBook")
	public String modifyBook(ModifyBookReq req,HttpSession session,Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		service.modifyBook(req);

		model.addAttribute("list",service.getBookList(""));
		model.addAttribute("book_name","");
		return "bookList";
	}
	
	@PostMapping("addBook")
	public String addBook(AddBookReq req,HttpSession session,Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		service.addBook(req);
		

		model.addAttribute("list",service.getBookList(""));
		model.addAttribute("book_name","");
		return "addBook";
	}
	
	@PostMapping("addCoupon")
	public String addCoupon(String coupon_name,Integer coupon_discount_rate, Integer coupon_discount_price, String coupon_expire_date,  HttpSession session,Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		

		return "couponManage";
	}
	
	@PostMapping("addCouponToUser")
	public String addCouponToUser(HttpSession session,Model model) {
		if(!session.getAttribute("id").equals("admin")) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		
		
		return "couponManage";
	}
	
//	@PostMapping("buy")
//	public String buy(Integer point,Long[] book_num,HttpSession session,Model model,Book book) {
//		if(session.getAttribute("id") == null ) {
//			model.addAttribute("list",service.getBookList(""));
//			model.addAttribute("book_name","");
//			return "bookList";
//		}
//		
//		Integer book_cart_amount = 1;
//		
//		for (int i = 0 ; i<book_num.length ; i++) {
//			book = service.getBookByBookNum(book_num[i]);
//			if(book_cart_amount > book.getBook_stock() || book.getBook_stock() == 0) {
//				model.addAttribute("list",service.getBookList(""));
//				model.addAttribute("book_name","");
//				return "bookList";
//			}
//		}
//		
//		for (int i = 0 ; i<book_num.length ; i++) {
//			service.addToCart(book_num[i],book_cart_amount,(String)session.getAttribute("id"));
//		}
//		
//		
//		Cart cart = service.getCart((String)session.getAttribute("id"));
//		
//		if(cart != null) {
//			BookCart[] bookCarts = service.getBookCartsByCartNum(cart.getCart_num());
//			service.buyWithCart(point,(String)session.getAttribute("id"),cart.getUser_num(),bookCarts);
//			//책 구매 로직
//		}
//		
//		model.addAttribute("list",service.getBookList(""));
//		model.addAttribute("book_name","");
//		return "bookList";
//	}
	
	@PostMapping("addToCart")
	public String addToCart(Long book_num, Integer book_cart_amount,HttpSession session,Model model,SignUpRes res, Book book) {
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		book = service.getBookByBookNum(book_num);
		
		if(book_cart_amount > book.getBook_stock() || book.getBook_stock() == 0) {
			res.setErrorMsg("재고 부족");
			return "detail";
		}
		
		service.addToCart(book_num,book_cart_amount,(String)session.getAttribute("id"));
		model.addAttribute("list",service.getBookList(""));
		model.addAttribute("book_name","");
		return "bookList";
	}
	
	@PostMapping("buyWithCart")
	public String buyWithCart(Integer point, HttpSession session,Model model){
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		Cart cart = service.getCart((String)session.getAttribute("id"));
		
		if(cart != null) {
			BookCart[] bookCarts = service.getBookCartsByCartNum(cart.getCart_num());
			service.buyWithCart(point,(String)session.getAttribute("id"),cart.getUser_num(),bookCarts);
			//책 구매 로직
		}
		
		return "myPage";
	}
	
	
	@PostMapping("addCard")
	public String addCard(Long card_num, String card_valid_date, String card_type, HttpSession session, Model model) {
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date card_valid_date_paresd = sdf.parse(card_valid_date);
			
			User user = service.getUserById((String)session.getAttribute("id"));
			Long user_num = user.getUser_num();
			
			service.addCard(card_num,user_num,card_valid_date_paresd,card_type);

			//카드 조회하라고 보내기
			Card[] cardList = service.getCardList((String)session.getAttribute("id"));
					
			if(cardList.length != 0) {
				model.addAttribute("list",cardList);
			}
			return "card";
		}catch(Exception e) {
			System.out.println("유효하지 않은 날짜 입력값. 예외 처리 필요");
			e.printStackTrace();
			return "a";
		}
		
		
	}
	
	
	@PostMapping("addAddress")
	public String addAddress(String zip_code, String default_addr, String detail_addr, HttpSession session, Model model) {
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
			
		User user = service.getUserById((String)session.getAttribute("id"));
		Long user_num = user.getUser_num();
			
		service.addAddress(new Address(null,user_num,zip_code,default_addr,detail_addr));

		//주소 조회하라고 보내기
		Address[] addressList = service.getAddressByUserNum(user.getUser_num());
		
		if(addressList.length != 0) {
			model.addAttribute("list",addressList);
		}
		
		return "address";
	}
	
	
	
	@PostMapping("deleteOrder")
	public String deleteOrder(Long order_num, HttpSession session, Model model) {
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
			
		service.deleteOrder(order_num);

		//주문 조회하라고 보내기
		Order[] orderList = service.getOrderList((String)session.getAttribute("id"));
		
		if(orderList.length != 0) {
			model.addAttribute("list",orderList);
		}
		return "order";
	}
	
	
	
	
}
