<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itine.model.*"%>
<%@ page import="com.itineMember.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>



<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<%
	// 建彤表示如果是只有會員才能看到的頁面的話，隨時都可以拿到memberVO裡面的數值呀~ 用el拿就好了~
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	//找出這個會員是哪些行程的創建人
	// Integer memberNo = (Integer)session.getAttribute("memberNo");
	Integer memberNo = ((MemberVO) session.getAttribute("memberVO")).getMemberNo();
	ItineService itineSvc = new ItineService();
	List<ItineVO> list = itineSvc.findByBuilder(memberNo);

	//要找出 這個會員是哪些行程的團員  
	ItineMemberService itineMemberSvc = new ItineMemberService();
	List<ItineMemberVO> listM = itineMemberSvc.findByMemberNo(memberNo);
	for (ItineMemberVO itineMemberVO : listM) {
		ItineVO itineVO = itineSvc.findByItineNo(itineMemberVO.getItineNo());
		list.add(itineVO);
	}
	pageContext.setAttribute("list", list);
%>

	
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>listMemberAllItine.jsp</title>

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
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/resources/sweetalert/sweetalert.css"> --%>

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
<!-- <script -->
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
<style>
body{
	background-color: #0a0533ad;
	 }

.page-content {
	margin-top: 150px;
	width: 60%;
}

.border-2 {
	border-width: 2px !important;
}

.new-file{
	transition:all 0.5s;
	background-color:white;
}

.new-file:hover {
	
	cursor: pointer;
	font-size:20px;
	margin-top: -4px;
	color:#2315f3;
	font-weight:bold;
	
}

.filelist {
	height: 352px;
	overflow-y: auto;
}

.file {
	position: relative;
	height:90px;
}

.file:nth-child(odd) {
	background-color: #C1E4F9;
}

.file:nth-child(even) {
	background-color: #d9e7fd;
}

.builder {
	font-size: 12px;
}

.update-date {
	font-size: 12px;
}

.delete-itinery {
	position: absolute;
	z-index: 1;
	top: 1%;
	left: 98%;
	display: none;
	cursor: pointer;
}

.body {
	position: relative;
}

.itine-name:hover {
	cursor: pointer;
}

.invite-friend {
	background-color: rgba(0, 0, 0, 0.3);
	position: fixed;
	/* z-index: 1; */
	top: 70px;
	display: none;
	height: 100%;
	width: 100%;
}

.invite-friend-content {
	margin: 100px auto;
	height: 330px;
	border-radius: 10px;
}

.search-friend-list {
	height: 330px;
	width: 20%;
	position: fixed;
	top: 29%;
	left: 75%;
	padding: 1%;
	text-align: center;
	background-color: white;
	border: 2px solid #2195E2;
	border-radius: 10px;
	display: none;
	overflow-y: auto;
}

.search-friend-list>.one-friend {
	height: 38px;
	line-height: 38px;
	margin: auto;
}


.friend-list {
	height: 150px;
	overflow-y: auto;
}
.friend-list>.one-friend{
	height:38px;
	line-height:38px;
}
.friend-list>.one-friend>.col-3{
	padding-left:0;
	padding-right:0px;
}

.one-friend i:hover {
	cursor: pointer;
}

