package com.ezone.ezonista.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezone.ezonista.post.bo.PostBO;
import com.ezone.ezonista.post.model.Post;
import com.ezone.ezonista.post.model.PostDetail;

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
	public String showTimeline(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// postBo에서 사용자가 좋아요 눌렀는지 여부의 userId를 가져오기 위해 session에서 불러옴
		int userId = (Integer)session.getAttribute("userId");
		
		List<PostDetail> postList = postBO.getPostList(userId);
		
		model.addAttribute("postList" , postList);
		
		return "post/timeline";
	}

}
