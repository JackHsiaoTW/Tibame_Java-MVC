<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.notify.model.*"%>
<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO"/>
<jsp:useBean id="notifySvc" scope="request" class="com.notify.model.NotifyService"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listOneNotify</title>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<div id="oneNotifyInfo">
<!-- 		<input type='hidden' name='getOneNotifyLoginNo' id='getOneNotifyLoginNo' value="0"> -->
		<input type='hidden' name='notifyNo' id='notifyNo' value="0">
		<input type='hidden' name='isReady' id='isReady' value="no">
	</div>
	
	<script>
	function loadoneNotifyInfo(){
// 		debugger;
// 		if($("#isReady").val() === "yes"){
			$.ajax({
				url : "<%=request.getContextPath()%>/notify/notifyServlet.do",
				type : "POSt",
				data : {
					action : "listOneNotify",
					notifyNo : $('#notifyNo').val(),
				},
				dataType: "json",
				success : function(data) {
					alert("AJAX-成功_loadoneNotifyInfo");
					$(data).each(function(i, item){
						console.log("notifyNo:"+item.notifyNo);
						console.log("notifyPerson:"+item.notifyPerson);
						console.log("notifyContent:"+item.notifyContent);
						console.log("notifyTime:"+item.notifyTime);
						console.log("###########################");
// 						$('#memberInfo').append("<tr><td>"+item.imgTag+" "+item.showLabel
// 							+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> "
// 							+" "+item.addFriendInvite+"</td><td>"+(item.unfriend != null ? item.unfriend : "")+"</td></tr>");
// 						alert(item.isInvitedOrIsFriend);
// 						(item.isInvitedOrIsFriend === 'yes' ? $("#addFriendInviteBtn").attr('disabled', true) : $("#addFriendInviteBtn").attr('disabled', false));
					
// 						addFriendInviteEvent();
					});
				},
				error: function(){alert("AJAX-發生錯誤囉!_loadoneNotifyInfo")}
			});
// 		};
	}
	</script>
</body>
</html>