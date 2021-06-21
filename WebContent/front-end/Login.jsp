<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.login.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/webfonts/ionicons.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/styles.min.css">
</head>

<body>
	<div class="login-dark">
	
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<form method="post"
			action="<%=request.getContextPath()%>/member/loginHandler.do">
			<h2 class="sr-only">Login Form</h2>
			<div class="illustration">
				<i class="icon ion-ios-locked-outline"></i>
			</div>
			<div class="form-group">
				<input class="form-control" type="text" name="email" placeholder="Email">
			</div>
			<div class="form-group">
				<input class="form-control" type="password" name="password" placeholder="密碼">
			</div>
			<div class="form-group">
				<input class="form-control" type="hidden" name="action" value="login">
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-block" type="submit">登入</button>
			</div>
			<a class="forgot" href="<%=request.getContextPath()%>/front-end/RestPassword2.jsp">忘記你的emali或是密碼?</a>
			<hr>
			<a class="regist" href="<%=request.getContextPath()%>/front-end/member/Register.jsp">沒有帳號? 註冊一下吧!</a>
		</form>
	</div>
	<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/js/bootstrap.login.js"></script>
</body>
</html>
