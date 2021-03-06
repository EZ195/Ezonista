package com.ezone.ezonista.post.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezone.ezonista.common.FileManagerService;
import com.ezone.ezonista.post.comment.bo.CommentBO;
import com.ezone.ezonista.post.comment.model.Comment;
import com.ezone.ezonista.post.dao.PostDAO;
import com.ezone.ezonista.post.like.bo.LikeBO;
import com.ezone.ezonista.post.model.Post;
import com.ezone.ezonista.post.model.PostDetail;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		String filePath = FileManagerService.saveFile(userId, file);
		
		return postDAO.addPost(userId, userName, content, filePath);
	}
	
	/*
	public List<Post> showTimeline() {
		
		return postDAO.showTimeline();
	}
	*/
	
	public List<PostDetail> getPostList(int userId) {
		
		List<Post> postList = postDAO.showTimeline();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		// 포스트마다 댓글 좋아요 가져오기
		for(Post post : postList) {
			// 좋아요 개수 얻어 오기 postId
			int likeCount = likeBO.getLikeCount(post.getId());
			
			// 댓글 리스트 가져오기
			List<Comment> commentList = commentBO.getCommentList(post.getId());
			
			// 로그인한 사용자가 좋아요 눌렀는지 여부
			boolean isLike = likeBO.isLike(post.getId(), userId);
			
			PostDetail postDetail = new PostDetail();
			
			postDetail.setPost(post);
			postDetail.setLikeCount(likeCount);
			postDetail.setCommentList(commentList);
			postDetail.setLike(isLike);
			
			postDetailList.add(postDetail);
		}
		
		return postDetailList;
		
	}
	
	public int deletePostDetail(int postId, int userId) {
		
		// postId 로 Post 객체 얻어오기
		Post post = postDAO.selectPost(postId);
		
		if(post.getUserId() == userId) {
			// 파일 삭제
			FileManagerService.removeFile(post.getImagePath());
			
			// 댓글 삭제
			commentBO.deleteCommentList(postId);
			
			// 좋아요 삭제
			likeBO.deleteLikeList(postId);
			
			return postDAO.deletePost(postId);
		}
		
		return 0;
		
		
	}
	
	
	
	
}
