<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset your password?</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" >
<style>
html, body {
	height: 100%;
}

body {
	display: -ms-flexbox;
	display: -webkit-box;
	display: flex;
	-ms-flex-align: center;
	-ms-flex-pack: center;
	-webkit-box-align: center;
	align-items: center;
	-webkit-box-pack: center;
	justify-content: center;
	background-color: #f5f5f5;
}

form {
	padding-top: 10px;
	font-size: 14px;
	margin-top: 30px;
}

.card-title {
	font-weight: 300;
}

.btn {
	font-size: 14px;
	margin-top: 20px;
}

.login-form {
	width: 320px;
	margin: 20px;
}

.sign-up {
	text-align: center;
	padding: 20px 0 0;
}

span {
	font-size: 14px;
}
</style>
</head>

<body>
	<div class="card login-form">
		<div class="card-body">
			<h3 class="card-title text-center">Reset password</h3>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<div class="card-text">
				<form method="post" action="<%=request.getContextPath()%>/member/loginHandler.do">
					
					<c:if test="${restPwdOption == null}">
						<div class="form-group">
							<label for="exampleInputEmail1" class="show1">Enter your
								email address and we will send you a link to reset your password.
							</label> 
							<input type="email" class="form-control form-control-sm show1" name="email" 
								placeholder="Enter your email address" required> 
							<input type="hidden" class="form-control form-control-sm" name="action"
								value="forgotPassword">
						</div>
						<button type="submit" class="btn btn-primary btn-block">Send password reset email</button>
					</c:if>
					
					<c:if test="${restPwdOption == '1'}">
						<div class="form-group">
							<label class="show2">檢查您的電子郵件以獲取重置密碼的鏈接。如果幾分鐘後仍未出現，請檢查您的垃圾郵件文件夾。</label>
							<input type="hidden" class="form-control form-control-sm" name="action" value="returnLogin">
						</div>
						<button type="submit" class="btn btn-primary btn-block show2">返回登入</button>
					</c:if>
					
					<c:if test="${restPwdOption == '2'}">
						<div class="form-group">
							<label class="show2">請輸入新密碼:</label>
							<input type="text" class="form-control form-control-sm" name="newPassword">
							<input type="hidden" class="form-control form-control-sm" name="action" value="setNewPassword">
							<input type="hidden" class="form-control form-control-sm" name="memberMail" value="${memberMail}">
							<input type="hidden" class="form-control form-control-sm" name="token" value="${restPwdToken}">
						</div>
						<button type="submit" class="btn btn-primary btn-block show2">送出</button>
					</c:if>
					
					<c:if test="${restPwdOption == '3'}">
						<div class="form-group">
							<label class="show2">密碼重設完成，請使用新密碼登入</label>
							<input type="hidden" class="form-control form-control-sm" name="action" value="returnLogin">
						</div>
						<button type="submit" class="btn btn-primary btn-block show2">返回登入</button>
					</c:if>
					
					<c:if test="${restPwdOption == '4'}">
						<div class="form-group">
							<label class="show2">密碼重設連結已超過時效，請重新操作一次！</label>
							<a href="<%=request.getContextPath()%>/front-end/RestPassword2.jsp">password reset link</a>
						</div>
					</c:if>

				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>