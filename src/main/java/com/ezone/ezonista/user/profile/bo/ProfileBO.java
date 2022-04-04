package com.ezone.ezonista.user.profile.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.user.profile.dao.ProfileDAO;

@Service
public class ProfileBO {
	
	@Autowired
	private ProfileDAO profileDAO;
	
	public int addProfile(int userId, String userName) {
		
		return profileDAO.addProfile(userId, userName);
		
	}

}
