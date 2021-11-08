package com.dsu2021.pj.controller;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dsu2021.pj.dto.UserDTO;
import com.dsu2021.pj.dto.UserDTO.SignUp;
import com.dsu2021.pj.repository.UserMapper;
@RequiredArgsConstructor
@RestController
@Slf4j
//@org.springframework.stereotype.Controller
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@PostMapping("signUp")//회원가입
	public void signUp(@RequestBody UserDTO.SignUp signUp) {
		userMapper.addUser(
				new UserDTO.SignUp(null, signUp.getId(), signUp.getPassword(),signUp.getName(),signUp.getPhoneNum()));
	}

	@PostMapping("signIn")//로그인
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

	@GetMapping("signOut")//로그아웃
	public void signOutPage(HttpSession session) {
		session.invalidate();
	}

	@GetMapping("myPage")
	public String myPage() {
		return "myPage";
	}





}
