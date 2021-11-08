package com.dsu2021.pj.repository;

import com.dsu2021.pj.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    //	User 로그인 시 id와 password 조회
    UserDTO.SignIn signIn(UserDTO.SignIn signIn);

    //	User 추가
    void addUser(UserDTO.SignUp signUp);


}
