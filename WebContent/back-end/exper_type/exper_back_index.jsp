<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.exper_type.model.*"%>

<%
	ExperTypeVO extypeVO = (ExperTypeVO) request.getAttribute("extypeVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>新增體驗種類</title>
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
<style>
.portfolio-block {
padding-top:70px;
}

.h1, h1 {
padding-top:20px;
}
</style>

</head>
<body>
	<%@include file="/back-end/experience/myNavBar_exper.jsp"%>

	<main class="page contact-page">
		<section class="portfolio-block contact">
				<jsp:include page="/back-end/experience/experSideBar.jsp" />

		</section>
	</main>

</body>
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/theme.js"></script>

</html>