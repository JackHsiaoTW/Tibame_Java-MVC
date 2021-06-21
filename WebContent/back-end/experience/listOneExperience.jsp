<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.experience.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content=""> -->
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

<title>達人體驗管理</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/experSideBar.css">
<style>
.portfolio-block {
padding-top:70px;
}

.h1, h1 {
padding-top:20px;
}
</style>


<%-- <%@ include file="/back-end/file/backEndCSS.file" %> --%>
<%-- <%@ include file="/back-end/file/navbar.file" %> --%>
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
.btn-light {
    color: #fff;
    background-color: #1E90FF;
    border-color: #1E90FF;
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
.btn-success {
    color: #fff;
    background-color: #1E90FF;
    border-color: #1E90FF;
}

.btn-success {
    color: #fff;
    background-color: #1E90FF;
    border-color: #1E90FF;
}
.table {
	font-size: 20px;
}
.list-photo{
height:50px;
width:50px;}
.main {
    grid-area: main;
    padding: 75px;
}


div.side-bar {
    background-color: #009ab9;
    position: fixed;
    height: 100vh;
    padding-top: 70px;
}

 div.container-fluid {
    padding-left: 0;
    padding-right: 1.5rem;
}
</style>    
</head>
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
											<th>圖片預覽</th>
											<th>體驗種類</th>
											<th>體驗名稱</th>
											<th>體驗價格</th>
											<th>體驗敘述</th>
											<th style="text-align: center; width: 100px;"><button
													type="button" data-func="dt-add"
													class="btn btn-success btn-xs dt-add"
													onclick="location.href='<%=request.getContextPath()%>/back-end/experience/addExperience.jsp'">
													新增達人體驗</button></th>
										</tr>
									</thead>
									<tbody>
										<jsp:useBean id="experSvc" scope="page"
											class="com.experience.model.ExperienceService" />
										<jsp:useBean id="extypeSvc" scope="page"
											class="com.exper_type.model.ExperTypeService" />
										<c:forEach var="experVO"
											items="${experSvc.getAllbyHostNo(memberVO.memberNo)}">
											<tr style="line-height: 25px; text-align: center;">
											<td><img class="list-photo"src="<%=request.getContextPath()%>
											/ExperPhoto/ExperPhoto.do?exper_no=
											${experVO.exper_no}&action=getListExperPhoByExperNo"></td>
												<td>${extypeSvc.getOneExperType(experVO.exper_type_no).exper_type_name}</td>
												<td>${experVO.name}</td>
												<td>${experVO.price}</td>
												<td class="exper_content" style="max-width: 100px;">${experVO.exper_descr}</td>
												<td class="btn_group" style="width: 100%;">
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/Experience/Experience.do">
														<button type="submit"
															class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">修改體驗</button>
														<input type="hidden" name="exper_no"
															value="${experVO.exper_no}"> <input type="hidden"
															name="action" value="getOne_For_Update">
													</form>
													
													
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do">
														<button type="submit"
															class="btn btn-light btn-xs dt-edit"
															style="margin-right: 16px;">圖片管理</button>
														<input type="hidden" name="exper_no"
															value="${experVO.exper_no}"> <input type="hidden"
															name="action" value="get_Photo_By_Exper_no">
													</form>
													
													
													<form METHOD="post"
														ACTION="<%=request.getContextPath()%>/ExperOrder/ExperOrder.do">
														<button type="submit"
														class="btn btn-light btn-xs dt-delete">設定梯次</button>
														<input type="hidden" name="exper_no"
															value="${experVO.exper_no}"> <input type="hidden"
															name="action" value="getOne_For_Insert">
													</form>
													
													
												</td>
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