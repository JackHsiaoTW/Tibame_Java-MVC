<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.experience.model.*"%>

<%
	ExperienceVO experVO = (ExperienceVO) request.getAttribute("experVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>新增達人體驗</title>
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
</head>
<style>
.portfolio-block {
padding-top:70px;
}

.h1, h1 {
padding-top:20px;
}

</style>

<body>
<%@include file="/back-end/experience/myNavBar_exper.jsp"%>

	<main class="page contact-page">
		<section class="portfolio-block contact">
		<jsp:include page="/back-end/experience/experSideBar.jsp" />
			<div class="container">
				<div class="heading">
					<h1>新增達人體驗</h1>
				</div>
				<form class="form-horizontal" method="POST"
					action="<%=request.getContextPath()%>/Experience/Experience.do">
					<div>
						<c:if test="${not empty errorMsgs}">
							<div class="container">
								<div class="alert alert-danger" role="alert">
									<strong>新增失敗，請修正以下錯誤:</strong>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li>${message}
										</c:forEach>
									</ul>
								</div>
							</div>
						</c:if>
					</div>
					<jsp:useBean id="extypeSvc" scope="page"
						class="com.exper_type.model.ExperTypeService" />
					<div class="form-group">
						<label for="subject">體驗種類:</label><select class="form-control"
							id="exper_type_no" name="exper_type_no">
							<c:forEach var="extypeVO" items="${extypeSvc.all}">
								<option value="${extypeVO.exper_type_no}">
									${extypeVO.exper_type_name }
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="exper_type_name">體驗名稱：</label><input
							class="form-control item" type="text" id="name" name="name"
							value="${experVO.name}">
					</div>
					<div class="form-group">
						<label for="exper_type_name">體驗定價：</label><input
							class="form-control item" type="text" id="price" name="price"
							value="${experVO.price}">
					</div>
					<div class="form-group">
						<label for="exper_type_name">體驗敘述：</label>
						<textarea class="form-control item" type="text" id="exper_descr"
							name="exper_descr" value="${experVO.exper_descr}"></textarea>
					</div>

					<div class="form-group">
						<input type="hidden" name="host_no" value="${memberVO.memberNo}">
						<input type="hidden" name="action" value="insert">
						<button class="btn btn-primary btn-block btn-lg" type="submit">送出新增</button>
					</div>
				</form>
			</div>
		</section>
	</main>
</body>
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script> --%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/theme.js"></script>

</html>