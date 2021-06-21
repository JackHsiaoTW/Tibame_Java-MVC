<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.attraction.model.*"%>

<%
	AttractionVO attraVO = (AttractionVO) request.getAttribute("attraVO");
	String location = attraVO.getLocation();
	String shortLocation = null;
	String county = null;
	String district = null;
	
	if(location.length()==5){
		county = location.substring(0,3);
		district = location.substring(3);
		shortLocation = "";
	}
	if(location.length()==6){
		county = location.substring(0,3);
		district = location.substring(3);
		shortLocation = "";
	}
	if(location.length()>=7){
		county = location.substring(0,3);
		district = location.substring(3,6);
		shortLocation = location.substring(6);
	}
	
	pageContext.setAttribute("county",county);
	pageContext.setAttribute("district",district);
	pageContext.setAttribute("shortLocation",shortLocation);
	
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>景點資料修改 - update_attra_input.jsp</title>
<!-- Custom fonts for this template-->
<%-- <link href="<%=request.getContextPath()%>/resources/css/all.min.css" --%>
<!-- 	rel="stylesheet" type="text/css"> -->
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" -->
<!-- 	rel="stylesheet"> -->

<!-- Custom styles for this template-->
<!-- <link -->
<%-- 	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" --%>
<!-- 	rel="stylesheet"> -->

<!-- Bootstrap core JavaScript-->
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script> --%>
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script> --%>

<!-- Core plugin JavaScript-->
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> --%>

<!-- Custom scripts for all pages-->
<!-- <script -->
<%-- 	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script> --%>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-12">
				<table id="table-1">
					<tr>
						<td>
							<h3>景點資料修改 :</h3>
						</td>
					</tr>
				</table>
			</div>
			<div class="col-12">

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<div class="col-12">
				<FORM METHOD="post" ACTION="attraction.do" name="form1">
					<table class="table table-striped">
						<tr>
							<td>景點編號:<font color=red><b>*</b></font></td>
							<td><%=attraVO.getAttraNo()%></td>
						</tr>
						<tr>
							<td>分類:</td>
							<td><select name="sort" class="form-control">
									<option value="">--請選擇分類--</option>
									<option value="景點"
										<%=(attraVO.getSort().equals("景點")) ? "selected" : ""%>>景點</option>
									<option value="餐廳"
										<%=(attraVO.getSort().equals("餐廳")) ? "selected" : ""%>>餐廳</option>
									<option value="住宿"
										<%=(attraVO.getSort().equals("住宿")) ? "selected" : ""%>>住宿</option>
							</select></td>
						</tr>
						<tr>
							<td>景點名稱:</td>
							<td><input type="TEXT" name="attraName1" size="45"
								value="<%=attraVO.getAttraName()%>" class="form-control"/></td>
						</tr>
						<tr>
							<td>敘述:</td>
							<td><input name="descr" type="text" size="50"
								value="<%=attraVO.getDescr()%>" class="form-control"></td>
						</tr>
						<tr>
							<td>地點:</td>
							<td>
							<div role="tw-city-selector" data-bootstrap-style >
								<div class="form-group">
									<select class="form-control county" name="county"></select>
								</div>
								<div class="form-group">
									<select class="form-control district" name="district"></select>
								</div>
							</div>
							<input type="TEXT" name="shortLocation" size="50"
								value="${shortLocation }" class="form-control"/>
							</td>
						</tr>
						<tr>
							<td>在架狀態:</td>
							<td>
							<label><input type="radio" name="isOnShelf" value="1" <%=attraVO.getIsOnShelf() == 1 ? "checked" : ""%>>架上</label>
							<label><input type="radio" name="isOnShelf" value="2" <%=attraVO.getIsOnShelf() == 2 ? "checked" : ""%>>架下</label>
							</td>
						</tr>
						<tr>
							<td>圖片:</td>

							<td><input type="TEXT" name="attraPic1" size="50"
								value="${attraVO.attraPic1 }" class="form-control"/>
								<div class="form-text">請輸入公開的圖片網址</div>
							</td>
						</tr>

					</table>
					<br> <input type="hidden" name="action" value="update">
						<input type="hidden" name="attraNo" value="<%=attraVO.getAttraNo()%>"> 
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
						<input type="hidden" name="finishURL"
							value="${param.finishURL }">
<!-- 						value="/back-end/itinery/listAllAttraction_full.jsp">  -->
						<input type="hidden" name="whichPage" value="${param.whichPage }">
						<input type="submit" value="送出修改" class=" btn btn-primary">
						<input type="hidden" name="paramAttraName" value="${param.paramAttraName }">
						<input type="hidden" name="paramSort" value="${param.paramSort }">
				</FORM>
			</div>
		</div>
	</div>
</body>

<script>
  new TwCitySelector({
	  el: 'div[role=tw-city-selector]',
	  elCounty: 'select[name=county]',
	  elDistrict: 'select[name=district]',
	  countyValue: '${county}',
	  districtValue: '${district}'
  });
</script>


</html>