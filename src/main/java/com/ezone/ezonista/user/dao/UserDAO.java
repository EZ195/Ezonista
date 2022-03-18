package com.ezone.ezonista.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.user.model.User;

@Repository
public interface UserDAO {
	
	public int addUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("name") String name,
			@Param("email") String email);
	
	public User login(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public int selectCountLoginId();
}
