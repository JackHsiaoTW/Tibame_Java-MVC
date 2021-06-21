<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM itinery: Home</title>

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
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM itinery: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM itinery: Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itinery/itine.do" >
       <b>選擇會員姓名:</b>
       <select size="1" name="memberNo">
          <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>
          <c:forEach var="memberVO" items="${memberSvc.all}">
          <option value="${memberVO.memberNo}">${memberVO.name}
          </c:forEach>	
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOneMemberAllItine_For_Display">
    </FORM>
  </li>

</ul>





</body>




</html>