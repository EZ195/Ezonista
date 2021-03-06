package com.ezone.ezonista.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public int commentCreate(
			@Param("postId") int postId,
			@Param("userId") int userId,
			@Param("userName") String userName,
			@Param("comment") String comment);
	public List<Comment> getCommentList(
			@Param("postId") int postId);
	
	public int deleteCommentList(
			@Param("postId") int postId);
}
