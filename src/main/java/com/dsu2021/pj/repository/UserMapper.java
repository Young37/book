package com.dsu2021.pj.repository;

import com.dsu2021.pj.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.dsu2021.pj.entity.Address;
import com.dsu2021.pj.entity.Book;
import com.dsu2021.pj.entity.User;

@Mapper
@Repository
public interface UserMapper {
<<<<<<< HEAD

    //	User 로그인 시 id와 password 조회
    UserDTO.SignIn signIn(UserDTO.SignIn signIn);

    //	User 추가
    void addUser(UserDTO.SignUp signUp);


=======
	
// CREATE
	public void insertUser(User user);
	
	public void insertAddress(Address address);

// READ
	public User[] selectUserByUserNumORID(User user);
	
	public User selectUserByIDAndPassword(User user);
	
	public Book[] getBookList(String book_name);
	
// PATCH

	
// PUT
	
	
// DELETE
	
	
>>>>>>> 88c527912bb93ef818e9537691451fd496ad2cdd
}
