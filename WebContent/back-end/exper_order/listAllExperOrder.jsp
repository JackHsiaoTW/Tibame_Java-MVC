<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exper_order.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ExperOrderService expordSvc = new ExperOrderService();
	List<ExperOrderVO> list = expordSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有體驗梯次資料 - listAllExperOrder.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有體驗梯次資料 - listAllExperOrder.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-sell-end/exper_order/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>


	<table>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<tr>
			<th>體驗梯次編號</th>
			<th>體驗編號</th>
			<th>體驗報名開始時間</th>
			<th>體驗報名結束時間</th>
			<th>體驗開始時間</th>
			<th>體驗結束時間</th>
			<th>體驗人數下限</th>
			<th>體驗人數上限</th>
			<th>體驗當期售價</th>
			<th>體驗梯次狀態</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="expordVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${expordVO.exper_order_no}</td>
				<td>${expordVO.exper_no}</td>
				<td>${expordVO.apply_start}</td>
				<td>${expordVO.apply_end}</td>
				<td>${expordVO.exper_order_start}</td>
				<td>${expordVO.exper_order_end}</td>
				<td>${expordVO.exper_max_limit}</td>
				<td>${expordVO.exper_min_limit}</td>
				<td>${expordVO.exper_now_price}</td>
				<td>${expordVO.exper_order_status}</td>



			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>