package com.ezone.ezonista.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.common.EncryptUtills;
import com.ezone.ezonista.post.model.Post;
import com.ezone.ezonista.user.dao.UserDAO;
import com.ezone.ezonista.user.model.User;
import com.ezone.ezonista.user.profile.bo.ProfileBO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProfileBO profileBO;

	
	public int addUser(User user) {
		
	
		String encryptPassword = EncryptUtills.md5(user.getPassword());
		user.setPassword(encryptPassword);
		userDAO.addUser(user);

		int userId = user.getId();
		String userLoginId = user.getLoginId();
		String name = user.getName();

		return profileBO.addProfile(userId, userLoginId, name);
	}
	
	public User login(String loginId, String password) {
		
		String encryptPassword = EncryptUtills.md5(password);
		
		return userDAO.login(loginId, encryptPassword);
	}
	
	public boolean idDuplicate(String loginId) {
		
		int count = userDAO.idDuplicate(loginId);
		
		// 중복 아닐 때 false
		if (count == 0) {
			return false;
		}
		// 중복일 때 true
		else {
			return true;
		}
	}
	public boolean emailDuplicate(String email) {
		
		int count = userDAO.emailDuplicate(email);
		if (count == 0) {
			return false;
		}
		else {
			return true;
		}
		
	}
}
