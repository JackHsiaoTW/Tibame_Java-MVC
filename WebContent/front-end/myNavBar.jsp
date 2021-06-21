<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO"/> --%>
<jsp:useBean id="notifySvc" scope="request" class="com.notify.model.NotifyService"/>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>myNavBar.jsp</title>

     <!-- Custom fonts for this template-->
<%--     <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css"> --%>
<!--     <link -->
<!--         href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" -->
<!--         rel="stylesheet"> -->

    <!-- Custom styles for this template-->
<%--     <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css"> --%>
<%--     <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css"> --%>

    <!-- Bootstrap core JavaScript-->
<%--     <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script> --%>
    <!-- Core plugin JavaScript-->
<%--     <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> --%>

    <!-- Custom scripts for all pages-->
<%--     <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script> --%>
<%--     <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script> --%>
<!--     <style> -->
        
<!--     </style> -->

</head>

<body>
    <!-- Topbar -->
        <div class="navbar navbar-expand navbar-light bg-white topbar fixed-top static-top shadow d-flex"
        style="height:70px">
<!--         Nav Item - logo -->
        <div class=" logo">
        <a href="<%=request.getContextPath()%>/front-end/index.jsp">
            <img src="<%=request.getContextPath()%>/resources/img/logo.PNG" style="height:70px">
        </a>
        </div>
<!--         Topbar Navbar -->
        <ul class="navbar-nav ml-auto">
            <li class="burger">
                <button class="hamburger hamburger--spring" type="button">
                    <span class="hamburger-box">
                        <span class="hamburger-inner"></span>
                    </span>
                </button>

            </li>
            <li class="top-button">
                <a href="#">
                    <i class="fas fa-store"></i>
                    <span class="text">商城</span>
                </a>
            </li>
            <li class="top-button">
                <a href="<%=request.getContextPath()%>/front-end/exper_index/exper_search_index.jsp">
                    <i class="fas fa-map-pin"></i>
                    <span class="text">體驗</span>
                </a>
            </li>
            <li class="top-button">
                <a href="#">
                    <i class="fas fa-align-center"></i>
                    <span class="text">文章</span>
                </a>
            </li>
            <li class="top-button">
                <a href="<%=request.getContextPath()%>/front-end/itinery/listMemberAllItine.jsp">
                    <i class="fas fa-edit"></i>
                    <span class="text">行程</span>
                </a>
            </li>
<!--             Nav Item - QA -->
            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-question fa-lg"></i>
                </a>
            </li>
<!--             Nav Item - Cart -->
            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-shopping-cart fa-lg"></i>
<!--                     Counter - Alerts -->
                    <span class="badge badge-danger badge-counter">3+</span>
                </a>
            </li>

<!--             Nav Item - Alerts -->
            <li class="nav-item dropdown no-arrow mx-1">
                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-bell fa-fw fa-lg"></i>
<!--                     Counter - Alerts -->
                    <span class="badge badge-danger badge-counter" id="counterAlerts">${countAlert}</span>
                </a>
<!--                 Dropdown - Alerts -->
                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in dropdownAlertlist"
					aria-labelledby="alertsDropdown">
					<h6 class="dropdown-header">Alerts Center</h6>
					
					<!-- 動態產生通知欄位,只顯示5個 -->
					<c:if test="${memberVO.memberNo != null}">
						<c:forEach var="notifyPer" items="${notifySvc.getOneNotifyByPerson(memberVO.memberNo)}" end="4" step="1">
							<a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/notify/notifyServlet.do?notifyNo=${notifyPer.notifyNo}&action=listOneNotify">
								<div class="mr-3">
									<div class="icon-circle bg-primary">
										<i class="fas fa-file-alt text-white"></i>
									</div>
								</div>
								<div>
									<div class="small text-gray-500"><fmt:formatDate value="${notifyPer.notifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
									<span class="font-weight-bold">${notifyPer.notifyContent}</span>
								</div>
							</a> 
					</c:forEach>
					</c:if>
					
					<a class="dropdown-item text-center small text-gray-500" href="<%=request.getContextPath()%>/front-end/notify/NotifyAll.jsp">Show All Alerts</a>
				</div>
            </li>

            <div class="topbar-divider d-none d-sm-block"></div>

