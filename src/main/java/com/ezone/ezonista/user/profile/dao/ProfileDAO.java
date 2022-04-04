package com.ezone.ezonista.user.profile.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDAO {
	
	public int addProfile(
			@Param("userId") int userId,
			@Param("userName") String userName);

}
