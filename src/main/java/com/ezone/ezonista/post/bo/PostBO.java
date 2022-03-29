package com.ezone.ezonista.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezone.ezonista.common.FileManagerService;
import com.ezone.ezonista.post.dao.PostDAO;
import com.ezone.ezonista.post.model.Post;
import com.ezone.ezonista.post.model.PostDetail;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.addPost(userId, userName, content, filePath);
	}
	
	public List<Post> showTimeline() {
		
		return postDAO.showTimeline();
	}
	
	/*
	public List<PostDetail> getPostList() {
		
		List<Post> postList = postDAO.showTimeline();
		return
		
	}
	*/
	
}
