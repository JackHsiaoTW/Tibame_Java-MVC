<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itine.model.*"%>
<%@ page import="com.friendList.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itineMember.model.*"%>



<%

	String itineNoStr = request.getParameter("itineNo");
	Integer itineNo = new Integer(itineNoStr);
	
	List<ItineMemberVO> list = (List<ItineMemberVO>)session.getAttribute("list");
	
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>新增團員的好友列表 - addItineMember.jsp</title>

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
		 <h3>新增團員的好友列表 - addItineMember.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>新增團員:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<td>好友名稱:</td>
		
	</tr>
	<jsp:useBean id="friendListSvc" scope="session" class="com.friendList.model.FriendListService"/>
	<c:forEach  var="friendListVO" items="${friendListSvc.getOneFriendListByMemNo(memberVO.memberNo)}"> 
	<tr>
	<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
				<td>${friendListVO.friendNo} -- ${ memberSvc.getOneMember(friendListVO.friendNo).name} --${itineMemberVO.memberNo}
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/itinery/itineMember.do" name="form1">
						<button ${itineMemberVO.memberNo==friendListVO.friendNo?"disable":""}>新增</button>
						<input type="hidden" name="action" value="addItineMember">
						<input type="hidden" name="itineNo" value="<%= itineNo%>">
						<input type="hidden" name="memberNo" value="${friendListVO.friendNo}">
					</FORM>
				</td>
			</tr>
	</c:forEach>
</table>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%=request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"): </font> <%=request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


</html>