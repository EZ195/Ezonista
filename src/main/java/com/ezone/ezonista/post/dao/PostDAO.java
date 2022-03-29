package com.ezone.ezonista.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.post.model.Post;

@Repository
public interface PostDAO {
	
	public int addPost(
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("content") String content,
			@Param("filePath") String filePath);
	
	public List<Post> showTimeline();

}
