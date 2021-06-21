<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.attraction.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	/*
	AttractionService attraSvc = new AttractionService();
	List<AttractionVO> list = attraSvc.getUnchecked();
	pageContext.setAttribute("list",list);
	*/
	List<AttractionVO> list = (List<AttractionVO>)request.getAttribute("list");
	if((List<AttractionVO>)request.getAttribute("list")==null){
		list = (List<AttractionVO>)session.getAttribute("list");
	}

	session.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>搜尋景點資料 listSearchForAttraction.jsp</title>
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
	<tr><td>
		 <h3>搜尋景點資料 listSearchForAttraction.jsp</h3>
		 <h4><a href="attraction_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<%@ include file ="page1.file" %>
<table>
	<tr>
		<th>分類</th>
		<th>景點名稱</th>
		<th>描述</th>
		<th>地點</th>
		<th>在架狀態</th>
		<th>圖片</th>
		
	</tr>
	<c:forEach var="attraVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${attraVO.sort}</td>
			<td>${attraVO.attraName}</td>
			<td>${attraVO.descr}</td>
			<td>${attraVO.location}</td>
			<td>${attraVO.isOnShelf}</td> 
			<td>${attraVO.attraPic1}</td>
			
			
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>



</body>
</html>