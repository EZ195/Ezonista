package com.ezone.ezonista.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.common.EncryptUtills;
import com.ezone.ezonista.user.dao.UserDAO;
import com.ezone.ezonista.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(
			String loginId, 
			String password, 
			String name, 
			String email) {
		
		String encryptPassword = EncryptUtills.md5(password);
		
		return userDAO.addUser(loginId, encryptPassword, name, email);
	}
	
	public User login(String loginId, String password) {
		
		String encryptPassword = EncryptUtills.md5(password);
		
		return userDAO.login(loginId, encryptPassword);
		
	}
}
