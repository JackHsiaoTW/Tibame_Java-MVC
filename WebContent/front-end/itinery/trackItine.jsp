<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.itineItem.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.itineMember.model.*"%>
<%@ page import="com.itine.model.*"%>
<%@ page import="com.attraction.*" %>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	Integer itineNo = new Integer(request.getParameter("itineNo"));

	ItineService itineSvc = new ItineService();
	ItineVO itineVO = itineSvc.findByItineNo(itineNo);

	ItineItemService itineItemSvc = new ItineItemService();
	List<ItineItemVO> list = itineItemSvc.findByItineNo(itineNo);

	ItineMemberService itineMemberSvc = new ItineMemberService();
	List<ItineMemberVO> itineMemberList = itineMemberSvc.findByItineNo(itineNo);

	pageContext.setAttribute("list", list);
	pageContext.setAttribute("itineMemberList", itineMemberList);
	pageContext.setAttribute("itineVO", itineVO);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService"/>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>trackItine.jsp</title>

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/resources/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script
	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
	

<style>
</style>
<style>
body{
	background-color:#e9b24e5c;
}

.doing-itine-item-list {
	height: 250px;
	overflow-y: auto;
}

.done-itine-item-list {
	height: 100px;
	overflow-y: auto;
}

.itine-item {
	height: 40px;
	border-radius:10px;
}

.itine-item:nth-child(odd) {
	background-color: #C1E4F9;
}

.itine-item:nth-child(even) {
	background-color: #d9e7fd;
}

.itine-item-name {
	font-size: 24px;
	border-bottom: 1px solid #2E55C7;
}

.note-area {
	border-radius: 5px;
	background-color: #C1E4F9;
	position: fixed;
	z-index: 2;
	height: 350px;
	width: 200px;
	top: 28%;
	left: 5%;
	overflow-y: auto;
	display: none;
}

.fa-plus:hover {
	cursor: pointer;
}

.task-note-area {
	border-radius: 5px;
	background-color: #C1E4F9;
	position: fixed;
	z-index: 2;
	height: 350px;
	width: 200px;
	top: 28%;
	left: 80%;
	overflow-y: auto;
	display: none;
}