.invite-friend-content>.input-group>.form-control {
	flex: none;
	width: 85%;
}
.friend-name{
	line-height:38px;
	height:38px;
}
</style>
<script>
	$(document).ready(function() {
		init();
		function init(){
		$(".file").off("mouseenter mouseleave").hover(function() {
			$(this).children(".right-side").children(".delete-itinery").show();
		}, function() {
			$(this).children(".right-side").children(".delete-itinery").hide();
		});

		$(".delete-itinery").off("click").click(function() {
// 			let yes = confirm("確定要刪除嗎?");
// 			if (yes === true) {
// 				var thisicon = this;
// 				$.ajax({
// 					 type: "POST",
// 					 url: "itine.do",
// 					 data: {"action":"fakeDelete","itineNo":$(this).find("input").val()},
// 					 dataType: "json",
// 					 success: function (data){
// 						 window.alert(data[0].word);
// 						console.log($(thisicon).parent().parent());
// 						$(thisicon).parent().parent().remove();
// 					 },
// 		            error: function(){alert("AJAX-class發生錯誤囉!")}
// 		        })
// 			}
			
			let thisicon = this;
			swal({
				  title: "你確定嗎?",
				  text: "一旦刪除，您將無法復原此檔案!",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					  $.ajax({
		 					 type: "POST",
		 					 url: "<%=request.getContextPath()%>/itinery/itine.do",
		 					 data: {"action":"fakeDelete","itineNo":$(this).find("input").val()},
		 					 dataType: "json",
		 					 success: function (data){
		 						$(thisicon).parent().parent().remove();
				    			swal("好的，您的行程已刪除!", {
				      				icon: "success",
				    			});
		 					 },
		 		            error: function(){alert("AJAX-class發生錯誤囉!")}
		 		        })
					  
				  } else {
				    swal("您的檔案很安全!");
				  }
				});
			
			
			
			
			
		});

		$(".invite").off("click").click(function() {
			$(".invite-friend").fadeIn("slow");
		});
		$(".invite-friend").off("click").click(function(e){
			$(".invite-friend").fadeOut("slow");
		})
		$(".invite-friend-content").off("click").click(function(e){
			e.stopPropagation();
			$(".search-friend-list").fadeOut("slow");
		})
	
		$(".search-friend-list").off("click").click(function(e){
			e.stopPropagation();
		})
		
		$(".confirm-friend").off("click").click(function() {
			$(".invite-friend").fadeOut("slow");
		});
		

		$("#addButton").off("click").click(function(e) {
			if ($("#itineName").val().trim().length == 0) {
				e.preventDefault();
				$("#itineName-reminder").text("行程名稱不得為空白");
			}
		})
		
		$(".itine-name").off("click").click(function(){
			location.assign('<%=request.getContextPath()%>/front-end/itinery/editItine.jsp?itineNo=' + $(this).attr('itineNo'));
		})
		
		$(".invite a").off("click").click(function(){
			let thisItineNo = $(this).attr("itineno");
			$.ajax({
				url: "<%=request.getContextPath()%>/itinery/itineMember.do",
				type: "GET",
				data: {
					action:"getAllItineMember",
					itineNo: thisItineNo,
				},
				success: function(data){
// 					window.alert(data);
					$(".friend-list").html("");
					$(".friend-list").attr("itineno",thisItineNo);
					let memberList = JSON.parse(data);
					$.each(memberList,function(idx,itineMemberVO){
// 					window.alert(itineMemberVO.name);
						let friendTxt1 = `<div class="row one-friend my-2" memberNo="${'$'}{itineMemberVO.memberNo}"">
											<div class="col-6">${'$'}{itineMemberVO.name}</div>
											<div class="col-3">`;
						let friendTxt2A = 		`<select class="custom-select">
													<option value="1"${'$'}{itineMemberVO.isEditable===1?'selected':''}>僅可觀看</option>
													<option value="2"${'$'}{itineMemberVO.isEditable===2?'selected':''}>可編輯</option>
												</select>`;
						let friendTxt2B =   	   "尚未加入";						
												
						let friendTxt3=		`</div>
											<div class="col-3 row justify-content-center">
												<i class="fas fa-trash"></i>
											</div>
										</div>`;
						if(itineMemberVO.isEditable==0){				
							$(".friend-list").append(friendTxt1+friendTxt2B+friendTxt3);
						}else if(itineMemberVO.isEditable==1||itineMemberVO.isEditable==2){
							$(".friend-list").append(friendTxt1+friendTxt2A+friendTxt3);
						}
						init();
					});
				},
				error: function(){
					window.alert("failed!");
				},
			});
		});
		
		$(".one-friend select").off("change").change(function(){
			$.ajax({
				url: "<%=request.getContextPath()%>/itinery/itineMember.do",
				type: "POST",
				data: {
					action:"updateMemberIsEditable",
					itineNo: $(".friend-list").attr("itineno"),
					memberNo: $(this).parent().parent().attr("memberno"),
					isEditable: $(this).val(),
				},
				success: function(data){
// 					window.alert(data);
					swal("太棒了",data,"success");
				},
				error: function(){
					window.alert("failed");
				},
			});
		});
		
		$(".one-friend i").off("click").click(function(){
			let thisOneFriend = $(this).parent().parent();
			swal({
				  title: "你確定嗎?",
				  text: "你朋友會哭哭唷!",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					$.ajax({
						url: "<%=request.getContextPath()%>/itinery/itineMember.do",
						type: "POSt",
						data: {
							action:"fakeDeleteMember",
							itineNo: $(".friend-list").attr("itineno"),
							memberNo: $(this).parent().parent().attr("memberno"),
						},
						success: function(data){
// 							window.alert(data);
				    		swal("OK，你朋友被踢出了!", {
				      			icon: "success",
				    		});
							thisOneFriend.remove();
						},
						error: function(){
							window.alert("failed");
						},
				
					});
				  } else {
				    swal("別擔心~","朋友還在拉!","info");
				  }
				});
			
			
		})
		
		$("#searchFriend").off("click").click(function(e){
			$(".search-friend-list").fadeIn("slow").html("");
			e.stopPropagation();
			
			$.ajax({
				url: "<%=request.getContextPath()%>/itinery/itineMember.do",
				type: "GET",
				data: {
					action:"searchFriend",
					memberNo: ${memberVO.memberNo},
					nameStr: $(this).parent().prev().val(),
				},
				success: function(data){
// 					window.alert(data);
					let memberList = JSON.parse(data);
					if(memberList.length===0){
						$(".search-friend-list").append("查無好友");
					}
					$.each(memberList, function(idx,memberVO){
						$(".search-friend-list").append(`<div class="row one-friend my-2" memberNo="${'$'}{memberVO.memberNo}">
													<div class="col-6 friend-name" >${'$'}{memberVO.name}</div>
													<div class="col-6 invite-this-friend">
														<button class="btn btn-primary">加入</button>
													</div>
												</div>`);
						
						$(".friend-list .one-friend").each(function(){
							console.log("memberVO.memberNo: "+memberVO.memberNo);
							console.log("$(this).attr('memberno'): "+$(this).attr('memberno'));
							if(memberVO.memberNo==$(this).attr("memberno")){
								console.log("bingo");
								$("[memberno="+memberVO.memberNo+"] .btn").prop("disabled",true);
							}
						});
					});
					init();
					
				},
				error: function(){
					window.alert("failed");
				},
			});
			
		});
		
		$(".invite-this-friend>button").off("click").click(function(){
			let memberNo = $(this).parent().parent().attr("memberno");
			let name = $(this).parent().prev().text();
			let thisOneFriend = $(this).parent().parent();
			let itineName = $(".itine-name[itineno="+$('.friend-list').attr('itineno')+"]").text();
			$.ajax({
				url: "<%=request.getContextPath()%>/itinery/itineMember.do",
				type: "POST",
				data: {
					action:"addItineMember",
					itineNo: $(".friend-list").attr("itineno"),
					memberNo: memberNo,
				},
				success: function(data){
					swal("太棒了!",data,"success");
					$(this).prop("disabled",true);
					
					let friendTxt = `<div class="row one-friend my-2" memberNo=${'$'}{memberNo}>
										<div class="col-6">${'$'}{name}</div>
										<div class="col-3">尚未加入</div>
										<div class="col-3 row justify-content-center">
											<i class="fas fa-trash"></i>
										</div>
									</div>`;
					$(".friend-list").append(friendTxt);
					thisOneFriend.remove();
					init();
					
// 					還要再將此通知存入資料庫
					$.ajax({
						url: "<%=request.getContextPath()%>/itinery/itineMember.do",
						type: "POST",
						data: {
							action:"notifyItineMember",
							notifyPerson: memberNo, //收通知對象
							notifyContent: "${memberVO.name}邀請您參加"+itineName,
							
						},
						success:function(data){
							swal("了解",data,"info");
						},
						error:function(){
							window.alert("存入通知failed");
						}
						
					});
					sendMessage("aboutItineMember",memberNo);
					
					
				},
				error: function(){
					window.alert("加入團員failed");
				},
			});
			
			
		
		});
		
		$(".declineItineBtn").off("click").click(function(e){
			e.preventDefault();
			//再將通知存入資料庫
			let builder = $(this).parent().parent().prev().prev().attr("builder");
			let itineName = $(this).parent().parent().parent().prev().text();
			console.log("builder: "+builder);
			console.log("itineName: "+itineName);
			$.ajax({
					url: "<%=request.getContextPath()%>/itinery/itineMember.do",
					type: "POST",
					data: {
						action:"notifyBuilderNo",
						notifyPerson: builder, //收通知對象
						notifyContent: "${memberVO.name}已拒絕了"+itineName,							
					},
					success:function(data){
// 						alert(data);
						swal("好的","已經拒絕此邀約","info");
						sendMessage("aboutItineMember",builder);
					},
					error:function(){
						window.alert("存入通知failed");
					}
			});
			let thisForm = $(this).parent();
			let d = setTimeout(function(){
			thisForm.submit();
			},1500);
			
		});
		$(".acceptItineBtn").off("click").click(function(e){
			e.preventDefault();
			//再將通知存入資料庫
			let builder = $(this).parent().parent().prev().attr("builder");
			let itineName = $(this).parent().parent().parent().prev().text();
			$.ajax({
					url: "<%=request.getContextPath()%>/itinery/itineMember.do",
					type: "POST",
					data: {
						action:"notifyBuilderYes",
						notifyPerson: builder, //收通知對象
						notifyContent: "${memberVO.name}已參加了"+itineName,							
					},
					success:function(data){
// 						alert(data);
						swal("太棒了","已經加入此行程","success");
						sendMessage("aboutItineMember",builder);
					},
					error:function(){
						window.alert("存入通知failed");
					}
			});
			let thisForm = $(this).parent();
			let a = setTimeout(function(){
			thisForm.submit();
			},1500);
			
		});
		
		
		}
	});
