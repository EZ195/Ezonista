<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp"/>
        
		<section class="d-flex justify-content-center my-5">
        <div class="mr-5">
        	<img class="logo-image mr-5" src="/static/img/InstagramLogo.png" >
        </div>
        <div>
			<form id=loginForm>
				<div class="join-box my-5">
				<h1 class="text-center">로그인</h1>
					<input type="text" id="loginId" class="form-control mt-3" placeholder="아이디">
					<input type="password" id="pw" class="form-control mt-3" placeholder="비밀번호">
					
					<button type="submit" id="loginBtn" class="btn btn-info btn-block mt-3">로그인</button>
					<div class="text-center mt-3">
						<small>아직 계정이 없다면? <a href="/user/signup_view">가입하기</a></small>
					</div>
				</div>
			</form>  
        </div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
	
		$().ready(function(){
			
			$("#loginBtn").on("click",function(e){
				e.preventDefault();
				
				let loginId = $("#loginId").val();
				let pw = $("#pw").val();
				
				if(loginId == "") {
					alert("아이디를 입력해주세요");
					return;
				}
				
				if(pw == "") {
					alert("비밀번호를 입력해주세요");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"/user/sign_in",
					data:{"loginId":loginId,"password":pw},
					success:function(data) {
						if(data.result == "success"){
							alert("로그인 성공");
							location.href="/post/timeline";
						}
						else{
							alert("아이디 및 비밀번호를 확인해주세요");
						}
					},
					error:function(){
						alert("에러발생");
					}
					
				});
			});
		});
	
	</script>


</body>
</html>