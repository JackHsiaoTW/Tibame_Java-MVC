<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>達人體驗 Experience : Home</title>

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
<body bgcolor='lightskyblue'>

<table id="table-1">
   <tr><td><h3>達人體驗 Experience : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for 達人體驗 Experience: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='<%=request.getContextPath() %>/front-sell-end/experience/listAllExperience.jsp'>List</a> all Experience.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Experience/Experience.do" >
        <b>請輸入體驗編號:</b>
        <input type="text" name="exper_no">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="experSvc" scope="page" class="com.experience.model.ExperienceService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Experience/Experience.do" >
       <b>請選擇體驗名稱:</b>
       <select size="1" name="exper_no">
         <c:forEach var="experVO" items="${experSvc.all}" > 
          <option value="${experVO.exper_no}">${experVO.name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath() %>/Experience/Experience.do" >
       <b>請選擇體驗編號:</b>
       <select size="1" name="exper_no">
         <c:forEach var="experVO" items="${experSvc.all}" > 
          <option value="${experVO.exper_no}">${experVO.exper_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
  
  

  
</ul>

 

<h3>達人體驗管理</h3>

<ul>
  <li><a href='<%=request.getContextPath() %>/front-sell-end/experience/addExperience.jsp'>Add</a> a new Experience.</li>
</ul>

</body>
</html>