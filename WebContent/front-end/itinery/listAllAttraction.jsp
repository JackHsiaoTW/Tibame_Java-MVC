<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>

<%
	AttractionService attraSvc = new AttractionService();
	List<AttractionVO> list = attraSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>listAllAttraction.jsp</title>

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/resources/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/nav-bar.css">
<link
	href="<%=request.getContextPath()%>/resources/css/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->

<script
	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
<!-- Page level plugins -->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.dataTables.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script
	src="<%=request.getContextPath()%>/resources/js/chungyuDataTable.js"></script>


<style>
</style>

</head>

<body>


	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">所有景點:</h6>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-bordered table-striped"
						id="dataTableAllAttraction" width="100%" cellspacing="0">
						<thead>
							<tr>
								<th>景點編號</th>
								<th>分類</th>
								<th>景點名稱</th>
								<th>描述</th>
								<th>地點</th>
								<th>在架狀態</th>
								<th>圖片</th>
								<th>修改</th>
								<th>下架</th>
							</tr>
						</thead>
						
						<tbody>
<%-- 							<c:forEach var="attraVO" items="${list}"> --%>
							<%@ include file="page1.file"%>
							<c:forEach var="attraVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">

								<tr>
									<td>${attraVO.attraNo}</td>
									<td>${attraVO.sort}</td>
									<td>${attraVO.attraName}</td>
									<td>${attraVO.descr}</td>
									<td>${attraVO.location}</td>
									<td>${attraVO.isOnShelf}</td>
									<td><c:if
											test="${attraVO.attraPic1!=null&&attraVO.attraPic1!=''}">
											<img src="${attraVO.attraPic1}"
												style="width: 300px; height: 200px;">
										</c:if></td>
									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/front-end/itinery/attraction.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="修改" class="btn btn-primary">
											<input type="hidden" name="attraNo"
												value="${attraVO.attraNo}"> <input type="hidden"
												name="action" value="getOne_For_Update">
										</FORM>
									</td>
									<td>
										<FORM METHOD="get"
											ACTION="<%=request.getContextPath()%>/front-end/itinery/attraction.do"
											style="margin-bottom: 0px;">
											<input type="submit" value="下架" class="btn btn-primary"
												onclick="return(confirm('確認要下架嗎？'))"> <input
												type="hidden" name="attraNo" value="${attraVO.attraNo}">
											<input type="hidden" name="action" value="notAvailable">
											<input type="hidden" name="requestURL"
												value="<%=request.getServletPath()%>">
										</FORM>
									</td>

								</tr>
							</c:forEach>

						</tbody>
					</table>
					<%@ include file="page2.file"%>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</body>

</html>
















