package com.dsu2021.pj.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
// CREATE
	public void insertUser(User user);
	
	public void insertAddress(Address address);

// READ
	public User[] selectUserByUserNumORID(User user);
	
	public User selectUserByIDAndPassword(User user);
	
	public Book[] getBookList(String book_name);
	
	public Book getBookByBookNum(String book_num);
	
	
// PATCH

	
// PUT
	
	
// DELETE
	
	
}