</script>
</head>

<body>
	<!-- Topbar -->
	<jsp:include page="/front-end/myNavBar.jsp"/>

	<!-- End of Topbar -->
	<!-- Start of page content-->
	<div class="container page-content ">
		<div class="row justify-content-center">
			<div
				class="col-12 border border-primary border-2 rounded mb-5 p-3 text-center new-file"
				data-toggle="modal" data-target="#addItien">+ 新增行程</div>
			<!-- 行程Modal -->
			<div class="modal fade" id="addItien" tabindex="-1"
				aria-labelledby="addItineModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="addItineModalLabel">請輸入行程名稱</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/itinery/itine.do" name="form1">
							<div class="modal-body">
								<input type="text" name="itineName" id="itineName"
									class="form-control"
									aria-describedby="inputGroup-sizing-default"> <input
									type="hidden" name="action" value="addTest">
								<div id="itineName-reminder"
									style="font-size: 14px; color: red; height: 14px"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">取消</button>
								<button type="submit" class="btn btn-primary" id="addButton">確定</button>
							</div>
						</FORM>
					</div>
				</div>
			</div>
		</div>
		<div class="filelist row justify-content-center">
			<jsp:useBean id="memberSvc" scope="page"
				class="com.member.model.MemberService" />
			<jsp:useBean id="itineMemberSvc1" scope="page"
				class="com.itineMember.model.ItineMemberService" />
			<c:forEach var="itineVO" items="${list}">
				<c:if
					test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)!=3}">
					<c:if test="${itineVO.itineStatus==0}">
						<div
							class="file col-12 border border-primary rounded mb-2 p-3 d-flex ">
							<div class="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)==0?'':'itine-name'}
								 w-50" itineNo="${itineVO.itineNo }">${itineVO.itineName}</div>
							<div class="right-side w-50 ml-auto d-flex">
								<div class="builder w-25 " builder="${itineVO.builder }">
									<div>創建人:</div>
									<div class="text-right" >${memberSvc.getOneMember(itineVO.builder).name}</div>
								</div>
								<div class="finish w-25 ml-1">
									<c:if test="${memberVO.memberNo==itineVO.builder}">
										<form action="<%=request.getContextPath()%>/itinery/itine.do">
											<input type="hidden" name="action" value="finishItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }">
											<button class="btn btn-primary p-1 " type="submit" value="">完成行程</button>
										</form>
									</c:if>

									<c:if
										test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)==0}">
										<form action="<%=request.getContextPath()%>/itinery/itineMember.do">
											<input type="hidden" name="action" value="acceptItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }"> <input type="hidden"
												name="memberNo" value="${memberVO.memberNo }">
											<button class="btn btn-primary p-1 acceptItineBtn"
												type="submit" value="">確認加入</button>
										</form>
									</c:if>
								</div>
								<div class="invite w-25 ml-1">
									<c:if test="${memberVO.memberNo==itineVO.builder}">
										<a class="btn btn-primary p-1" href="#" role="button"
											itineNo="${itineVO.itineNo }">邀請好友</a>
									</c:if>
									<c:if
										test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)==0}">
										<form action="<%=request.getContextPath()%>/itinery/itineMember.do">
											<input type="hidden" name="action" value="declineItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }"> <input type="hidden"
												name="memberNo" value="${memberVO.memberNo }">
											<button class="btn btn-primary p-1 declineItineBtn"
												type="submit" value="">拒絕加入</button>
										</form>
									</c:if>
								</div>
								<div class="update-date w-25 ">
									<div>最後更新時間:</div>
									<div>${itineVO.updateTime}</div>
								</div>
								<c:if test="${memberVO.memberNo==itineVO.builder}">
									<div class="delete-itinery">
										<input type="hidden" name="itineNo"
											value="${itineVO.itineNo }"> <i class="fas fa-trash"></i>
									</div>
								</c:if>
							</div>
						</div>
					</c:if>
					<c:if test="${itineVO.itineStatus==1}">
						<div
							class="file col-12 border border-primary rounded mb-2 p-3 d-flex ">
							<div class="w-50" itineNo="${itineVO.itineNo }">${itineVO.itineName}</div>
							<div class="right-side w-50 ml-auto d-flex">
								<div class="builder w-25 " builder="${itineVO.builder }">
									<div>創建人:</div>
									<div class="text-right" >${memberSvc.getOneMember(itineVO.builder).name}</div>
								</div>
								<div class="cancel-finish w-25 ml-1">
									<c:if test="${memberVO.memberNo==itineVO.builder}">
										<form action="<%=request.getContextPath()%>/itinery/itine.do">
											<input type="hidden" name="action" value="unfinishItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }">
											<button class="btn btn-primary p-1" type="submit" value="">取消完成</button>
										</form>
									</c:if>
									<c:if
										test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)==0}">
										<form action="<%=request.getContextPath()%>/itinery/itineMember.do">
											<input type="hidden" name="action" value="acceptItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }"> <input type="hidden"
												name="memberNo" value="${memberVO.memberNo }">
											<button class="btn btn-primary p-1 acceptItineBtn"
												type="submit" value="">確認加入</button>
										</form>
									</c:if>
								</div>
								<div class="track w-25 ml-1">
									<c:if
										test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)!=0}">
										<a class="btn btn-primary p-1"
											href="<%=request.getContextPath()%>/front-end/itinery/trackItine.jsp?itineNo=${itineVO.itineNo }"
											role="button"> 分工追蹤</a>
									</c:if>
									<c:if
										test="${itineMemberSvc1.thisMemberIsEditable(itineVO.itineNo,memberVO.memberNo)==0}">
										<form action="<%=request.getContextPath()%>/itinery/itineMember.do">
											<input type="hidden" name="action" value="declineItine">
											<input type="hidden" name="itineNo"
												value="${itineVO.itineNo }"> <input type="hidden"
												name="memberNo" value="${memberVO.memberNo }">
											<button class="btn btn-primary p-1 declineItineBtn"
												type="submit" value="">拒絕加入</button>
										</form>
									</c:if>
								</div>
								<div class="update-date w-25 ">
									<div>最後更新時間:</div>
									<div>${itineVO.updateTime}</div>
								</div>
								<c:if test="${memberVO.memberNo==itineVO.builder}">
									<div class="delete-itinery">
										<input type="hidden" name="itineNo"
											value="${itineVO.itineNo }"> <i class="fas fa-trash"></i>
									</div>
								</c:if>
							</div>
						</div>
					</c:if>
					<c:if test="${itineVO.itineStatus==3}">
						<div
							class="file col-12 border border-primary rounded mb-2 p-3 d-flex ">
							<div class="itine-name w-50" itineNo="${itineVO.itineNo }">${itineVO.itineName}</div>
							<div class="right-side w-50 ml-auto d-flex">
								<div class="builder w-25 " builder="${itineVO.builder }">
									<div>創建人:</div>
									<div class="text-right" >${memberSvc.getOneMember(itineVO.builder).name}</div>
								</div>
								<div class="gone w-50 ml-1 text-center mt-1">
									<i><b>已出遊</b></i>
								</div>
								<div class="update-date w-25 ">
									<div>最後更新時間:</div>
									<div>${itineVO.updateTime}</div>
								</div>
							</div>
						</div>
					</c:if>
				</c:if>
			</c:forEach>

		</div>
	</div>
	<div class="invite-friend container-fluid  justify-content-center">
		<div
			class="invite-friend-content col-6  border border-2 border-primary bg-light">

			<div class="input-group m-3" style="width: 100%;">
				<input type="text" class="form-control" placeholder="請輸入好友名稱"
					aria-label="Friend's username" aria-describedby="searchFriend">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button"
						id="searchFriend">搜尋</button>
				</div>
			</div>

			<div class="friend-list col-9 border-2 border mx-auto">
				
			</div>
			<div class="row justify-content-end confirm-friend">
				<a class="btn btn-primary  mr-5 mt-4" href="#" role="button">確定</a>
			</div>
		</div>

		<div class="search-friend-list"></div>
	</div>



</body>

</html>