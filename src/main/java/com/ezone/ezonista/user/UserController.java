package com.ezone.ezonista.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/signup_view")
	public String signupView() {
		
		return "/user/signUp";
	}
	
	@GetMapping("/signin_view")
	public String signinView() {
		
		return "/user/signIn";
	
	}
	
	@GetMapping("/sign_out")
	public String signOut(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/signin_view";
		
	}
}
