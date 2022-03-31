package com.ezone.ezonista.post.model;

import java.util.List;

import com.ezone.ezonista.post.comment.model.Comment;

public class PostDetail {
	
	private Post post;
	private int likeCount;
	private List<Comment> commentList;
	private boolean isLike;

	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	// is러 시작하는 형태의 변수는 getter setter로 가져올 때 get형식에서는 get이 빠지고 set에서는 is가 빠짐
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

	
	

}
