package com.ezone.ezonista.user.profile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ProfileController {
	
	@GetMapping("/profile_view")
	public String profileView() {
		
		return "/user/profile";
	}

}
