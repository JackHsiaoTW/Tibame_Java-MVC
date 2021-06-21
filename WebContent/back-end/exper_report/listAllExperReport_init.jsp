<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exper_report.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ExperReportService erSvc = new ExperReportService();
	List<ExperReportVO> list = erSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="experSvc" scope="page"
	class="com.experience.model.ExperienceService" />


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
<title>體驗檢舉總表</title>

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

	<div id="viewport">
		<div id="content">
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
											<th>體驗檢舉單編號</th>
											<th>檢舉人ID</th>
											<th>被檢舉體驗名稱</th>
											<th>檢舉理由</th>
											<th>檢舉時間</th>
											<th>回應內容</th>
											<th>回應時間</th>
											<th>檢舉處理狀態</th>
											<th>執行審查</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="erVO" items="${list}">
											<tr style="line-height: 25px; text-align: center;">
												<td>${erVO.report_no}</td>
												<td>${erVO.reporter_no}</td>
												<td>${experSvc.getOneExperience(erVO.reported_exper_no).name}</td>
												<td>${erVO.reason}</td>
												<td>${erVO.report_time}</td>
												<td>${erVO.reply_content}</td>
												<td>${erVO.reply_time}</td>
												<c:choose>
													<c:when test="${erVO.is_checked == '0'}">
														<td>尚未處理</td>
													</c:when>
													<c:when test="${erVO.is_checked == '1'}">
														<td>檢舉已成立</td>
													</c:when>
													<c:when test="${erVO.is_checked == '2'}">
														<td>檢舉未成立</td>
													</c:when>
													<c:otherwise>
														<td>其他狀態</td>
													</c:otherwise>
												</c:choose>

												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/ExperReport/ExperReport.do">
												<class="btn_group" style="width: 100%;"><input
													type="submit" value="執行審查"> <input type="hidden"
													name="report_no" value="${erVO.report_no}"> <input
													type="hidden" name="action" value="getOne_For_Update">
													</FORM>
											</tr>
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
</html>