<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>達人體驗照片</title>

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
   <tr><td><h3>達人體驗 照片 : Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for 達人體驗 照片 : Home</p>

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
  <li><a href='<%=request.getContextPath()%>/front-sell-end/exper_photo/listAllExperPhoto.jsp'>List</a> all ExperPhoto.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do" >
        <b>輸入體驗照片編號:</b>
        <input type="text" name="exper_photo_no">
        <input type="hidden" name="action" value="get_Photo_By_Exper_photo_no">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="expphoSvc" scope="page" class="com.exper_photo.model.ExperPhotoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do" >
       <b>選擇體驗照片編號:</b>
       <select size="1" name="exper_photo_no">
         <c:forEach var="expphoVO" items="${expphoSvc.all}" > 
          <option value="${expphoVO.exper_photo_no}">${expphoVO.exper_photo_no}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="get_Photo_By_Exper_photo_no">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>體驗照片管理</h3>

<ul>
  <li><a href="<%=request.getContextPath()%>/front-sell-end/exper_photo/upload_Photo.jsp">新增</a>體驗照片</li>
</ul>
<ul>
  <li><a href="<%=request.getContextPath()%>/front-sell-end/exper_photo/upload_Photo_Multi.jsp">上傳多張</a>體驗照片</li>
</ul>
</body>
</html>