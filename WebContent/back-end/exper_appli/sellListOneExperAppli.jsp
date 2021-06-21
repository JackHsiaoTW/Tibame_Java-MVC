<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.experience.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.exper_order.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/untitled.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/experSideBar.css">
<title>達人體驗訂單管理</title>
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
	padding: 30px;
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
<style>
.container-fluid {
padding-top:70px;
}

.h1, h1 {
padding-top:20px;
}
div.container-fluid {
    padding-left: 0;
    padding-right: 1.5rem;
}
.main {
    grid-area: main;
    padding: 20px;
    padding-left: 100px;
}

</style>
</head>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color: red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color: red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
<%@include file="/back-end/experience/myNavBar_exper.jsp"%>
	<div id="viewport">
		<div id="content">
			<div class="container-fluid">
			<jsp:include page="/back-end/experience/experSideBar.jsp" />
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
											<th>體驗名稱</th>
											<th>體驗報名編號</th>
											<th>下單會員</th>
											<th>體驗報名截止日</th>
											<th>體驗開始時間</th>
											<th>價格</th>
											<th>體驗訂單狀態</th>
											<th>體驗訂單付款狀態</th>
											<th>訂單備註</th>
											<th>訂單確認</th>
										</tr>
									</thead>
									<tbody>
										<jsp:useBean id="memberSvc" scope="page"
											class="com.member.model.MemberService" />
										<jsp:useBean id="expordSvc" scope="page"
											class="com.exper_order.model.ExperOrderService" />
										<jsp:useBean id="experSvc" scope="page"
											class="com.experience.model.ExperienceService" />
										<jsp:useBean id="expapSvc" scope="page"
											class="com.exper_application.model.ExperApplicationService" />
										<c:forEach var="experVO"
											items="${experSvc.getAllbyHostNo(memberVO.memberNo)}">
											<c:forEach var="expordVO"
												items="${expordSvc.getAllExperPerByExperNo(experVO.exper_no)}">
												<c:forEach var="expapVO"
													items="${expapSvc.getExperAppliByExperOrderrNo(expordVO.exper_order_no)}">
													<tr style="line-height: 25px; text-align: center;">
														<td>${experVO.name}</td>
														<td>${expapVO.exper_appli_no}</td>
														<td>${memberVO.memberNo}</td>
														<td><fmt:formatDate value="${expordVO.apply_end}"
																pattern="yyyy-MM-dd" /></td>
														<td><fmt:formatDate
																value="${expordVO.exper_order_start}"
																pattern="yyyy-MM-dd HH:mm" /></td>
														<td>${expapVO.sum}</td>
														<td><c:choose>
																<c:when test="${expapVO.exper_appli_status==0}">
                                                                    已確認出發
                                                                </c:when>
																<c:when test="${expapVO.exper_appli_status==1}">
                                                                    取消報名
                                                                </c:when>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${expapVO.exper_payment_status==0}">
                                                                    尚未付款
                                                                </c:when>
																<c:when test="${expapVO.exper_payment_status==1}">
                                                                   待確認是否付款成功
                                                                </c:when>
																<c:when test="${expapVO.exper_payment_status==2}">
                                                                    退款中
                                                                </c:when>
																<c:when test="${expapVO.exper_payment_status==3}">
                                                                    已完成退款
                                                                </c:when>
																<c:when test="${expapVO.exper_payment_status==4}">
                                                                    已確認付款
                                                                </c:when>
															</c:choose></td>
														<td>${expapVO.exper_appli_memo}</td>
														<c:choose>
															<c:when test="${expapVO.exper_payment_status==2}">
																<form METHOD="post"
																	ACTION="<%=request.getContextPath()%>/ExperApplication/ExperApplication.do">
																	<td class="btn_group" style="width: 100%;">
																		<button type="submit"
																			class="btn btn-light btn-xs dt-edit"
																			style="margin-right: 16px;">退款</button> <input
																		type="hidden" name="exper_appli_no"
																		value="${expapVO.exper_appli_no}"> <input
																		type="hidden" name="action"
																		value="hostnoConfirmRefund">
																	</td>
																</form>
															</c:when>

														</c:choose>

														<c:choose>
															<c:when test="${expapVO.exper_payment_status==1}">
																<form METHOD="post"
																	ACTION="<%=request.getContextPath()%>/ExperApplication/ExperApplication.do">
																	<td class="btn_group" style="width: 100%;">
																		<button type="submit"
																			class="btn btn-light btn-xs dt-edit"
																			style="margin-right: 16px;">確認付款</button> <input
																		type="hidden" name="exper_appli_no"
																		value="${expapVO.exper_appli_no}"> <input
																		type="hidden" name="action" value="hostnoConfirmPay">
																	</td>
																</form>
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>
													</tr>
												</c:forEach>
											</c:forEach>
										</c:forEach>
									</tbody>
								</table>
							</main>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/theme.js"></script>

</html>