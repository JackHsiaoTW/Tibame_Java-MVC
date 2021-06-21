<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.experience.model.*"%>
<%@ page import="com.exper_report.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	ExperReportVO erVO = (ExperReportVO) request.getAttribute("erVO");
%>
<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO" />


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<title>檢舉達人體驗</title>
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
<body>




	<%@include file="/back-end/experience/myNavBar_exper.jsp"%>

	<main class="page contact-page">
		<section class="portfolio-block contact">
			<div class="container">
				<div class="heading">
					<h1>檢舉達人體驗</h1>
					</div>
					<form class="form-horizontal" method="POST"
						action="<%=request.getContextPath()%>/ExperReport/ExperReport.do">
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
						<div class="form-group">
							<label for="reporter_no">檢舉人ID:</label>
								<input type="text" class="form-control item" id="reporter_no"
									name="reporter_no" value="${memberVO.memberNo}" />
						</div>

						<div class="form-group">
							<label for="price">體驗編號:</label>
								<input type="text" class="form-control item" id="reported_exper_no"
									name="reported_exper_no" value="${erVO.reported_exper_no}1" />
						</div>
						<div class="form-group">
							<label class for="reason">檢舉理由:</label>
								<input type="text" class="form-control item" id="reason"
									name="reason" value="${erVO.reason}" />
						</div>

						<div class="form-group">
								<input type="hidden" name="action" value="insert">
								<button type="submit" class="btn btn-primary btn-block btn-lg">送出檢舉</button>
							</div>
				</form>
			</div>
		</section>
	</main>
</body>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/theme.js"></script>
</body>



</html>