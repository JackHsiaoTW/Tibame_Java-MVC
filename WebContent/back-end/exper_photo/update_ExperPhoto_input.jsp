<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.exper_photo.model.*"%>

<%
  ExperPhotoVO expphoVO = (ExperPhotoVO) request.getAttribute("expphoVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>體驗照片修改 - update_ExperPhoto_input.jsp</title>

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
img.QQ{
width:100px;
height:100px;}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>體驗照片修改 - update_ExperPhoto_input.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath() %>/front-sell-end/exper_photo/select_page.jsp"><img
						src="images/back1.gif" width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

<!-- 	錯誤表列 -->
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM 
		ACTION="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do" name="form1" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>體驗照片編號:<font color=red><b>*</b></font></td>
				<td><%=expphoVO.getExper_photo_no()%></td>
			</tr>
			<tr>
				<td>體驗編號:</td>
				<td><%=expphoVO.getExper_no()%></td>
			</tr>
			<tr>
				<td>體驗照片:</td>
				<td>
				<input type="file" name="upfile1" id="myFile">
				<input type="hidden" name="photo" value="<%=expphoVO.getPhoto()%>" />
				<img class="QQ" src="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do?exper_photo_no=
				${expphoVO.exper_photo_no}&action=getOneExperPho"></td>
			</tr>
		

		</table>
		<br> <input type="hidden" name="action" value="update"> 
		<input type="hidden" name="exper_photo_no" value="<%=expphoVO.getExper_photo_no()%>">
		<input type="hidden" name="exper_no" value="<%=expphoVO.getExper_no()%>">
	    <input type="submit" value="送出修改">
	</FORM>
</body>




</html>