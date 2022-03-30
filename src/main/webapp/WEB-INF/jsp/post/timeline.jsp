<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Time Line</title>
</head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<body>

	<div id="wrap">
	<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		
		<div class="create-icon mb-1 mt-1">
			<a href="/post/create_view"><i class="bi-pencil-square"></i></a>
		</div>
		<section class="d-flex justify-content-center">
			<div class="w-75 my-5 ">
			<div>
				<c:forEach var="post" items="${post }">
					<div class="card border rounded mt-3">
						<div class="d-flex justify-content-between p-2 border-bottom">
							<h5 class="font-weight-bold">${post.userName }</h5><br>
						</div>
						<div>
							<img width="600px" height="600px" src="${post.imagePath }">
						</div>
						<!-- 좋아요 -->
						<div class="m-2">
						<c:choose>
							<c:when test="${postDetail.like }">
							<%-- 좋아요 눌리면 빨간 하트로 변함 --%>
								<i class="bi bi-heart-fill heart-icon text-danger"></i>
							</c:when>
							<c:otherwise>
								<a href="#" class="likeBtn" data-post-id="${post.id }"><i class="bi bi-heart heart-icon text-dark"></i></a>								
							</c:otherwise>
						</c:choose>
						<span class="middle-size ml-1">${postDetail.likeCount} 개</span>
						</div>
						
						<div class="mx-3">
							${postDetail.post.content}
						</div>
						<div  class="middle-size m-2">
							<c:forEach var="comment" items="${postDetail.commentList }">
								<div class="mt-1">
									<b>${comment.userName }</b><input type="text" value="${comment.comment }">						
								</div>
							</c:forEach>
						</div>
						<div class="d-flex mt-2 border-top">
							<input type="text" class="form-control border-0" id="commentInput${postDetail.post.id }">
							<button type="button" data-post-id="${postDetail.post.id }" class="btn btn-info mx-3 ml-3 commentBtn">댓글</button>
						</div>
					</div>
				</c:forEach>
			</div>
			</div>
		</section>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
		$().ready(function(){
			$(".commentBtn").on("click", function(){
				
				let postId = $(this).data("post-id");
				
				// Ex $("#commentInput4")
				let comment = $("#commentInput" + postId).val();
				if(comment == "") {
					alert("댓글을 입력하세요");
					return;
				}
				
				$.ajax({
					
					type:"get",
					url:"/post/comment/create",
					data:{"postId":postId,"comment":comment},
					success:function(data){
						if(data.result == "success") {
							alert("뎃글 작성 성공");
						}
						else {
							alert("댓글 작성 실패");
						}
						
					},
					error:function(){
						alert("에러 발생");
					}
					
				});
			});
			
			$(".likeBtn").on("click", function(e) {
				e.preventDefault();
				
				let postId = $(this).data("post-id");
				
				
				$.ajax({
					type:"get",
					url:"/post/like",
					data:{"postId":postId},
					success:function(data) {
						
						if(data.result == "success") {
							location.reload();
						} else {
							alert("좋아요 실패");
						}
					},
					error:function() {
						alert("좋아요 에러");
					}
					
				});
				
				
			});
		});
	</script>

</body>
</html>