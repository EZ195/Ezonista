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
		
		<section class="d-flex justify-content-center">
		<div class="w-75 my-5">
		<c:forEach var="post" items="${post }">
			<label class="bg-primary">${post.userName }</label><br>
			<img width="600px" height="600px" src="${post.imagePath }">
			<textarea class="form-control mt-3" rows="5" cols="10" id="contentInput">${post.content}</textarea>
			<br>
			<div class="m-2">
				<a href="#" class="likeBtn" data-post-id="${post.id }"><i class="bi bi-heart heart-icon text-dark"></i></a>
						
				<span class="middle-size ml-1"> 개</span>
			</div>
			<br>
			<label>댓글</label>
			<c:forEach var="com" items="${comment }">
				<label>${com.userName }</label><input type="text" value="${com.comment }">
			</c:forEach>
			<input type="text" class="form-control border-0" id="commentInput${post.id }">
			<button type="button" data-post-id="${post.id }" class="btn btn-info ml-2 commentBtn">댓글</button><br>
		</c:forEach>
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