<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script> -->
<!-- Bootstrap core JavaScript-->
<%-- <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script> --%>
<title>One Friend Info</title>
</head>
<body>
	<div id="memberInfo">
		${param.loginNo}<br>
		${param.memNO}
		<input type='hidden' name='getOneFriendLoginNo' id='getOneFriendLoginNo' value="0">
		<input type='hidden' name='getOneFriendMemNO' id='getOneFriendMemNO' value="0">
		<input type='hidden' name='isReady' id='isReady' value="no">
	</div>
	
	<script type="text/javascript">
	function loadOneFriend(){
// 		debugger;
		if($("#isReady").val() === "yes"){
		$.ajax({
			url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
			type : "POSt",
			data : {
				action : "getOneMemInfo",
// 				loginNo : '${param.loginNo}',
// 				memNO : '${param.memNO}'
				loginNo : $("#getOneFriendLoginNo").val(),
				memNO : $("#getOneFriendMemNO").val() 
			},
			dataType: "json",
			success : function(data) {
// 				alert("AJAX-成功666!");
				$(data).each(function(i, item){
					console.log("memberNo:"+item.memberNo);
					console.log("name:"+item.name);
					console.log("IMG:"+item.imgTag);
					console.log("isInvitedOrIsFriend:"+item.isInvitedOrIsFriend);
					console.log("unfriend:"+item.unfriend);
					console.log("item.unfriend != null:"+item.unfriend != null);
					console.log("###########################");
					$('#memberInfo').append("<tr><td>"+item.imgTag+" "+item.showLabel
							+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> "
// 							+" "+(item.addFriend != null ? item.addFriend : "")+"</td></tr>");
// 							+" "+item.addFriendInvite+"</td><td>"+(item.unfriend != null ? item.unfriend : "")+"</td></tr>");
							+" "+item.addFriendInvite+"</td></td></tr>");
// 					alert(item.isInvitedOrIsFriend);
					(item.isInvitedOrIsFriend === 'yes' ? $("#addFriendInviteBtn").attr('disabled', true) : $("#addFriendInviteBtn").attr('disabled', false));
					
					addFriendInviteEvent();
				});
			},
			error: function(){alert("AJAX-發生錯誤囉!")}
		});
		}
		
		//---加好友事件
		function addFriendInviteEvent(){
// 			alert("addFriendInviteEvent");
			$("#addFriendInviteBtn").click(function(){
				$.ajax({
					url : "<%=request.getContextPath()%>/friendInvit/friendInvitServlet.do",
					type : "POSt",
					data : {
						action : "addFriendInvite",
// 						loginNo : '${param.loginNo}',
// 						memNO : '${param.memNO}'
						loginNo : $("#getOneFriendLoginNo").val(),
						memNO : $("#getOneFriendMemNO").val() 
						
					},
					dataType: "json",
					success : function(data) {
// 						alert("AJAX-成功_addFriendInviteBtn!");
						$("#addFriendInviteBtn").attr('disabled', true);
						$(data).each(function(i, item){item.result});
					},
					error: function(){alert("AJAX-發生錯誤囉addFriendInviteBtn!")}
				});
			});
		}
		
		//---解除好友事件
		function unfriendEvent(){
			alert("unfriendEvent");
			$("#unfriendBtn").click(function(){
				$.ajax({
					url : "<%=request.getContextPath()%>/friendList/friendListServlet.do",
					type : "POSt",
					data : {
						action : "unfriend",
// 						loginNo : '${param.loginNo}',
// 						memNO : '${param.memNO}'
						loginNo : $("#getOneFriendLoginNo").val(),
						memNO : $("#getOneFriendMemNO").val() 
						
					},
					dataType: "json",
					success : function(data) {
						alert("AJAX-成功_unfriendBtn!");
						$("#unfriendBtn").attr('disabled', true);
						$(data).each(function(i, item){item.result});
					},
					error: function(){alert("AJAX-發生錯誤囉unfriendBtn!")}
				});
			});
		}
	};
	</script>
	
	
</body>
</html>