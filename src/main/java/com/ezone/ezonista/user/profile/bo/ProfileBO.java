package com.ezone.ezonista.user.profile.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.user.profile.dao.ProfileDAO;
import com.ezone.ezonista.user.profile.model.Profile;

@Service
public class ProfileBO {
	
	@Autowired
	private ProfileDAO profileDAO;
	
	public int addProfile(int userId, String userLoginId, String userName) {
		
		return profileDAO.addProfile(userId, userLoginId, userName);
	}
	
	public Profile getProfile(int userId) {
		
		return profileDAO.getProfile(userId);
		
	}

}
