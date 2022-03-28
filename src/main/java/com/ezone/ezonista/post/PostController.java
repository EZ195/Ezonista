package com.ezone.ezonista.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezone.ezonista.post.bo.PostBO;
import com.ezone.ezonista.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/create_view")
	public String createView() {
		return "post/create";
	}
	
	@GetMapping("/timeline")
	public String showTimeline(Model model) {
		
		List<Post> post = postBO.showTimeline();
		
		model.addAttribute("post" , post);
		
		return "post/timeline";
	}

}
