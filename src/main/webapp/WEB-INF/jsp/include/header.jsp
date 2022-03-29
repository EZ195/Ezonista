<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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