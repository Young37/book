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
import com.dsu2021.pj.entity.Card;
import com.dsu2021.pj.entity.Cart;
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
	
	
	//요청 처리 + 페이지 이동
	
	@PostMapping("signUp")
	public String signUp(UserDTO.SignUpReq req, UserDTO.SignUpRes res, HttpSession session) {
		
		System.out.println("-------------------------");
		System.out.println("null입니까?");
		System.out.println(req.getUserNum() == null);
		System.out.println(req.getName() == null);
		System.out.println(req.getId() == null);
		System.out.println(req.getPassword() == null);
		System.out.println("-------------------------");
		System.out.println("값은?");
		System.out.println(req.getUserNum());
		System.out.println(req.getName());
		System.out.println(req.getId());
		System.out.println(req.getPassword());
		System.out.println("입니다.");
		
		
		if(req.getUserNum() == null || req.getName() == "" || req.getId() == "" || req.getPassword() == "") {
			res.setErrorMsg("필수 입력값을 채우지 않았습니다.");
			return "signUp";
		}
		else if(req.getUserNum() <= 0 ) {
			res.setErrorMsg("유효하지 않은 입력값입니다.");
			return "signUp";
		}
		else if((req.getDefaultAddr() == "" && req.getDetailAddr() == "" && req.getZipCode() == "")){
			if(service.dupleCheckUser(req))
			{
				res.setErrorMsg("이미 있는 회원번호 또는 아이디입니다.");
				return "signUp";
			}
			service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword()));
			session.setAttribute("id",req.getId());
			return "login";
		}
		else if((!req.getDefaultAddr().equals("") && !req.getDetailAddr().equals("") && !req.getZipCode().equals(""))) {
			if(service.dupleCheckUser(req))
			{
				res.setErrorMsg("이미 있는 회원번호 또는 아이디입니다.");
				return "signUp";
			}
			service.addUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword()));
			
			service.addAddress(new Address(null,req.getUserNum(),req.getZipCode(),req.getDefaultAddr(),req.getDetailAddr()));
			session.setAttribute("id",req.getId());
			return "login";
		}
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
	public String buyWithCart(HttpSession session,Model model){
		if(session.getAttribute("id") == null ) {
			model.addAttribute("list",service.getBookList(""));
			model.addAttribute("book_name","");
			return "bookList";
		}
		
		Cart cart = service.getCart((String)session.getAttribute("id"));
		
		if(cart != null) {
			BookCart[] bookCarts = service.getBookCartsByCartNum(cart.getCart_num());
			service.buyWithCart((String)session.getAttribute("id"),cart.getUser_num(),bookCarts);
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
	
	
	
	
}
