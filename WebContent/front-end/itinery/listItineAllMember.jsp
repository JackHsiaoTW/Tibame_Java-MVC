<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itineMember.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	List<ItineMemberVO> list = (List<ItineMemberVO>) request.getAttribute("list");
	// 	System.out.println("A:" +list);
	// 	session.setAttribute("list",list);
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>該團所有團員 listItineAllMember.jsp</title>
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
<body>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>該行程所有團員 listItineAllMember.jsp</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/front-end/itinery/itinery_select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>姓名</th>
			<th>權限</th>
			<th></th>
		</tr>
		<c:forEach var="itineMemberVO" items="${list}">

			<tr bgcolor=${param.memberNo==itineMemberVO.memberNo?"pink":"" }>
				<jsp:useBean id="memberSvc" scope="page"
					class="com.member.model.MemberService" />
				<td>${itineMemberVO.memberNo}--${memberSvc.getOneMember(itineMemberVO.memberNo).name }</td>

				<td>
					
						<c:if test="${itineMemberVO.isEditable==0}">未加入</c:if>
						<c:if test="${itineMemberVO.isEditable==1}">不能編輯</c:if>
						<c:if test="${itineMemberVO.isEditable==2}">能編輯</c:if>
						<c:if test="${itineMemberVO.isEditable==3}">已刪除</c:if>

				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/itinery/itineMember.do"
						style="margin-bottom: 0px;">
						<select name="isEditable">
<%-- 							<option value="0" ${itineMemberVO.isEditable==0?'selected':'' }>未加入</option> --%>
							<option value="1" ${itineMemberVO.isEditable==1?'selected':'' }>不能編輯</option>
							<option value="2" ${itineMemberVO.isEditable==2?'selected':'' }>能編輯</option>
<%-- 							<option value="3" ${itineMemberVO.isEditable==3?'selected':'' }>已刪除</option> --%>
						</select> <input type="submit" value="確認修改"> <input type="hidden"
							name="itineNo" value="${itineMemberVO.itineNo}"> <input
							type="hidden" name="memberNo" value="${itineMemberVO.memberNo}">
						<input type="hidden" name="action" value="updateMemberIsEditable">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/itinery/itineMember.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="itineNo" value="${itineMemberVO.itineNo}"> <input
							type="hidden" name="memberNo" value="${itineMemberVO.memberNo}">
						<input type="hidden" name="action" value="fakeDeleteMember">
					</FORM>
				</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/front-end/itinery/itineMember.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="確認加入"> <input type="hidden"
							name="itineNo" value="${itineMemberVO.itineNo}"> <input
							type="hidden" name="memberNo" value="${itineMemberVO.memberNo}">
						<input type="hidden" name="action" value="confirmInvite">
					</FORM>
				</td>



			</tr>
		</c:forEach>
	</table>

	<ul>
		<li><form method="get"
				action="<%=request.getContextPath()%>/front-end/itinery/listMemberForItineryMember.jsp">
				<input type="hidden" name="itineNo" value="${param.itineNo}">
				<input type="hidden" name="itineMemberList" value="${list}">
				<button>所有好友</button>
			</form></li>
		<li><form method="get"
				action="<%=request.getContextPath()%>/front-end/itinery/listMemberForItineryMember.jsp">
				<input type="hidden" name="itineNo" value="${param.itineNo}">
				<input type="text" name="memberName">
				<button>搜尋好友</button>
			</form></li>
	</ul>



</body>
</html>