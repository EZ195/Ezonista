<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
</head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<body>

	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
		
		<section class="d-flex justify-content-center">
		<div class="w-75 my-5">
			<input type="file" class="mt-3" id="fileInput">
			<textarea class="form-control mt-3" rows="10" cols="50" id="contentInput"></textarea>
			<div class="d-flex justify-content-between mt-3">
				<a href="/post/timeline" class="btn btn-info">목록으로</a>
				<button type="button" id="saveBtn" class="btn btn-success">업로드</button>
			</div>		
		</div>
		</section>
		
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
		$().ready(function(){
			
			$("#saveBtn").on("click" , function(){
				
				let content = $("#contentInput").val();
				
				if (content == "") {
					alert("내용을 입력해주세요");
					return;
				}
				
				if($("#fileInput")[0].files.length == 0) {
					alert("파일을 올려주세요");
					return;
				}
					
				// 여기의 키는 컨트롤러의 파라메타에서 지정한 키랑 이름이 동일해야함
				var formData = new FormData();
				formData.append("content",content);
				formData.append("imagePath", $("#fileInput")[0].files[0]);
				
				$.ajax({
					type:"post",
					url:"/post/create",
					data:formData,
					enctype:"multipart/form-data",
					processData:false,
					contentType:false,
					success:function(data) {
						if(data.result == "success") {
							alert("업로드 성공");
						}
						
						else {
							slert("업로드 실패");
						}
					},
					error:function(){
						alert("에러 발생");
					}
				
				});
				
			});
		});
	</script>

</body>
</html>