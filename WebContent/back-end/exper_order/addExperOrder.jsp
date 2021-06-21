<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>新增梯次</title>
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/assets/css/untitled.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>
    <link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap-theme.min.css'>
    <link href="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.min.css" />
    <link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/untitled.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/experSideBar.css">
    <style type="text/css">
        #success_message {
            display: none;
        }
        legend {
            margin: auto;
        }
    </style>
    <style>
.portfolio-block {
padding-top:70px;
}

.h1, h1 {
padding-top:20px;
}

</style>
</head>

<body>
<%@include file="/back-end/experience/myNavBar_exper.jsp"%>
    <main class="page contact-page">
					<section class="portfolio-block contact">
					<jsp:include page="/back-end/experience/experSideBar.jsp" />
						<div class="container">
							<div class="heading">
                        <h1>新增體驗梯次</h1>
                        </div>
							<form class="form-horizontal" method="POST" action="<%=request.getContextPath()%>/ExperOrder/ExperOrder.do">
								<div>
									<c:if test="${not empty errorMsgs}">
										<div class="container">
											<div class="alert alert-danger" role="alert">
												<strong>新增失敗，請修正以下錯誤:</strong>
												<ul>
													<c:forEach var="message" items="${errorMsgs}">
														<li>${message}
													</c:forEach>
												</ul>
											</div>
										</div>
									</c:if>
								</div>
                        
                        <div class="form-group">
                                <label for="exper_no">體驗編號:</label>
                                    <input type="text" class="form-control item" id="exper_no" name="exper_no" value="${expordVO.exper_no}" />
                            </div>
                            <div class="form-group">
                                <label for="apply_start">體驗報名開始:</label>
                              
                                    <input type="text" class="form-control item" id="apply_start" name="apply_start" value="${expordVO.apply_start}" />
               
                            </div>
                            <div class="form-group">
                                <label for="apply_end">體驗報名結束:</label>
                                    <input type="text" class="form-control item" id="apply_end" name="apply_end" value="${expordVO.apply_end}" />
                               
                            </div>
                            <div class="form-group">
                                <label for="exper_order_start">體驗開始:</label>
                                    <input type="text" class="form-control item" id="exper_order_start" name="exper_order_start" value="${expordVO.exper_order_start}" />
                                
                            </div>
                            <div class="form-group">
                                <label for="exper_order_end">體驗結束:</label>
                                
                                    <input type="text" class="form-control item" id="exper_order_end" name="exper_order_end" value="${expordVO.exper_order_end}" />
                                
                            </div>
                            <div class="form-group">
                                <label for="exper_max_limit">體驗人數上限:</label>
                             
                                    <input type="text" class="form-control item" id="exper_max_limit" name="exper_max_limit" value="${expordVO.exper_max_limit}" />
                               
                            </div>
                            <div class="form-group">
                                <label for="exper_min_limit">體驗人數下限:</label>
                              
                                    <input type="text" class="form-control item" id="exper_min_limit" name="exper_min_limit" value="${expordVO.exper_min_limit}" />
                               
                            </div>
                            <div class="form-group">
                                <label for="exper_now_price">此體驗當期售價:</label>
                              
                                    <input type="text" class="form-control item" id="exper_now_price" name="exper_now_price" value="${expordVO.exper_now_price}" />
                              
                            </div>
                            <div class="form-group">
         
                                    <input type="hidden" name="exper_no" value="${expordVO.exper_no}"> <input type="hidden" name="action" value="insert">
                                    <button class="btn btn-primary btn-block btn-lg" type="submit">送出新增</button>
                                </div>
							</form>
						</div>
					</section>
				</main>
        <!-- /.container -->
        <!-- partial -->
             <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
        <script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js'></script>
        <script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-datetimepicker/2.5.20/jquery.datetimepicker.full.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {


            $.datetimepicker.setLocale('zh');
            $("[name='apply_start']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: false, //timepicker:true,
                step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d', //format:'Y-m-d H:i:s',
                hideIfNoPrevNext: true,
                value: new Date(),
                //maxDate: "+3d", // 去除今日(不含)之後
                minDate: new Date()



            });
            $("[name='apply_end']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: false, //timepicker:true,
                step: 1, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d', //format:'Y-m-d H:i:s',
                value: $("[name='act_sign_start']").change(function(e) {
                    $("[name='act_sign_end']").val($("[name='act_sign_start']").val());
                }),

                minDate: $("[name='act_sign_start']").change(function(e) {
                    $("[name='act_sign_end']").val($("[name='act_sign_start']").val());
                }), // 去除今日(不含)之後
            });
            $("[name='exper_order_start']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: true, //timepicker:true,
                step: 30, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d H:i', //format:'Y-m-d H:i:s',
                value: $("[name='act_sign_end']").change(function(e) {
                    $("[name='act_period_start']").val($("[name='act_sign_end']").val());
                }),
                minDate: '+0' // 去除今日(不含)之後
            });
            $("[name='exper_order_end']").datetimepicker({
                theme: '', //theme: 'dark',
                timepicker: true, //timepicker:true,
                step: 30, //step: 60 (這是timepicker的預設間隔60分鐘)
                format: 'Y-m-d H:i', //format:'Y-m-d H:i:s',
                value: $("[name='act_period_start']").change(function(e) {
                    $("[name='act_period_end']").val($("[name='act_period_start']").val());
                }),
                minDate: '+0' // 去除今日(不含)之後
            });


        })
        </script>
</body>

</html>