.task-note-title {
	border-bottom: 1px solid #2E55C7;
	font-size: 24px;
}
</style>
<script>
        $(document).ready(function () {
            init();
            function init() {
                $(".itine-item .left-side input").off("click").click(function (e) {
                    e.stopPropagation();
                });
                $(".itine-item .left-side input").off("change").change(function (e) {
                    // 未完成>>已完成
                    if (this.checked) {
                        //已完成
                        let doneTxt=
                            `<div class="col-12 itine-item d-flex align-items-center">
                        		<div class="left-side w-50"><input type="checkbox" name="isDone" value=${'$'}{$(this).val()} checked>${'${$(this).parent().text()}'}</div>
                            	<div class="right-side w-50 d-flex">
                                	<div class="manager w-25 text-center mt-1">
                                    	${'$'}{$(this).parent().parent().find("#manager option:selected").text()}
                                	</div>
                            		<div class="finish-date w-50 text-center mt-1">${'${$(this).parent().parent().find("input[type=date]").val()}'}</div>
                            		<div class="w-25"><i class="fas fa-plus ml-5 mt-2" id=${'$'}{$(this).val()}></i></div>
                        		</div>
                    		</div>`;
                         //長出已完成的自己並刪除未完成的自己

						let thisItineItemNo = $(this).val();
						$("[type=checkbox]").each(function(){
							if($(this).val()===thisItineItemNo){
								$(this).parent().parent().parent().next().next().prepend(doneTxt);
								$(this).parent().parent().remove();
							}
						});
                        init();
                        $.ajax({
                        	url: "<%=request.getContextPath()%>/itinery/itineItem.do?action=updateIsDone&itineNo=${param.itineNo}&attraNo="+$(this).val()+"&isDone=true",
                        	type: "GET",
                        	success: function(data){
                        		swal("太棒了",data,"success");
                        	},
                        	error: function(){
                        		window.alert("failed");
                        	},
                        });
                    //  已完成>>未完成
                    } else {
//                         window.alert($(this).parent().parent().find(".manager").text().trim());
                        // 未完成
                        let doingTxt=
                            `<div class="col-12 itine-item d-flex align-items-center">
                        		<div class="left-side w-50"><input type="checkbox" name="isDone" value=${'$'}{$(this).val()} >${'$'}{$(this).parent().text()}</div>
                            	<div class="right-side w-50 d-flex">
                                	<div class="manager w-25">
                                    	<select name="manager" id="manager" class="form-control">
                                    		<option value="${itineVO.builder }" class="form-control" ${'$'}{$(this).parent().parent().find(".manager").text().trim() === "${memberSvc.getOneMember(itineVO.builder).name }" ? "selected" : ""}>${memberSvc.getOneMember(itineVO.builder).name }</option>
                                    	<c:forEach var="itineMemberVO" items="${itineMemberList }">
                                    		<option value="${itineMemberVO.memberNo }" class="form-control" ${'$'}{$(this).parent().parent().find(".manager").text().trim() === "${memberSvc.getOneMember(itineMemberVO.memberNo).name }" ? "selected" : ""}>${memberSvc.getOneMember(itineMemberVO.memberNo).name }</option>
                                    	</c:forEach>
                                    	</select>
                                	</div>
                                	<div class="finish-date w-50"><input type="date" class="form-control " value="${'$'}{$(this).parent().parent().find('.finish-date').text()}"></div>
                                    <div class="w-25"><i class="fas fa-plus ml-5 mt-2" id=${'$'}{$(this).val()}></i></div>
                                </div>
                    		</div>`;
                        //長出已完成的自己並刪除未完成的自己

						let thisItineItemNo = $(this).val();
						$("[type=checkbox]").each(function(){
							if($(this).val()===thisItineItemNo){
								$(this).parent().parent().parent().prev().prev().prepend(doingTxt);
								$(this).parent().parent().remove();
							}
						});
                        init();
                        $.ajax({
                        	url: "<%=request.getContextPath()%>/itinery/itineItem.do?action=updateIsDone&itineNo=${param.itineNo}&attraNo="+$(this).val()+"&isDone=false",
                        	type: "GET",
                        	success: function(data){
                        		swal("太棒了",data,"success");
                        	},
                        	error: function(){
                        		window.alert("failed");
                        	},
                        });
                    }
                });


                $(".fa-plus").off("click").click(function () {
                    $(".task-note-area").fadeToggle();
                    $.ajax({
                    	url: "<%=request.getContextPath()%>/itinery/itineItem.do?action=showTaskNote&itineNo=${param.itineNo}&attraNo="+$(this).attr("id"),
                    	type: 'GET',
                    	success: function(data){
//                     		window.alert("success");
                    		$(".task-note").html("");
                    		let itineItemVO = JSON.parse(data);
                    		if(itineItemVO.taskNote){
                    		$(".task-note").text(itineItemVO.taskNote);
                    		}
                    		$(".task-note").attr("attraNo",itineItemVO.attraNo);
//                     		window.alert(itineItemVO.taskNote);
                    	},
                    	error: function(){
                    		window.alert("failed");
                    	},
                    });
                })
 
                $(".itine-item .left-side").off("click").click(function (e) {
                    $(".note-area").fadeToggle();
                    $.ajax({
                    	url: "<%=request.getContextPath()%>/itinery/itineItem.do?action=showNote&itineNo=${param.itineNo}&attraNo="+$(this).find('input').val(),
                    	type: 'GET',
                    	success: function(data){
//                     		window.alert("success");
                    		$(".note-area").html("");
                    		let itineItemVO = JSON.parse(data);
                    		$(".note-area").append(
                    		`<div class="itine-item-name">${'${itineItemVO.attraName}'}</div>
            
            					<div class="time">開始時間:</div>
            					<div class="start-time">${'${itineItemVO.startTime}'}</div>
            
           	 					<div class="time">結束時間:</div>
            					<div class="end-time">${'${itineItemVO.endTime}'}</div>
            
            					<div>備註:</div>
            					<div class="note">${'$'}{itineItemVO.note!==undefined?itineItemVO.note:''}
                				</div>`		
                    		
                    		);
                    	},
                    	error: function(){
                    		window.alert("failed");
                    	},
                    });
                });
                
                $("#save-task-note-btn").off("click").click(function(){
                	$.ajax({
                		url: "<%=request.getContextPath()%>/itinery/itineItem.do",
                		type: "POST",
                		data: {
                			"action": "updateTaskNote",
                			"itineNo": "${param.itineNo}",
                			"attraNo": $(".task-note").attr("attraNO"),
                			"taskNote": $(".task-note").val()
                		},
                		success: function(data){
                			swal("太棒了",data,"success");
                		}, 
                		error: function(){
                			window.alert("failed");
                		},
                	})
                })
                
                $(".manager").off("change").change(function(){
                	$.ajax({
                		url: "<%=request.getContextPath()%>/itinery/itineItem.do",
                		type: "POST",
                		data: {
                			"action": "updateManager",
                			"itineNo": "${param.itineNo}",
                			"attraNo": $(this).next().next().find("i").attr("id"),
                			"manager": $(this).find("#manager").val()
                		},
                		success: function(data){
                			swal("太棒了",data,"success");
                		}, 
                		error: function(){
                			window.alert("failed");
                		},
                	})
                	
                	let thisItineItem = $(this).parent().parent();
                	
                	//append方法會把原本的東西刪掉。再去附加在別人身上。
                	// 選到自己的 在自己那邊append一個(只能在團體選)
                	
            		if($(this).find("#manager option:selected").text()==$("#userDropdown>span").text()){
            			console.log("選到自己")
                		$("#itine-person .doing-itine-item-list").prepend(thisItineItem.clone());
            		}
//                 	console.log("append完成");
                	// 從自己選到他人  兩邊都刪掉  團體那邊再更新
                	
                	if($(this).find("#manager option:selected").text()!==$("#userDropdown>span").text()){
                		console.log("選到他人")
                	$(".itine-item").each(function(){
                		if($(this).find("input[type=checkbox]").val()==thisItineItem.find("input[type=checkbox]").val()){
                			$(this).remove();
                			console.log("remove");
                			$("#itine-group .doing-itine-item-list").prepend(thisItineItem);
                			console.log("append!");
                		}
                	})
                	}
                	init();
                	
                });
                
                $(".finish-date").off("change").change(function(){
                	console.log("finishDate :"+$(this).find("input").val());
                	$.ajax({
                		url:"<%=request.getContextPath()%>/itinery/itineItem.do",
                		type:"POST",
                		data:{
                			"action": "updateFinishDate",
                			"itineNo": "${param.itineNo}",
                			"attraNo": $(this).next().find("i").attr("id"),
                			"finishDate": $(this).find("input").val()
                		},
                		success: function(data){
                			swal("太棒了",data,"success");
                		},
                		error: function(){
                			window.alert("failed");
                		}
                	});
                	
                	let thisItineItem = $(this).parent().parent();
                	if($(this).prev().find("option:selected").text()==$("#userDropdown>span").text()){
                		console.log("變更自己負責的景點的預計完成時間")
                		$(".itine-item").each(function(){
                			if($(this).find("input[type=checkbox]").val()==thisItineItem.find("input[type=checkbox]").val()){
                				$(this).remove();
                			}
                		})
                		$(".doing-itine-item-list").prepend(thisItineItem.clone());
                	}
                	
                })
                
               
                
                
            }
        });
        

    </script>

