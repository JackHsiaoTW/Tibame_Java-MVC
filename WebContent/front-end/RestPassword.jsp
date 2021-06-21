<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset your password?</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
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

			<div class="card-text">
				<form method="post" action="/member/loginHandler.do">
					<div class="form-group">
						<label for="exampleInputEmail1" class="show1">Enter your email address
							and we will send you a link to reset your password.
						</label> 
						<input
							type="email" class="form-control form-control-sm class="show1""
							name="email" placeholder="Enter your email address" required>
						<input
							type="hidden" class="form-control form-control-sm"
							name="action" value="forgotPassword">
					</div>
					
					<div class="form-group">
					<label class="show"2 >檢查您的電子郵件以獲取重置密碼的鏈接。如果幾分鐘後仍未出現，請檢查您的垃圾郵件文件夾。</label>
					</div>

					<button type="submit" class="btn btn-primary btn-block">Send password reset email
					</button>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
		crossorigin="anonymous"></script>
</body>
</html>