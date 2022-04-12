package com.ezone.ezonista.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezone.ezonista.user.bo.UserBO;
import com.ezone.ezonista.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/sign_up")
	public Map<String, String> signUp(User user) {		
		
		int count = userBO.addUser(user);
		
		Map<String , String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		}
		
		else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@PostMapping("/sign_in")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		User user = userBO.login(loginId, password);
		
		
		Map<String, String> result = new HashMap<>();
		
		if(user != null) {
			result.put("result", "success");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
		}
		
		else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@GetMapping("/id_duplicate")
	public Map<String, Boolean> idDuplicate(@RequestParam("loginId") String loginId) {
		
		boolean id_duplicate = userBO.idDuplicate(loginId);
		
		Map<String, Boolean> result = new HashMap<>();
		result.put("idDuplicate", id_duplicate);
		
		return result;
	}
	
	@GetMapping("/email_duplicate")
	public Map<String, Boolean> emailDuplicate(@RequestParam("email") String email) {
		
		boolean email_duplicate = userBO.emailDuplicate(email);
		
		Map<String, Boolean> result = new HashMap<>();
		
		result.put("emailDuplicate",email_duplicate);
		
		return result;
	}

}
