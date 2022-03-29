package com.ezone.ezonista.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.post.comment.dao.CommentDAO;
import com.ezone.ezonista.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int commentCreate(int postId, int userId, String userName, String comment) {
		return commentDAO.commentCreate(postId, userId, userName, comment);
	}

}
