<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO" />
<jsp:useBean id="memberSvc" scope="session" class="com.member.model.MemberService" />
<jsp:useBean id="friendListSvc" class="com.friendList.model.FriendListService" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- <link rel="stylesheet" href="css/friendchat.css" type="text/css" /> -->
<title>聊天室</title>
<%@ include file="/front-end/footer/footer_lib.file" %> 
<script src="<%=request.getContextPath()%>/resources/js/moment-with-locales.js"></script>
<style>
* {
	box-sizing: border-box;
}

body {
    margin: 0;
}
.zz {
	border: 1px solid blue;
	margin:0 0 10px 0;
}
ul li { 
	display: inline-block; 
	clear: both; 
/* 	padding: 20px;  */
/* 	border-radius: 30px;  */
/* 	margin-bottom: 2px;  */
/* 	font-family: Helvetica, Arial, sans-serif;  */
} 
#statusOutput{
	position: sticky;
	top:0;
	background-color: #DAF7A6;
}
.input-area {
	position: sticky;
	bottom: 0;
}
.chatBox {
	height: 500px;
	overflow-y: auto;
}
#area{
	padding: 0
}
.me{
	float: right; 
 	background: #0084ff;
 	color: #fff;
 	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
	border-top-left-radius: 5px;
	border-bottom-left-radius: 5px;
}
.me .msgContent{
	float: right; 
}

.friend{
    display: block;
/*     border: 1px solid black; */
}

.timeStyle{
	display: none;
	font-size: 8px;
	color: gray;
	clear: both; 
}
.showMsgTime{
	display: block;
}

</style>

<script>
// $(document).ready(function () {
    function showM() {
		$('#messagesArea #area .me, #messagesArea #area .friend').hover(function () {
			$(this).find('.timeStyle').addClass('showMsgTime');
		}, function () {
			$(this).find('.timeStyle').removeClass('showMsgTime');
		});
	}
// });
</script>

</head>
<body onload="connect();" onunload="disconnect();">
	<div class="container">
        <div class="row">
            <div class="col-12 col-md-3 xx"></div>
            <div class="col-12 col-md-6 xx zz">
            	<div class="chatBox">
	                <h3 id="statusOutput" class="statusOutput"></h3>
	                <div class="yy" id="messagesArea">messagesArea</div>
	                <div class="panel input-area yy">
	                    <div class="input-group mb-3">
	                        <input type="text" id="message" class="form-control" placeholder="Type Message"
	                            aria-label="Recipient's username" aria-describedby="sendMessage" onkeydown="if (event.keyCode == 13) sendMessage();">
	                        <div class="input-group-append">
	                            <button class="btn btn-outline-secondary" type="button" id="sendMessage" onclick="sendMessage();">Send</button>
	                        </div>
	                    </div>
	                    <!-- <input id="message" class="text-field" type="text" placeholder="Message"
	                        onkeydown="if (event.keyCode == 13) sendMessage();" />
	                    <input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" /> -->
	                    <!-- 		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" />  -->
	                    <!-- 		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" /> -->
	                </div>
                </div>
            </div>
            <div class="col-12 col-md-3 xx"></div>
        </div>

    </div>
<!-- 	<h3 id="statusOutput" class="statusOutput"></h3> -->
<!-- 	<div id="row"></div> -->
<!-- 	<div id="messagesArea" class="panel message-area" ></div> -->
<!-- 	<div class="panel input-area"> -->
<!-- 		<input id="message" class="text-field" type="text" placeholder="Message" onkeydown="if (event.keyCode == 13) sendMessage();" />  -->
<!-- 		<input type="submit" id="sendMessage" class="button" value="Send" onclick="sendMessage();" />  -->
<!-- 		<input type="button" id="connect" class="button" value="Connect" onclick="connect();" />  -->
<!-- 		<input type="button" id="disconnect" class="button" value="Disconnect" onclick="disconnect();" /> -->
<!-- 	</div> -->
</body>
<script>
	var FriendMyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var friendPath = window.location.pathname;
	var friendWebCtx = friendPath.substring(0, friendPath.indexOf('/', 1));
	var friendeEndPointURL = "ws://" + window.location.host + friendWebCtx + FriendMyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var chatMemSelf = '${userName}';
	var friendWebSocket;

	function connect() {
		// create a websocket
		friendWebSocket = new WebSocket(friendeEndPointURL);

		friendWebSocket.onopen = function(event) {
			console.log("Connect Success!");
// 			document.getElementById('sendMessage').disabled = false;
// 			document.getElementById('connect').disabled = true;
// 			document.getElementById('disconnect').disabled = false;
		};

		friendWebSocket.onmessage = function(event) {
// 			debugger;
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				statusOutput.innerHTML = '${friendUserName}';
				var jsonObj = {
						"type" : "history",
						"sender" : chatMemSelf,
						"receiver" : '${friendUserName}',
						"message" : ""
					};
				friendWebSocket.send(JSON.stringify(jsonObj));
// 				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
// 				debugger;
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var timeStamp = historyData.timeStamp;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === chatMemSelf ? li.className += 'me' : li.className += 'friend';
					li.innerHTML = "<div class='msgContent'>"+showMsg+"</div><div class='timeStyle'>"+timeStamp+"</div>";
					ul.appendChild(li);
// 					$('.timeStyle').css({'font-size': '8px', 'color': 'gray'});
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
				showM();
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === chatMemSelf ? li.className += 'me' : li.className += 'friend';
// 				li.innerHTML = jsonObj.message+"<br><div class='timeStyle' style='font-size: 8px; color: gray;'>"+jsonObj.timeStamp+"</div>";
				li.innerHTML = "<div class='msgContent'>"+jsonObj.message+"</div><div class='timeStyle'>"+jsonObj.timeStamp+"</div>";
// 				$('.timeStyle').css({'font-size': '8px', 'color': 'gray'});
// 				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
				showM();
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}
			
		};

		friendWebSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	
	function sendMessage() {
		console.log("chatNew sendMessage...");
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : chatMemSelf,
				"receiver" : '${friendUserName}',
				"message" : message,
				"timeStamp" : moment(new Date()).format("YYYY-MM-DD, H:mm:ss")
			};
// 			console.log("chatNew sendMessage...before send");
			friendWebSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}
	
	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
// 		debugger;
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i].name === chatMemSelf) { continue; }
			row.innerHTML +='<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>' + friends[i] + '</h2></div>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
// 		debugger;
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
					"type" : "history",
					"sender" : chatMemSelf,
					"receiver" : '${friendUserName}',
					"message" : ""
				};
			friendWebSocket.send(JSON.stringify(jsonObj));
		});
	}
	
	function disconnect() {
		friendWebSocket.close();
// 		document.getElementById('sendMessage').disabled = true;
// 		document.getElementById('connect').disabled = false;
// 		document.getElementById('disconnect').disabled = true;
	}
	
	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</html>