package com.dsu2021.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsu2021.pj.dto.UserDTO;

import com.dsu2021.pj.dto.UserDTO.SignInReq;
import com.dsu2021.pj.dto.UserDTO.SignUpReq;
import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
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
	
	public void addUser(UserDTO.SignUpReq req) {
		userMapper.insertUser(new User(req.getUserNum(),req.getName(),req.getId(),req.getPassword()));
	}
	
	public void addAddress(UserDTO.SignUpReq req) {
		userMapper.insertAddress(new Address(null,req.getUserNum(),req.getZipCode(),req.getDefaultAddr(),req.getDetailAddr()));
	}
	
	public boolean validCheckUser(SignInReq req) {
		User user = userMapper.selectUserByIDAndPassword(new User(null,null,req.getId(),req.getPassword()));
		
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}

	public Book[] getBookList(String book_name) {
		Book[] bookList = userMapper.getBookList(book_name);
		return bookList;
	}
	
	public Book getBookByBookNum(String book_num) {
		Book book = userMapper.getBookByBookNum(book_num);
		return book;
	}
	

}
