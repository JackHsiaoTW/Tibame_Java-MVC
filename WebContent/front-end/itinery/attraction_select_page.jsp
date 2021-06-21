<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Itine Home</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body>
<table id="talble-1">
	   <tr><td><h3>IBM Atrraction: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Attraction : Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>	
	</ul>
</c:if>

<ul>
	<li>
		<a href="listAllAttraction.jsp">List</a> all Attractions<br>
	</li>
	<li>
		<a href="listUncheckedAttraction.jsp">List</a> unchecked Attractions<br>
	</li>
	<li>
		<form method ="get" action="attraction.do">
			<input type ="text" name="searchfor">
			<input type = "hidden" name = "action" value= "search">
			<input type = "submit" value="查詢">
		</form>
	</li>

</ul>

<h3>新增景點</h3>

<ul>
  <li><a href='addAttraction.jsp'>Add</a> a new attraction.</li>
</ul>


</body>
</html>