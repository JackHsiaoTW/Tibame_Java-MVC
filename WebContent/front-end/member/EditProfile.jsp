<%@page import="com.member.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO"/>
<%-- --${memberVO }--memberVO--${memberVO.name} --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
     <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
<style>
div#preview>img{
	width:100px; 
	height:100px
}
</style>
</head>

<body>
	<c:import url="/front-end/myNavBar.jsp"></c:import>
	
	<div class="container">
        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">

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
                                <h1 class="h4 text-gray-900 mb-4">Edit Profile</h1>
                            </div>
                            <form class="user" METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" enctype="multipart/form-data">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="account" name="account"
                                        placeholder="Account" value="<%=memberVO==null ? "" : memberVO.getAccount() %>" readonly required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="password" class="form-control form-control-user"
                                            id="password" name="password" placeholder="Password" value="<%=memberVO==null ? "" : memberVO.getPassword() %>" required>
                                    </div>
<!--                                     <div class="col-sm-6"> -->
<!--                                         <input type="password" class="form-control form-control-user" -->
<!--                                             id="repeatPassword" placeholder="Repeat Password" required> -->
<!--                                     </div> -->
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
                                        placeholder="Email Address" value="<%=memberVO==null ? "" : memberVO.getEmail() %>" readonly required>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-6 mb-3 mb-sm-0" id="preview">
                                    	<img src="<%=memberVO==null ? "" : request.getContextPath()+"/GetPicture?id="+memberVO.getMemberNo() %>">
                                    </div>
                                    <input type="file" class="form-control form-control-user" id="memberPic" name="memberPic" value="<%=memberVO==null ? "" : memberVO.getMemberPic() %>">
                                </div>
                                <%-- <a href="login.html" class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </a> --%>
                                <input type="hidden" name="memberNo" value="<%= memberVO.getMemberNo()%>">
                                <input type="hidden" name="action" value="updateMem">
                                <input type="submit" class="btn btn-primary btn-user btn-block" value="send">
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<script src="<%=request.getContextPath()%>/resources/js/register.js"></script>
</body>
</html>