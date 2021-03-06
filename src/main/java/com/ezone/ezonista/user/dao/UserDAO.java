package com.ezone.ezonista.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.post.model.Post;
import com.ezone.ezonista.user.model.User;

@Repository
public interface UserDAO {
	
	public int addUser(User user);
	
	public User login(
			@Param("loginId") String loginId,
			@Param("password") String password);
	
	public int selectCountLoginId();
	
	public int idDuplicate(@Param("loginId") String loginId);
	public int emailDuplicate(@Param("email") String email);
}
