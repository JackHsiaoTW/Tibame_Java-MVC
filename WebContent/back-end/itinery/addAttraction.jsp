<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.attraction.model.*"%>

<%
	AttractionVO attraVO = (AttractionVO) request.getAttribute("attraVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>景點資料新增 - addAttraction.jsp</title>

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
				<h3>新增景點:</h3>

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
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itinery/attraction.do" name="form1">
					<table class="table table-striped">
						<tr>
							<td>分類:</td>
							<td><select name="sort" class="form-control">
									<option value="">--請選擇分類--</option>

									<option value="景點"
										<%=(attraVO != null && attraVO.getSort().equals("景點")) ? "selected" : ""%>>景點</option>
									<option value="餐廳"
										<%=(attraVO != null && attraVO.getSort().equals("餐廳")) ? "selected" : ""%>>餐廳</option>
									<option value="住宿"
										<%=(attraVO != null && attraVO.getSort().equals("住宿")) ? "selected" : ""%>>住宿</option>

							</select></td>
						</tr>
						<tr>
							<td>景點名稱:</td>
							<td><input type="TEXT" name="attraName" size="45"
								value="<%=(attraVO == null) ? "" : attraVO.getAttraName()%>"
								class="form-control" /></td>
						</tr>
						<tr>
							<td>敘述:</td>
							<td><input type="TEXT" name="descr" size="45"
								value="<%=(attraVO == null) ? "" : attraVO.getDescr()%>"
								class="form-control" /></td>
						</tr>
						<tr>
							<td>地址:</td>
							<td>
								<div role="tw-city-selector" data-bootstrap-style >
								<div class="form-group">
									<select class="form-control county" name="county"></select>
								</div>
								<div class="form-group">
									<select class="form-control district" name="district"></select>
								</div>
								</div>
								<input type="TEXT" name="shortLocation" size="45"
									value="${(attraVO == null) ? '' : param.shortLocation}"
									class="form-control" />
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
					<br> <input type="hidden" name="action" value="add">
					<!-- 重要 -->
					<input type="submit" value="送出新增" class=" btn btn-primary">
				</FORM>
			</div>
		</div>
	</div>
	
<script>
  new TwCitySelector({
	  el: 'div[role=tw-city-selector]',
	  elCounty: 'select[name=county]',
	  elDistrict: 'select[name=district]',
	  countyValue: '${param.county}',
	  districtValue: '${param.district}'
  });
</script>
</body>




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date hiredate = null;
	try {
		//	    hiredate = empVO.getHiredate();
	} catch (Exception e) {
		hiredate = new java.sql.Date(System.currentTimeMillis());
	}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>