<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.exper_report.model.*"%>

<%
	ExperReportVO erVO = (ExperReportVO) request.getAttribute("erVO");
%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>審查體驗檢舉</title>
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/resources/assets/bootstrap/css/bootstrap.min.css"> --%>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://fonts.googleapis.com/css?family=Lato:300,400,700"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css"> -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/resources/assets/css/untitled.css"> --%>


</head>
<body>


	<main class="page contact-page">
		<section class="portfolio-block contact">
			<div class="container">
				<div class="heading">
					<h1>審查體驗檢舉</h1>
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
						<label for="reason">檢舉理由:</label> <input type="text"
							class="form-control item" id="reason" name="reason"
							value="${erVO.reason}" />

					</div>
					<div class="form-group">
						<label for="reply_content">回覆內容:</label> <input type="text"
							class="form-control item" id="reply_content" name="reply_content"
							value="${erVO.reply_content}" />
					</div>
					<div class="form-group">
						<td>檢舉狀態:</td>
						<td><input type="radio" name="is_checked" value="1">檢舉已成立
							<input type="radio" name="is_checked" value="2">檢舉未成立</td>
					</div>



			<div class="form-group">

				<input type="hidden" name="reporter_no" value="${erVO.reporter_no}">
				<input type="hidden" name="reported_exper_no"
					value="${erVO.reported_exper_no}"> <input type="hidden"
					name="report_no" value="${erVO.report_no}"> <input
					type="hidden" name="action" value="update">


				<button class="btn btn-primary btn-block btn-lg" type="submit">送出審查</button>
			</div>
			</form>
			</div>
		</section>
	</main>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/resources/assets/js/jquery.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/resources/assets/bootstrap/js/bootstrap.min.js"></script> --%>
<!-- 	<script -->
<!-- 		src="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"></script> -->
<!-- 	<script -->
<%-- 		src="<%=request.getContextPath()%>/resources/assets/js/theme.js"></script> --%>
</body>



</html>