<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%@page import="com.friendList.model.*"%>

<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO" />
<jsp:useBean id="memberSvc" scope="session" class="com.member.model.MemberService" />
<jsp:useBean id="friendListSvc" class="com.friendList.model.FriendListService" />
<jsp:useBean id="notifySvc" scope="request" class="com.notify.model.NotifyService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

     <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>

<style>

#friendInfos{
	width: 500px;
 	margin: 80px auto auto;
}

/* .fa-heart { */
/*     color: red; */
/*      text-shadow: 1px 1px 1px #ccc; */
/*      font-size: 1.5em; */
/* } */

/* ul#allFriendInfo { */
/*     margin-top: 80px; */
/* } */
</style>
<title>Friend Info</title>
<%-- <%@ include file="/front-end/footer/footer_lib.file" %>  --%>
</head>
<body>
<c:import url="/front-end/myNavBar.jsp"></c:import>
<%-- <%@ include file="/front-end/footer/navbar.file" %>  --%>

<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
	<!-- ========================== -->
	<div id="friendInfos">
	<ul class="nav nav-tabs" id="allFriendInfo" role="tablist">
        <li class="nav-item" role="presentation">
            <a class="nav-link active" id="friendList_tab" data-toggle="tab" href="#friendList" role="tab" aria-controls="friendList"
                aria-selected="true">好友列表</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="serchMember_tab" data-toggle="tab" href="#serchMember" role="tab" aria-controls="serchMember"
                aria-selected="false">搜尋會員</a>
        </li>
        <li class="nav-item" role="presentation">
            <a class="nav-link" id="friendInvitation_tab" data-toggle="tab" href="#friendInvitation" role="tab" aria-controls="friendInvitation"
                aria-selected="false">交友邀請</a>
        </li>
    </ul>
    <div class="tab-content" id="allFriendInfoTabContent">
        <div class="tab-pane fade show active" id="friendList" role="tabpanel" aria-labelledby="friendList_tab">
            <table class="table table-striped" id="friendTable">
                <tr>
                    <td colspan="2">
                        <input type="text" name="searchFriend" id="searchFriend" placeholder="搜尋好友">
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="serchMember" role="tabpanel" aria-labelledby="serchMember_tab">
            <table class="table table-striped" id="searchMemberTable">
                <tr>
                    <td colspan="2">
                        <input type="text" name="searchMember" id="searchMember" placeholder="搜尋會員">
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="friendInvitation" role="tabpanel" aria-labelledby="friendInvitation_tab">
            <table class="table table-striped" id="friendInvitTable">
                <tr>
                    <td colspan="2"></td>
                </tr>
            </table>
        </div>
    </div>
    </div>
<!-- ========================== -->
	<div class="modal fade" id="showOneMemberInfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">會員資訊_getOneFriend</h5>
					<button class="close" type="button" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<jsp:include page="/front-end/friendList/getOneFriend.jsp" />
				</div>
			</div>
		</div>
	</div>
<!-- ========================== -->
	