</head>

<body>
	<!-- Topbar -->
	<jsp:include page="/front-end/myNavBar.jsp"/>

	<!-- End of Topbar -->
	<!-- Start of page content -->
	<div class="container" style="margin-top: 70px;">
		<div class="note-area"></div>
		<div class="task-note-area">
			<div class="task-note-title">分工備註:</div>
			<div>
				<textarea class="task-note" name="task-note" rows="11" cols="18"></textarea>
				<button class="btn btn-primary mt-0" id="save-task-note-btn">儲存設定</button>
			</div>
		</div>
		<div class="row">
			<div class="col-8 mx-auto">
				<!-- new tab -->
				<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
					<li class="nav-item" role="presentation"><a
						class="nav-link active" id="pills-group-tab" data-toggle="pill"
						href="#itine-group" role="tab" aria-controls="pills-group"
						aria-selected="true">團體</a></li>
					<li class="nav-item" role="presentation"><a class="nav-link"
						id="pills-person-tab" data-toggle="pill" href="#itine-person"
						role="tab" aria-controls="pills-person" aria-selected="false">個人</a>
					</li>
				</ul>
				<div class="tab-content" id="pills-tabContent">
					<!-- the first tab -->
					<div class="tab-pane fade show active" id="itine-group"
						role="tabpanel" aria-labelledby="pills-group-tab">

						<div class="col-12 d-flex align-items-end">
							<div class="left-side w-50"
								style="font-size: 25px; font-weight: bold;">未完成</div>
							<div class="right-side w-50 d-flex">
								<div class="w-25">負責人</div>
								<div class="w-75">預計完成日</div>
							</div>
						</div>
						<div class="row doing-itine-item-list align-content-start ">
							<jsp:useBean id="attraSvc" scope="page" class="com.attraction.model.AttractionService" />
							<c:forEach var="itineItemVO" items="${list}">
								<c:if test="${itineItemVO.isDone==false}">
									<div class="col-12 itine-item d-flex align-items-center">
										<div class="left-side w-50">
											<input type="checkbox" name="isDone"
												value="${itineItemVO.attraNo }">${attraSvc.getOne(itineItemVO.attraNo).attraName} 
										</div>
										<div class="right-side w-50 d-flex">
											<div class="manager w-25">
												<select name="manager" id="manager" class="form-control">
													<option value="${itineVO.builder }"
														${itineVO.builder==itineItemVO.manager?'selected':'' }>${memberSvc.getOneMember(itineVO.builder).name}</option>
													<c:forEach var="itineMemberVO" items="${itineMemberList }">
														<option value="${itineMemberVO.memberNo }"
															${itineMemberVO.memberNo==itineItemVO.manager?'selected':'' }
															class="form-control">${memberSvc.getOneMember(itineMemberVO.memberNo).name}</option>
													</c:forEach>
												</select>
											</div>
											<div class="finish-date w-50">
												<input type="date" class="form-control"
													value="${itineItemVO.finishDate}">
											</div>
											<div class="w-25">
												<i class="fas fa-plus ml-5 mt-2"
													id="${itineItemVO.attraNo }"></i>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</div>
						<div class="col-12 d-flex align-items-end">
							<div class="left-side w-50"
								style="font-size: 25px; font-weight: bold;">已完成</div>
							<div class="right-side w-50 d-flex">
								<div class="w-25">負責人</div>
								<div class="w-75">預計完成日</div>
							</div>
						</div>
						<div class="row done-itine-item-list align-content-start">
							<c:forEach var="itineItemVO" items="${list }">
								<c:if test="${itineItemVO.isDone==true }">
									<div class="col-12 itine-item d-flex align-items-center">
										<div class="left-side w-50">
											<input type="checkbox" name="isDone" checked
												value="${itineItemVO.attraNo }">${attraSvc.getOne(itineItemVO.attraNo).attraName}
										</div>
										<div class="right-side w-50 d-flex">
											<div class="manager w-25 text-center mt-1">
												${memberSvc.getOneMember(itineItemVO.manager).name}</div>
											<div class="finish-date w-50 text-center mt-1">${itineItemVO.finishDate }</div>
											<div class="w-25">
												<i class="fas fa-plus ml-5 mt-2"
													id="${itineItemVO.attraNo }"></i>
											</div>
										</div>
									</div>
								</c:if>
							</c:forEach>

						</div>
					</div>

					<!-- the second tab -->
					<div class="tab-pane fade" id="itine-person" role="tabpanel"
						aria-labelledby="pills-person-tab">


						<div class="col-12 d-flex align-items-end">
							<div class="left-side w-50"
								style="font-size: 25px; font-weight: bold;">未完成</div>
							<div class="right-side w-50 d-flex">
								<div class="w-25">負責人</div>
								<div class="w-75">預計完成日</div>
							</div>
						</div>
						<div class="row doing-itine-item-list align-content-start">
							<c:forEach var="itineItemVO" items="${list}">
								<c:if test="${itineItemVO.manager==memberVO.memberNo }">
									<c:if test="${itineItemVO.isDone==false}">
										<div class="col-12 itine-item d-flex align-items-center">
											<div class="left-side w-50">
												<input type="checkbox" name="isDone"
													value="${itineItemVO.attraNo }">${attraSvc.getOne(itineItemVO.attraNo).attraName}
											</div>
											<div class="right-side w-50 d-flex">
												<div class="manager w-25">
													<select name="manager" id="manager" class="form-control">
														<option value="${itineVO.builder }"
															${itineVO.builder==itineItemVO.manager?'selected':'' }>${memberSvc.getOneMember(itineVO.builder).name}</option>
														<c:forEach var="itineMemberVO" items="${itineMemberList }">
															<option value="${itineMemberVO.memberNo }"
																${itineMemberVO.memberNo==itineItemVO.manager?'selected':'' }
																class="form-control">${memberSvc.getOneMember(itineMemberVO.memberNo).name}</option>
														</c:forEach>
													</select>
												</div>
												<div class="finish-date w-50">
													<input type="date" class="form-control"
														value="${itineItemVO.finishDate}">
												</div>
												<div class="w-25">
													<i class="fas fa-plus ml-5 mt-2"
														id="${itineItemVO.attraNo }"></i>
												</div>
											</div>
										</div>
									</c:if>
								</c:if>
							</c:forEach>



						</div>
						<div class="col-12 d-flex align-items-end">
							<div class="left-side w-50"
								style="font-size: 25px; font-weight: bold;">已完成</div>
							<div class="right-side w-50 d-flex">
								<div class="w-25">負責人</div>
								<div class="w-75">預計完成日</div>
							</div>
						</div>
						<div class="row done-itine-item-list align-content-start">
							<c:forEach var="itineItemVO" items="${list }">
								<c:if test="${itineItemVO.manager==memberVO.memberNo }">
									<c:if test="${itineItemVO.isDone==true }">
										<div class="col-12 itine-item d-flex align-items-center">
											<div class="left-side w-50">
												<input type="checkbox" name="isDone"
													value="${itineItemVO.attraNo }" checked>${attraSvc.getOne(itineItemVO.attraNo).attraName}
											</div>
											<div class="right-side w-50 d-flex">
												<div class="manager w-25 text-center mt-1">
													${memberSvc.getOneMember(itineItemVO.manager).name}</div>
												<div class="finish-date w-50 text-center mt-1">${itineItemVO.finishDate }</div>
												<div class="w-25">
													<i class="fas fa-plus ml-5 mt-2"
														id="${itineItemVO.attraNo }"></i>
												</div>
											</div>
										</div>
									</c:if>
								</c:if>
							</c:forEach>



						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- End of page content -->




</body>

</html>