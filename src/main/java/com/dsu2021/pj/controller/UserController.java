package com.dsu2021.pj.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dsu2021.pj.dto.UserDTO;
import com.dsu2021.pj.dto.UserDTO.SignUpRes;

@Controller
public class UserController {
	
	
	//단순 페이지 이동
	@GetMapping("/")
	public String mainPage() {
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

	@GetMapping("signIn")
	public String signInPage() {
		return "signIn";
	}

	@GetMapping("signOut")
	public String signOutPage() {
		return "signOut";
	}
	
	@GetMapping("myPage")
	public String myPage() {
		return "myPage";
	}
	
	
	//요청 처리 + 페이지 이동
	
	@PostMapping("signUp")
	public String signUp(UserDTO.SignUpReq req, UserDTO.SignUpRes res, HttpSession session) {
		System.out.println("-------------------------");
		System.out.println(req.getUserNum() == null);
		System.out.println(req.getName() == null);
		System.out.println(req.getId() == null);
		System.out.println(req.getPassword() == null);
		System.out.println("-------------------------");
		System.out.println(req.getUserNum());
		System.out.println(req.getName() == "");
		System.out.println(req.getId() == "");
		System.out.println(req.getPassword() == "");
		
		if(req.getUserNum() == null || req.getName() == null || req.getId() == null || req.getPassword() == null) {
			
			
			
			
			res.setErrorMsg("필수 입력값을 채우지 않았습니다.");
			return "signUp";
		}
		else if(req.getUserNum() <= 0 || req.getName() == "" || req.getId() == "" || req.getPassword() == "") {
			res.setErrorMsg("유효하지 않은 입력값입니다.");
			return "signUp";
		}
		
		
		session.setAttribute("id",req.getId());
		return "login";
	}
}