<!--             Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    <span class="mr-2 d-none d-lg-inline text-gray-600 medium">${memberVO.name}</span>
                    <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/GetPicture?id=${memberVO.memberNo }">
                </a>
<!--                 Dropdown - User Information -->
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                    
                    
                    <c:choose>
						<c:when test="${empty memberVO.account}">
							<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/Login.jsp"> 
								<i class="fas fa-sign-in-alt fa-md fa-fw mr-2 text-gray-400"></i> 登入
							</a>
							<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/member/Register.jsp"> 
								<i class="fas fa-user-plus fa-md fa-fw mr-2 text-gray-400"></i> 註冊
							</a>
						</c:when>
						<c:otherwise>
							<a class="dropdown-item" href="#"> 
								<i class="fas fa-gifts fa-md fa-fw mr-2 text-gray-400"></i> 商品
							</a> 						
							<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/exper_report/addExperReport.jsp"> 
								<i class="fas fa-running fa-md fa-fw mr-2 text-gray-400"></i> 檢舉體驗
							</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/back-end/exper_back_index.jsp"> 
								<i class="fas fa-running fa-md fa-fw mr-2 text-gray-400"></i> 達人專區
							</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/friendList/allFriendInfo.jsp"> 
								<i class="fas fa-user fa-md fa-fw mr-2 text-gray-400"></i> 好友
							</a> 
							<a class="dropdown-item" href="#"> 
								<i class="fas fa-envelope fa-md fa-fw mr-2 text-gray-400"></i> 訊息
							</a> 
							<a class="dropdown-item" href="<%=request.getContextPath()%>/member/member.do?action=getOne_For_Update"> 
								<i class="fas fa-cog fa-md fa-fw mr-2 text-gray-400"></i> 帳號
							</a>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal"> 
								<i class="fas fa-sign-out-alt fa-md fa-fw mr-2 text-gray-400"></i> 登出
							</a>
						</c:otherwise>
					</c:choose>
                    
                    
                </div>
            </li>
        </ul>
    </div>
<!--     media query -->
    <div class="nav-bar second-nav-bar fixed-top">
        <div class="top-button">
            <a href="#">
                <i class="fas fa-store"></i>
                <span class="text">商城</span>
            </a>
        </div>
        <div class="top-button">
            <a href="#">
                <i class="fas fa-map-pin"></i>
                <span class="text">體驗</span>
            </a>
        </div>
        <div class="top-button">
            <a href="#">
                <i class="fas fa-align-center"></i>
                <span class="text">文章</span>
            </a>
        </div>
        <div class="top-button">
            <a href="<%=request.getContextPath()%>/front-end/itinery/listMemberAllItine.jsp">
                <i class="fas fa-edit"></i>
                <span class="text">行程</span>
            </a>
        </div>
    </div>
<!--     Logout Modal -->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">確定離開?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">選擇確定如果你真的要登出您的帳戶
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">取消</button>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/member/loginHandler.do?action=logout">確定</a>
                </div>
            </div>
        </div>
    </div>
    
    <!-- listOneNotify Modal -->
	<c:if test="${openListOneNotifyModal!=null}">
		<div class="modal fade" id="listOneModal" tabindex="-1" role="dialog"
			aria-labelledby="listOneModal" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">listOneNotify</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<jsp:include page="/front-end/notify/listOneNotify.jsp" />
					</div>
				</div>
			</div>
		</div>

		<script>
			$("#listOneModal").modal({show: true});
		</script>
 	</c:if>
    <!-- End of Topbar -->

	<!-- CounterAlerts JS -->
	<%@ include file="/front-end/file/counterAlertsJS.file" %>
	
	<c:if test="${memberVO.memberNo != null}">
		<%@ include file="/front-end/file/notifyWebSocket.file" %>
	</c:if>

</body>

</html>