package com.ezone.ezonista.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public int addLike(
			@Param("postId") int postId,
			@Param("userId") int userId);
}
