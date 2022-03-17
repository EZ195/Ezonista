package com.ezone.ezonista.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(
			String loginId, 
			String password, 
			String name, 
			String email) {
		
		return userDAO.addUser(loginId, password, name, email);
	}
}
