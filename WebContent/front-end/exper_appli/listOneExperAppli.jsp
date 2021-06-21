<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.experience.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.exper_application.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	
%>
<html>

<head>
<link rel="stylesheet" type="text/css"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/buttons/1.2.2/css/buttons.bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- <script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> -->
<!--      Custom fonts for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!--     Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

<!--     Bootstrap core JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<!--     Core plugin JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

<!--     Custom scripts for all pages -->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
<title>達人體驗單管理</title>
<style type="text/css">
.sidenav {
	grid-area: sidenav;
	background-color: #394263;
	top: 0;
	left: 0;
	background-color: #111;
	opacity: .9;
}

.grid-container {
	display: flex;
}

.main {
	grid-area: main;
	padding: 100px;
}

.footer {
	grid-area: footer;
	background-color: #648ca6;
}

.sidebar-menu {
	
}

.treeview {
	border-bottom: 1px solid;
	padding: 5px;
	text-align: left;
	text-decoration: none;
}

.sidebar-menu a {
	display: block;
	padding: 20px 30px;
	font-size: 14px;
	text-decoration: none;
	color: #ccc;
}

.treeview:hover {
	color: #fff;
	transition: 0.4s;
}

.slide a {
	color: #000;
	font-size: 36px;
}

#content {
	overflow: hidden;
}

example #contetn {
	align-items: center;
	text-align: center;
}

.btn_group {
	width: 120px;
	align-items: center;
	display: flex;
	line-height: 100px;
}

.act_content {
	max-width: 500px;
	max-height: 68px;
	text-overflow: ellipsis;
	display: block;
	overflow: hidden;
	border: 0;
	white-space: nowrap;
}

.table {
	font-size: 5px;
}

.list-photo {
	height: 50px;
	width: 50px;
}

.btn-light {
	color: #fff;
	background-color: #1E90FF;
	border-color: #1E90FF;
}
</style>
</head>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">訂單下訂失敗:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
<%@include file="/front-end/myNavBar.jsp"%>
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col calendarCol">
					<main class="main">
						<table id="example"
							class="table table-striped table-bordered table-hover"
							cellspacing="0" width="100%" style="text-align: center;">
							<thead>
								<tr class="warning"
									style="line-height: 25px; text-align: center;">
									<th>體驗報名單編號</th>
									<th>體驗梯次編號</th>
									<th>體驗報名單總金額</th>
									<th>體驗報名訂單狀態</th>
									<th>付款狀態</th>
									<th>體驗備註</th>
									<th>付款/取消報名</th>
								</tr>
							</thead>
							<tbody>
								<jsp:useBean id="expapSvc" scope="page"
									class="com.exper_application.model.ExperApplicationService" />
								<c:forEach var="expapVO"
									items="${expapSvc.getExpAppliByMemberNo(memberVO.memberNo)}">
									<tr style="line-height: 25px; text-align: center;">
										<td>${expapVO.exper_appli_no}</td>
										<td>${expapVO.exper_order_no}</td>
										<td>${expapVO.sum}</td>
										<td><c:choose>
												<c:when test="${expapVO.exper_appli_status==0}">
                                                    已確認出發
                                                </c:when>
												<c:when test="${expapVO.exper_appli_status==1}">
                                                    已取消報名
                                                </c:when>
												<c:otherwise>
                                                    其他狀態
                                                </c:otherwise>
											</c:choose></td>
										<td><c:choose>
												<c:when test="${expapVO.exper_payment_status==0}">
                                                    未付款
                                                </c:when>
												<c:when test="${expapVO.exper_payment_status==1}">
                                                    已寄送付款相關資訊
                                                </c:when>
												<c:when test="${expapVO.exper_payment_status==2}">
                                                    退款確認中
                                                </c:when>
												<c:when test="${expapVO.exper_payment_status==3}">
                                                    已退款
                                                </c:when>
												<c:otherwise>
                                                    其他狀態
                                                </c:otherwise>
											</c:choose></td>
										<td>${expapVO.exper_appli_memo}</td>
										<td class="btn_group"
											style="width: 100%; justify-content: center;"><c:choose>
												<c:when test="${expapVO.exper_payment_status==0}">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ExperApplication/ExperApplication.do">
														<button type="submit" class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">付款</button>
														<input type="hidden" name="exper_appli_no"
															value="${expapVO.exper_appli_no}"> <input
															type="hidden" name="action" value="memPayTheBill">
													</form>
												</c:when>

											</c:choose> <c:choose>
												<c:when
													test="${(expapVO.exper_payment_status!=2)&&(expapVO.exper_payment_status!=3) }">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ExperApplication/ExperApplication.do">
														<button type="submit" class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">取消訂單</button>
														<input type="hidden" name="exper_appli_no"
															value="${expapVO.exper_appli_no}"> <input
															type="hidden" name="action" value="memCancelExperAppli">
													</form>
												</c:when>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</main>
				</div>
			</div>
		</div>
	</div>
</body>

</html>