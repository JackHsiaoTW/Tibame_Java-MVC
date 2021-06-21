<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itineItem.model.*"%>
<%@ page import="com.member.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	Integer itineNo = (Integer) session.getAttribute("itineNo");
	Integer memberNo = memberVO.getMemberNo();
	

	ItineItemService itineItemSvc = new ItineItemService();
	List<ItineItemVO> list = itineItemSvc.findByItineNoAndManager(itineNo,memberNo);
	

	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>該行程所有自己負責行程明細 - listItineManageItineItem.jsp</title>

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
				<h3>該行程所有自己負責行程明細 - listItineManageItineItem.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img
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
	<form action="listItineManageItineItem.jsp" >
	<button >自己負責項目</button>
	</form>
	<form action="listItineAllItineItem.jsp" >
	<button >團體負責項目</button>
	</form>
	<table>
		<tr>
			<th>景點編號</th>
			<th>開始時間</th>
			<th>結束時間</th>
			<th>備註</th>
			<th>修改行程明細</th>
			<th>負責人</th>
			<th>是否完成</th>
			<th>預計完成日</th>
			<th>分工備註</th>
			<th>修改分工</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
		<c:forEach var="itineItemVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${itineItemVO.attraNo}</td>
				<td>${itineItemVO.startTime}</td>
				<td>${itineItemVO.endTime}</td>
				<td>${itineItemVO.note}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改行程明細"> <input type="hidden"
							name="empno" value="${empVO.empno}"> <input type="hidden"
							name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<!-- 目前尚未用到  -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>${memberSvc.getOneMember(itineItemVO.manager).name}</td>
				<td>${itineItemVO.isDone==false?"還沒":"OK"}</td>
				<td>${itineItemVO.finishDate}</td>
				<td>${itineItemVO.taskNote}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改分工"> <input type="hidden"
							name="empno" value="${empVO.empno}"> <input type="hidden"
							name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller-->
						<!-- 目前尚未用到  -->
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="empno" value="${empVO.empno}"> <input type="hidden"
							name="requestURL" value="<%=request.getServletPath()%>">
						<!--送出本網頁的路徑給Controller 這樣C才能再帶你回來-->
						<input type="hidden" name="action" value="delete">
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
	</b>

</body>
</html>