<script type="text/javascript">
	$(document).ready(function(){
// 		------------------------------好友列表------------------------------
		getFriendListF();
	
		function getFriendListF(){
			$.ajax({
				url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
				type : "POSt",
				data : {
					action : "listAllFriend",
					memNO : '${memberVO.memberNo}'
				},
				dataType: "json",
				success : function(data) {
					clearFriend();
					$(data).each(function(i, item){
// 						console.log("memberNo:"+item.memberNo);
// 						console.log("name:"+item.name);
// 						console.log("IMG:"+item.imgTag);
// 						console.log("#########init#############");
// 						$('#friendTable').append("<tr><td><div class='friendListItems'> "+item.imgTag+" "+item.name
// 							+" <input type='hidden' name='friendNo' id='friendNo' value='"+item.memberNo+"'> </div></td>"
						$('#friendTable').append("<tr><td><div class='friendListItems'> "
							+" <form method='post' action='<%=request.getContextPath()%>/message/webSocket.do'>"
							+item.imgTag+" "+item.name
							+" <input type='hidden' name='action' value='friendChat'>"
							+" <input type='hidden' name='friendNo' class='friendNo' value='"+item.memberNo+"'> </form></div></td>"
							
							+" <td>"+ item.unfriend+"</td></tr>");
// 						$('#friendTable').append("<tr><div class='friendListItems'><td>"+item.imgTag+" "+item.name+"</td>"
// 								+" <td>"+ item.unfriend+"</td>"
// 								+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> </div></tr>");
					});
					
					//---showOneMemberInfo
// 					showOneMemberInfo();
					unfriendBtnEvent();
					clickToChat();
				},
				error: function(){alert("AJAX-發生錯誤囉!")}
			});
		}
		
		// 	點選好友列表時，重新查詢好友
		$('ul#allFriendInfo li').eq(0).click(function() {
			$('#searchFriend').val('');  // 請除searchFriend欄位值
			getFriendListF();
		});
		
		// 在好友列表的輸入框搜尋時，即時查詢資料	
		$("#searchFriend").keyup(function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
				type : "POSt",
				data : {
					searchFriend : $("#searchFriend").val(),
					action : "queryFriend",
					memNO : '${memberVO.memberNo}'
				},
				dataType: "json",
				success : function(data) {
					clearFriend();
					$(data).each(function(i, item){
// 						console.log("memberNo:"+item.memberNo);
// 						console.log("name:"+item.name);
// 						console.log("IMG:"+item.imgTag);
// 						console.log("#########queryFriend############");
						$('#friendTable').append("<tr><td><div class='friendListItems'> "+item.imgTag+" "+item.name
								+" <input type='hidden' name='friendNo' class='friendNo' value='"+item.memberNo+"'> </div></td>"
								+" <td>"+ item.unfriend+"</td></tr>");
// 						$('#friendTable').append("<tr><td>"+item.imgTag+" "+item.name
// 								+" <td>"+ item.unfriend+"</td>"
// 								+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> </td></tr>");
					});
					unfriendBtnEvent();
				},
				error: function(){alert("AJAX-發生錯誤囉!")}
			});
		});
		
		function clearFriend(){
			for (let index = $("#friendTable tr").length-1; index >= 1; index--) {
	            $("#friendTable tr").eq(index).remove();
	        }
		}
		
		function unfriendBtnEvent(){
			$("#friendTable tr td #unfriendBtn").click(function (){
// 				alert("friendNo:"+$(this).closest('tr').find('input#friendNo').val());
				$.ajax({
					url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
					type : "POSt",
					data : {
						action : "unfriend",
						loginNo : '${memberVO.memberNo}',
						friendNo : $(this).closest('tr').find('input.friendNo').val()
					},
					dataType: "json",
					success : function(data) {
						$(data).each(function(i, item){
// 							console.log("friendName:"+item.friendName);
// 							console.log("result:"+item.result);
// 							$("#allFriendInfo").before(`<div class='alert alert-warning alert-dismissible fade show' role='alert'>
// 										<div id='alert_content'>已成功解除與${'${item.friendName}'}好友關係</div>
// 										<button type='button' class='close' data-dismiss='alert' aria-label='Close'>
// 											<span aria-hidden='true'>&times;</span>
// 										</button>
// 									</div>`);
							toastr.warning(`已成功解除與${'${item.friendName}'}好友關係`);
						});
						getFriendListF();
					},
					error: function(){alert("AJAX-發生錯誤囉!")}
				});
			});
		}
		
		//點選好友來聊天
		function clickToChat(){
			$('#friendTable tr td .friendListItems').click(function(){
// 				alert($(this).find('form').submit());
				$(this).find('form').submit();
			});
		}
		
// 		------------------------------搜尋會員------------------------------
		// 	點選搜尋會員時，清除之前的搜尋紀錄
		$('ul#allFriendInfo li').eq(1).click(function() {
			$('#searchMember').val('');  // 請除searchFriend欄位值
			clearMember();
		});

		$("#searchMember").keyup(function() {
			$.ajax({
				url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
				type : "POSt",
				data : {
					searchMember : $("#searchMember").val(),
					action : "queryMemberForAddFriend",
					memNO : '${memberVO.memberNo}'
				},
				dataType: "json",
				success : function(data) {
					clearMember();
					$(data).each(function(i, item){
// 						console.log("memberNo:"+item.memberNo);
// 						console.log("name:"+item.name);
// 						console.log("IMG:"+item.imgTag);
// 						console.log("addFriend:"+item.addFriend);
// 						console.log("friendTag:"+item.friendTag);
// 						console.log("#########queryFriend############");
						$('#searchMemberTable').append("<tr><td><div class='addFriendItems'> "+item.imgTag+" "+item.memberNo+" "+item.name
								+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> "
								+(item.addFriend != null ? item.addFriend : item.friendTag)+" </div></td></tr>");
					});
					showOneMemberInfo();
				},
				error: function(){alert("AJAX-發生錯誤囉!")}
			});
		});
	});
	
	function clearMember(){
		for (let index = $("#searchMemberTable tr").length-1; index >= 1; index--) {
            $("#searchMemberTable tr")[index].remove();
        }
	}
	
	function showOneMemberInfo(){
		$(".addFriendItems").click(function(){
			clearOneMemberInfo();
// 			alert($(this).find('input:last').attr('id'));
			$("#getOneFriendLoginNo").val('${memberVO.memberNo}');
			$("#getOneFriendMemNO").val($(this).find('input:last').attr('id'));
			$("#isReady").val('yes');
			$('#showOneMemberInfoModal').modal('show');
			loadOneFriend();
		});
	}
	
	function clearOneMemberInfo(){
// 		alert("#memberInfo tr length:"+$("#memberInfo tr").length);
		for (let index = $("#memberInfo tr").length-1; index >= 0; index--) {
            $("#memberInfo tr")[index].remove();
        }
	}
	
	$('#showOneMemberInfoModal').modal({show:false});
	
