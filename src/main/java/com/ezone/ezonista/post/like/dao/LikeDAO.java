package com.ezone.ezonista.post.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ezone.ezonista.post.like.model.Like;

@Repository
public interface LikeDAO {
	public int addLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int getLikeCount(
			@Param("postId") int postId);
	
	public int selectLikeCountByUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int deleteLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	public int deleteLikeList(
			@Param("postId") int postId);

}
