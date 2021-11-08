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
	
	@GetMapping("myPage")
	public String myPage() {
		return "myPage";
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
		else if(
				(req.getDefaultAddr() == "" && req.getDetailAddr() == "" && req.getZipCode() == "")
				||
				(!req.getDefaultAddr().equals("") && !req.getDetailAddr().equals("") && !req.getZipCode().equals(""))
			)
		
		
		session.setAttribute("id",req.getId());
		return "login";
	}
}
