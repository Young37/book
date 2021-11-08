package com.dsu2021.pj.controller;

import javax.servlet.http.HttpSession;
<<<<<<< HEAD

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dsu2021.pj.dto.UserDTO;
import com.dsu2021.pj.dto.UserDTO.SignUp;
import com.dsu2021.pj.repository.UserMapper;
@RequiredArgsConstructor
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.dsu2021.pj.dto.UserDTO;
import com.dsu2021.pj.dto.UserDTO.SignUpRes;
import com.dsu2021.pj.service.UserService;

>>>>>>> 88c527912bb93ef818e9537691451fd496ad2cdd
@Controller
@Slf4j
//@org.springframework.stereotype.Controller
public class UserController {
<<<<<<< HEAD

	@Autowired
	private UserMapper userMapper;


=======
	
	@Autowired
	UserService service;
	
	
	//단순 페이지 이동
	
>>>>>>> 88c527912bb93ef818e9537691451fd496ad2cdd
	@GetMapping("/")
	public String mainPage(String book_name ,Model model) {
		model.addAttribute("list",service.getBookList(book_name));
		model.addAttribute("book_name",book_name);
		return "bookList";
	}

	@GetMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
<<<<<<< HEAD

	@PostMapping("/signUp")//회원가입
	public void signUp(@RequestBody UserDTO.SignUp signUp) {
		userMapper.addUser(
				new UserDTO.SignUp(null, signUp.getId(), signUp.getPassword(),signUp.getName(),signUp.getPhoneNum()));
	}

	@PostMapping("/signIn")//로그인
	public void signInPage(HttpSession session, @RequestBody UserDTO.SignIn signIn) {
		signIn = userMapper.signIn(signIn);

//      일치하는 값이 없는 경우에 예외처리 필요
		try {
			signIn.setId(signIn.getId());
			session.setAttribute("id", signIn.getId());
		}catch (Exception e){
			log.info("error 발생");
		}

	}

	@GetMapping("/signOut")//로그아웃
	public void signOutPage(HttpSession session) {
		session.invalidate();
	}

	@GetMapping("/myPage")
	public String myPage() {
		return "myPage";
	}





=======
	
	@GetMapping("myPage")
	public String myPage(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "bookList";
		}
		return "myPage";
	}
	
	@GetMapping("detail")
	public String detail(String book_num,HttpSession session, Model model) {
		if(session.getAttribute("id") == null) {
			return "bookList";
		}
		
		
		model.addAttribute("book_num",service.getBookByBookNum(book_num));
		
		return "detail";
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
			service.addUser(req);
			session.setAttribute("id",req.getId());
			return "login";
		}
		else if((!req.getDefaultAddr().equals("") && !req.getDetailAddr().equals("") && !req.getZipCode().equals(""))) {
			if(service.dupleCheckUser(req))
			{
				res.setErrorMsg("이미 있는 회원번호 또는 아이디입니다.");
				return "signUp";
			}
			service.addUser(req);
			service.addAddress(req);
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
	
>>>>>>> 88c527912bb93ef818e9537691451fd496ad2cdd
}
