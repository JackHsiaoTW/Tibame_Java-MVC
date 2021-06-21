<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itineItem.model.*"%>
<%@ page import="com.itine.model.*"%>

<%

	Integer itineNo = (Integer)session.getAttribute("itineNo");
	ItineItemVO itineItemVO = (ItineItemVO)request.getAttribute("itineItemVO");
	System.out.println(itineItemVO);
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增行程明細 - addItineItem.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>新增行程明細 - addItineItem.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增行程:</h3>
${itineItemVO }---${itineItemVO.attraNo }---${itineItemVO.startTime }---${itineItemVO.finishDate }---${itineItemVO.isDone }
<%-- 錯誤表列 --%>
<c:if test="${! empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="itineItem.do" name="form1">
<table>
	<tr>
		<td>景點名稱:<font color=red><b>*</b></font></td>
		<td>
			<select name="attraNo" value="">
			<jsp:useBean id="attraSvc" scope="page" class="com.attraction.model.AttractionService"/>
			<c:forEach var="attractionVO" items="${attraSvc.all}">
			<option value="${attractionVO.attraNo }" ${itineItemVO.attraNo==attractionVO.attraNo?'selected':'' }>${attractionVO.attraName}</option>
			</c:forEach>
			</select>
		</td>
		
	</tr>

	<tr>
		<td>開始時間:</td>
		<td><input type="datetime-local" name="startTime" value="${itineItemVO.startTime }"></td>
	</tr>
	<tr>
		<td>結束時間:</td>
		<td><input type="datetime-local" name="endTime" value="${itineItemVO==null?'':'itineItemVO.endTime' }"></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="text" name="note" value="${itineItemVO==null?'':itineItemVO.getNote() }"></td>
	</tr>
	<tr>
	<jsp:useBean id="itineMemberSvc" scope="page" class="com.itineMember.model.ItineMemberService"/>
	<jsp:useBean id="itineSvc" scope="page" class="com.itine.model.ItineService"/>
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
		<td>負責人:</td>
		<td>
			<select name="manager" value="${itineItemVO==null?'':'itineItemVO.manager' }">
<!-- 				負責人可以是這團的創建人 -->
				<option value="${itineSvc.findByItineNo(itineNo).builder }">
				${memberSvc.getOneMember(itineSvc.findByItineNo(itineNo).builder).name }</option>
<!-- 				或是這團的團員 -->
			<c:forEach var="itineMemberVO" items="${itineMemberSvc.findByItineNo(itineNo) }">
				<option value="${itineMemberVO.memberNo }">
				${memberSvc.getOneMember(itineMemberVO.memberNo).name }</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>是否完成:</td>
		<td>
		<input type="radio" name="isDone" value="false" ${itineItemVO!=null&&itineItemVO.isDone==false?'checked':'' }>尚未
		<input type="radio" name="isDone" value="true" ${itineItemVO!=null&&itineItemVO.isDone==true?'checked':'' }>ok
		</td>
	</tr>
	<tr>
		<td>完成日期:</td>
		<td><input type="date" name="finishDate" value="${itineItemVO==null?'':'itineItemVO.finishDate' }"></td>
	</tr>
	<tr>
		<td>分工備註:</td>
		<td><input type="text" name="taskNote" value="${itineItemVO==null?'':itineItemVO.taskNote }"></td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="add">
<input type="hidden" name="itineNo" value="${itineNo }">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
<input type="submit" value="送出新增"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%=request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


</html>