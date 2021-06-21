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


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

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
				<h3>所有景點資料 - listAllAttraction.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/select_page.jsp"><img
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
						<input type="hidden" name="attraNo" value="${attraVO.attraNo}">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="get"
						ACTION="<%=request.getContextPath()%>/front-end/itinery/attraction.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="下架" class="btn btn-primary"
							onclick="return(confirm('確認要下架嗎？'))"> <input
							type="hidden" name="attraNo" value="${attraVO.attraNo}">
						<input type="hidden" name="action" value="notAvailable"> <input
							type="hidden" name="requestURL"
							value="<%=request.getServletPath()%>">
					</FORM>
				</td>

			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

	<br>本網頁的路徑:
	<br>
	<b> <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
		<font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%>
		</br> <font color=blue>request.getQueryString(): </font> <%=request.getQueryString()%>
		</br> <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%>
		</br>
</body>
</html>