<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.exper_type.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ExperTypeService extypeSvc = new ExperTypeService();
	List<ExperTypeVO> list = extypeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有體驗種類資料 - listAllExperType.jsp</title>

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
<body bgcolor='lightskyblue'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有體驗種類資料 - listAllExperType.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-sell-end/exper_type/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>體驗種類編號</th>
			<th>體驗種類名稱</th>
			<th>修改</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="extypeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${extypeVO.exper_type_no}</td>
				<td>${extypeVO.exper_type_name}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/ExperType/ExperType.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="exper_type_no" value="${extypeVO.exper_type_no}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>