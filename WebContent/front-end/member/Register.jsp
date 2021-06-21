<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
<title>Register</title>

<style>
	.bg-register-image{
		background : url("<%=request.getContextPath()%>/resources/img/climb.jpg")!important;
	}
	.form__status.is-alert {
    	background: #c9302c;
    	color: #FFF;
    	border-radius: 10px;
    	text-indent: 1rem;
	}
	.form__status.is-warning {
    	background: #ec971f;
    	color: #FFF;
    	border-radius: 10px;
    	text-indent: 1rem;
	}
</style>
</head>
<body class="bg-gradient-primary">

    <div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                        <%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color:red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color:red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="account" name="account"
                                        placeholder="Account" value="<%=memberVO==null ? "" : memberVO.getAccount() %>" required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="password" name="password" placeholder="Password" required>
                                    </div>
                                    <div class="col-sm-6">
                                        <input type="password" class="form-control form-control-user"
                                            id="repeatPassword" placeholder="Repeat Password" required>
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="text" class="form-control form-control-user" id="name" name="name"
                                            placeholder="Name" value="<%=memberVO==null ? "" : memberVO.getName() %>" required>
                                    </div>
                                    <%-- <div class="col-sm-6">
                                        <input type="file" class="form-control form-control-user" id="member_pic">
                                    </div> --%>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="id_number" name="idNumber"
                                        placeholder="ID_Number" value="<%=memberVO==null ? "" : memberVO.getIdNumber() %>" required>
                                </div>
                                <div class="form-group">
                                    <input type="date" class="form-control form-control-user" id="birthDate" name="birthDate"
                                        placeholder="Birth_Date" value="<%=memberVO==null ? "" : memberVO.getBirthDate() %>">
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="phone" name="phone"
                                        placeholder="Phone" value="<%=memberVO==null ? "" : memberVO.getPhone() %>">
                                </div>
                                
                                <div class="form-group">
                                    <input type="email" class="form-control form-control-user" id="email" name="email"
                                        placeholder="Email Address" value="<%=memberVO==null ? "" : memberVO.getEmail() %>" required>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-6 mb-3 mb-sm-0" id="preview"></div>
                                    <input type="file" class="form-control form-control-user" id="memberPic" name="memberPic">
                                </div>
                                <input type="hidden" name="action" value="addtMem">
                                <input type="submit" id="sendRegister" class="btn btn-primary btn-user btn-block" value="send">
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="<%=request.getContextPath()%>/front-end/RestPassword2.jsp">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="<%=request.getContextPath()%>/front-end/Login.jsp">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/register.js"></script>

</body>

<script type="text/javascript">

function clearDupAlert(){
	$('.form__status').each(function(index){
        $(this).remove();
    });
}

// ajax檢查帳號/email是否有被註冊過
$("#account, #email").blur(function() {
	$.ajax({
		url : "<%=request.getContextPath()%>/member/member.do",
		type : "POSt",
		data : {
			account : $("#account").val(),
			email : $("#email").val(),
			action : "checkIsRegister"
		},
		dataType: "json",
		success : function(data) {
			clearDupAlert();
			$(data).each(function(i, item){
					console.log("accountCheck:"+item.accountCheck);
					console.log("emailCheck:"+item.emailCheck);
				if(item.accountCheck){
					$('#account').closest('div.form-group').append(`
							<div id="uid-msg" class="form__status is-alert">
							<i class="fas fa-times-circle">已有人使用!</i>
							</div>`);
				}
				
				if(item.emailCheck){
					$('#email').closest('div.form-group').append(`
							<div id="err-msg" class="form__status is-warning">
							<i class="fas fa-exclamation-circle">此信箱已註冊</i>
							</div>`);
				}
				
			});
		},
		error: function(){alert("AJAX-發生錯誤囉!檢查帳號/email是否有被註冊過")}
	});
});
</script>

</html>