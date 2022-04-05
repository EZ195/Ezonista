package com.ezone.ezonista.user.profile.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.user.profile.model.Profile;

@Repository
public interface ProfileDAO {
	
	public int addProfile(
			@Param("userId") int userId,
			@Param("userLoginId") String userLoginId,
			@Param("userName") String userName);
	
	public Profile getProfile(@Param("userId") int userId);

}
