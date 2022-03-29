package com.ezone.ezonista.post.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezone.ezonista.post.comment.bo.CommentBO;
import com.ezone.ezonista.post.model.Post;

@RestController
@RequestMapping("/post/comment")
public class CommentRestController {
	
	@Autowired
	private CommentBO commentBO;
	private Post post;
	
	@GetMapping("/create")
	public Map<String, String> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment
			,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		Map<String, String> result = new HashMap<>();
		
		int count = commentBO.commentCreate(postId, userId, userName, comment);
		
		if(count == 1) {
			result.put("result", "success");
		}
		else {
			result.put("result" , "fail");
		}
		
		return result;
	}

}