// 	------------------------------交友邀請------------------------------
	$("#friendInvitation_tab").click(function() {
		loadFriendInvite();
	});
	
	function loadFriendInvite(){
		$.ajax({
			url : "<%=request.getContextPath()%>/friendInvit/friendInvitServlet.do",
			type : "POSt",
			data : {
				action : "getFriendInvitByMemberNo",
				memNo : '${memberVO.memberNo}'
			},
			dataType: "json",
			success : function(data) {
				cleanFriendInviteInfo();
				$(data).each(function(i, item){
// 					console.log("friendInvitNo:"+item.friendInvitNo);
// 					console.log("adder:"+item.adder);
// 					console.log("confirmer:"+item.confirmer);
// 					console.log("adderNo:"+item.adderNo);
// 					console.log("adderName:"+item.adderName);
// 					console.log("IMG:"+item.imgTag);
// 					console.log("comfirmToFriend:"+item.comfirmToFriend);
// 					console.log("#########getFriendInvitByMemberNo############");
					$('#friendInvitTable').append("<tr><td><div class='comfirmToFriendItems'> "+item.imgTag+" "+item.adderNo+" "+item.adderName
							+" <input type='hidden' name='adder' id='adder' value='"+item.adder+"'> "
							+" <input type='hidden' name='confirmer' id='confirmer' value='"+item.confirmer+"'> "
							+" <input type='hidden' name='friendInvitNo' id='friendInvitNo' value='"+item.friendInvitNo+"'> "
							+ item.comfirmToFriend+" </div></td></tr>");
				});
				cofirmFriendInviteEvent();
			},
			error: function(){alert("AJAX-發生錯誤囉!")}
		});
	}
	
	function cofirmFriendInviteEvent(){
		$(".comfirmToFriendItems #comfirmToFriendBtn").click(function(){
// 			debugger;
// 			console.log($(this).parent());
// 			alert("LoginNo:"+$(this).parent().find('input#confirmer').val());
// 			alert("friendNO:"+$(this).parent().find('input#adder').val());
// 			alert("friendInvitNo:"+$(this).parent().find('input#friendInvitNo').val());
			let notifyPerson = $(this).parent().find('input#adder').val();
			$.ajax({
				url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
				type : "POSt",
				data : {
					action : "addFriend",
					loginNo : $(this).parent().find('input#confirmer').val(),
					friendNO : $(this).parent().find('input#adder').val(),
					friendInvitNo : $(this).parent().find('input#friendInvitNo').val()
				},
				dataType: "json",
				success : function(data) {
// 					alert("AJAX-成功_addFriendInviteBtn!");
					$("#addFriendInviteBtn").attr('disabled', true);
					$(data).each(function(i, item){item.result});
// 					console.log("------------");
// 					console.log($(this).parent());
					loadFriendInvite();
					toastr.warning("與"+data[0].friendName+"已成為好友");
					sendMessage("acceptFriendInvitation", parseInt(notifyPerson)); //確認好友後，用webSocket通知對方
				},
				error: function(){alert("AJAX-發生錯誤囉addFriendInviteBtn!")}
			});
		});
	}
	
	function cleanFriendInviteInfo(){
		for (let index = $("#friendInvitTable tr").length-1; index >= 0; index--) {
            $("#friendInvitTable tr")[index].remove();
        }
	}
	
	</script>

</body>
</html>