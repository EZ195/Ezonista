<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap" rel="stylesheet">
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
	<header class="d-flex align-items-center justify-content-between">
		<h1 class="ml-5 font-weight-bold">EZONISTA</h1>
		<div>
		<c:if test="${not empty userId }">
			<div class="mr-3 font-weight-bold">${userName }님이 로그인 중 <a href="/user/sign_out">로그아웃</a></div>
		</c:if>
		</div>
	</header>

</body>
</html>