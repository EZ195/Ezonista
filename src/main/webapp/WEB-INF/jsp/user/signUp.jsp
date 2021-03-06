<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
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
			<div class="join-box border px-3 py-3">
				<div>
					<h1 class="text-center">회원가입</h1>
				</div>
				<div class="d-flex mt-3">
					<input type="text" id="loginIdInput" class="form-control mt-3" placeholder="아이디">
					<div id="idDuplication"><small class="text-danger">중복된 아이디 입니다.</small></div>
					<div id="idNotDuplication"><small class="text-primary">사용 가능한 아이디 입니다.</small></div>
					<div class="d-flex  mt-3">
						<button type="button" id="id_duplicate" class="btn btn-default btn-sm ml-2">중복확인</button>
					</div>
				</div>
				<input type="password" id="pwInput" class="form-control mt-3" placeholder="비밀번호">
				<input type="password" id="pwCheckInput" class="form-control mt-3" placeholder="비밀번호 확인">
				<input type="text" id="nameInput" class="form-control mt-3" placeholder="이름">
				<div class="d-flex  mt-3">
					<input type="email" id="emailInput" class="form-control mt-3" placeholder="이메일">
					<div id="emailDuplication"><small class="text-danger">중복된 이메일 입니다.</small></div>
					<div id="emailNotDuplication"><small class="text-primary">사용 가능한 이메일 입니다.</small></div>
					<div class="d-flex mt-3">
						<button type="button" id="email_duplicate" class="btn btn-default btn-sm ml-2">중복확인</button>
					</div>
				</div>
				<div>
					<button type="button" id="joinBtn" class="btn btn-block mt-3" style="background-color: #515bd4;">회원가입</button>
				</div>
				<div class="text-center mt-3">
					<small>이미 가입했다면 <a href="/user/signin_view">로그인</a> 하러가기</small>
				</div>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	
	<script>
	
	$().ready(function(){
		
		// id, email 중복 체크를 했는지 여부 저장 (false면 중복체크 안함/ true면 중복체크 했다는 의미)
		var idChecked = false;
		var emailChecked = false;
		
		// id, email이 중복된 상태인지 확인하는 변수 (true면 중복된 상태 의미) 
		var idDuplicate = true;
		var emailDuplicate = true;
		
		// 중복체크 후 id,email 입력 내용 수정 시 중복 체크 관련 값 초기화 및 관련 텍스트 숨기기
		$("#loginIdInput").on("input" , function(){
			
			idChecked = false;
			idDuplicate = true;
			
			$("#idDuplication").hide();
			$("#idNotDuplication").hide();
		});
		
		$("#emailInput").on("input" , function(){
			
			emailChecked = false;
			emailDuplicate = true;
			$("#emailDuplication").hide();
			$("#emailNotDuplication").hide();
			
		});
		
		$("#id_duplicate").on("click" ,function(){
			
			let loginId = $("#loginIdInput").val();
			
			if (loginId == ""){
				alert("아이디를 입력해주세요!");
				return;
			}
			
			$.ajax({
				type:"get",
				url:"/user/id_duplicate",
				data:{"loginId":loginId},
				
				success:function(data){
					
					idChecked = true;
					
					$("#idDuplication").hide();
					$("#idNotDuplication").hide();
					
					if(data.idDuplicate) {
						idDuplicate = true;
						$("#idDuplication").show();
					}
					
					else {
						idDuplicate = false;
						$("#idNotDuplication").show();
					}
					
				},
				error:function(){
					alert("에러 발생");
				}
				
			});
			
			
		});
		
		$("#email_duplicate").on("click",function(){
			
			let email = $("#emailInput").val();
			
			if(email == "") {
				alert("이메일을 입력해주세요");
				return;
			}
			
			$.ajax({
				type:"get",
				url:"/user/email_duplicate",
				data:{"email":email},
				success:function(data){
					
					emailChecked = true;
					
					if(data.emailDuplicate){
						emailDuplicate = true;
						$("#emailDuplication").show();
					}
					else {
						emailDuplicate = false;
						$("#emailNotDuplication").show();
					}
				},
				error:function(){
					alert("에러 발생");
				}
				
			});
			
		});
		
		$("#joinBtn").on("click" , function(){
			
			let loginId = $("#loginIdInput").val();
			let password = $("#pwInput").val();
			let passwordCheck = $("#pwCheckInput").val();
			let name = $("#nameInput").val();
			let email = $("#emailInput").val();
			
			if(loginId == "") {
				alert("아이디를 입력하세요!");
				return;
			}
			
			if(password == "") {
				alert("비밀번호를 입력하세요!");
				return;
			}
			
			if(password != passwordCheck) {
				alert("비밀번호를 확인해주세요");
				return;
			}
			if (name == "") {
				alert("이름을 입력하세요");
				return;
			}
			
			if (email == "") {
				alert("이메일을 입력하세요");
				return;
			}
			
			if (idChecked == false) {
				alert("아이디 중복체크를 확인해주세요");
				return;
			}
			
			if (idDuplicate == true) {
				alert("아이디가 중복되었습니다")
				return;
			}
			
			if (emailChecked == false) {
				alert("이메일 중복체크를 확인해주세요");
				return;
			}
			if (emailDuplicate == true) {
				alert("이메일이 중복되었습니다");
				return;
			}
			
			$.ajax({
				type:"post",
				url:"/user/sign_up",
				data:{"loginId":loginId,"password":password,"name":name,"email":email},
				success:function(data){
					if(data.result == "success") {
						alert("회원가입 성공");
						location.href = "/user/signin_view";
					}
					else {
						alert("회원가입 실패");
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