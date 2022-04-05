package com.ezone.ezonista.user.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezone.ezonista.user.profile.bo.ProfileBO;
import com.ezone.ezonista.user.profile.model.Profile;

@Controller
@RequestMapping("/user/profile")
public class ProfileController {
	
	@Autowired
	private ProfileBO profileBO;
	
	@GetMapping("/profile_view")
	public String profileView(
			HttpServletRequest request,
			Model model) {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		Profile profile = profileBO.getProfile(userId);
		
		model.addAttribute("profile", profile);
		
		return "/user/profile";
	}

}
