<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.member.model.*"%>
<%@page import="com.friendList.model.*"%>

<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO" />
<jsp:useBean id="memberSvc" scope="session" class="com.member.model.MemberService" />
<jsp:useBean id="friendListSvc" class="com.friendList.model.FriendListService" />
--memberVO.memberNo--${memberVO.memberNo}

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
	.friendList {
            width: 500px;
            border: 1px solid #f00;
            margin-top: 80px;
            margin-left: auto;
            margin-right: auto;
        }
</style>
<title>Insert title here</title>
</head>
<body>
	<c:import url="/front-end/myNavBar.jsp"></c:import>
	<div class="friendList">
		<table id="friendTable">
			<thead>搜尋會員
			</thead>
			<tr>
				<td colspan="2">
						<input type="text" name="searchMember" id="searchMember" placeholder="搜尋會員"> 
				</td>
			</tr>
		</table>
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
					clearFriend();
					$(data).each(function(i, item){
						console.log("memberNo:"+item.memberNo);
						console.log("name:"+item.name);
						console.log("IMG:"+item.imgTag);
						console.log("addFriend:"+item.addFriend);
						console.log("friendTag:"+item.friendTag);
						console.log("#########queryFriend############");
						$('#friendTable').append("<tr><td><div class='addFriendItems'> "+item.imgTag+" "+item.memberNo+" "+item.name
								+" <input type='hidden' name='"+item.memberNo+"' id='"+item.memberNo+"'> "
								+(item.addFriend != null ? item.addFriend : item.friendTag)+" </div></td></tr>");
								/* +(item.addFriend != null ? item.addFriend : item.friendTag)+" </div></td></tr>"); */
						
						/* $("#getOneFriendLoginNo").val('${memberVO.memberNo}');
						$("#getOneFriendMemNO").val(item.memberNo); */
						
					});
					/* $(this).find('input:last').attr('id') */
					showOneMemberInfo();
				},
				error: function(){alert("AJAX-發生錯誤囉!")}
			});
		});
	});
	
	function clearFriend(){
		for (let index = $("#friendTable tr").length-1; index >= 1; index--) {
            $("#friendTable tr")[index].remove();
        }
	}
	
	function showOneMemberInfo(){
		$(".addFriendItems").click(function(){
			clearOneMemberInfo();
			alert($(this).find('input:last').attr('id'));
			$("#getOneFriendLoginNo").val('${memberVO.memberNo}');
			$("#getOneFriendMemNO").val($(this).find('input:last').attr('id'));
			$("#isReady").val('yes');
			$('#showOneMemberInfoModal').modal('show');
			loadOneFriend();
		});
	}
	
	function clearOneMemberInfo(){
		alert("#memberInfo tr length:"+$("#memberInfo tr").length);
		for (let index = $("#memberInfo tr").length-1; index >= 0; index--) {
            $("#memberInfo tr")[index].remove();
        }
	}
	
	$('#showOneMemberInfoModal').modal({show:false});
	
	</script>

</body>
</html>