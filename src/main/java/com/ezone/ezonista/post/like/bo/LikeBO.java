package com.ezone.ezonista.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezone.ezonista.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLike(int postId, int userId) {
		return likeDAO.addLike(postId, userId);
	}
	
	public int getLikeCount(int postId) {
		return likeDAO.getLikeCount(postId);
	}
	
	public boolean isLike(int postId, int userId) {
		int count = likeDAO.selectLikeCountByUserId(postId, userId);

/*
		// 조회된 결과가 없으면 좋아요 아닌 상태
		if(count == 0) {
			return false;
		} 
		// 좋아요 상태
		else {
			return true;
		}
*/
		 return (count != 0);
	}
	
	public int unlike(int postId, int userId) {
		
		return likeDAO.deleteLike(postId, userId);
		
	}

}
