<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.itine.model.*"%>
<%@ page import= "com.itine.controller.*"%>
<%@ page import="com.itineMember.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>

<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	Integer memberNo = new Integer(memberVO.getMemberNo());
	Integer itineNo = new Integer(request.getParameter("itineNo"));
	ItineService itineSvc = new ItineService();
	ItineVO itineVO = itineSvc.findByItineNo(itineNo);
	pageContext.setAttribute("itineVO", itineVO);
	ItineMemberService itineMemberSvc = new ItineMemberService();

	Integer answer = itineMemberSvc.thisMemberIsEditable(itineNo, memberNo);
	pageContext.setAttribute("answer", answer);
	
	List<List> msgList = JedisHandleBoardMessage.getHistoryMsg("itineID:"+itineNo.toString());
	pageContext.setAttribute("msgList",msgList);
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>editItine.jsp</title>

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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/jquery-ui.theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/nav-bar.css">


<!-- Bootstrap core JavaScript-->
<script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script> --%>
<script
	src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"
	integrity="sha256-0YPKAwZP7Mp3ALMRVB2i8GXeEndvCq3eSl/WsAl1Ryk="
	crossorigin="anonymous"></script>
<!-- Core plugin JavaScript-->
<script
	src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script
	src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.1/dist/tw-city-selector.min.js"></script>	
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
<style>
.input-group>#searchfor {
	width: 300px;
}

#itineName {
	height: 50px;
	margin-top: 5px;
	margin-bottom: 15px;
	color: #2E55C7;
	font-size: 30px;
	font-style: italic;
	font-weight: bold;
}

.attraction-list {
	height: 270px;
	overflow-y: auto;
}

.attraction {
	z-index: 2;
	height: 60px;
	width: 280px;
}

.ui-accordion-content {
	max-height: 200px;
}

.traffic {
	display: inline-block;
	width: 23%;
	z-index: 2;
}

.traffic:hover {
	cursor: pointer;
}

.traffic-box {
	white-space: nowrap;
	height: 35px;
}

.traffic-list {
	overflow-x: auto;
	height: 50px;
}

#fake-button:hover {
	cursor: default;
}
</style>

<!-- tab style-->
<style>

.ui-tabs-nav {
    background-color: whitesmoke;
    border: white;
}
.ui-tabs {
    position: relative;
    padding:0;
}
.ui-tabs .ui-tabs-panel{
	background:#c1d9ef;
}
#dialog label, #dialog input {
	display: block;
}

#dialog label {
	margin-top: 0.5em;
}

#dialog input, #dialog textarea {
	width: 95%;
}



#tabs li i.fa-times {
	float: left;
	margin: 0.4em 0.2em 0 0;
	cursor: pointer;
}

#add_tab {
	cursor: pointer;
}
</style>
<style>
.traffic:nth-child(even) {
	background-color: #C1E4F9;
}

.btns {
	height: 40px;
}

.right-side {
	height: 520px;
}

.msgboard-content {
	height: 190px;
	overflow-y: auto;
	background-color: #C1E4F9;
}

.itine-table {
	z-index: 0;
	background-color: #C1E4F9;
	border-radius: 5px;
}

.itine-time-content {
	height: 40px;
	border-top: 1px dashed gray;
	border-bottom: 1px dashed gray;
	width: 100%;
}

.itine-time-content p {
	/* width: 100%; */
	/* width:600px; */
	text-align: center;
	line-height: 40px;
}

.itine-time {
	height: 40px;
	position: relative;
	right: 14px;
}

.ui-state-highlight {
	position: relative;
}

.itine-item {
	position: relative;
	background-color: #2195E2;
	border-radius: 5px;
	text-align: center;
	line-height: 40px;
	height: 38px;
	width: 95%;
	margin: 0 auto;
}
.traffic-tool{
	background-color:#7d8fe6;
}

.delete-itine-item {
	position: absolute;
	z-index: 10;
	top: -30%;
	left: 97%;
	display: none;
	cursor: pointer;
}

.ui-resizable-helper {
	border: 1px dashed blue;
	border-radius: 5px;
}

#note-wrapper {
	height: 100%;
	width: 30%;
	z-index: 100;
	position: fixed;
	top: 0;
	background-color: rgba(0, 0, 0, 0.3);
	display: none;
}

#itine-item-note {
	position: absolute;
	top: 24%;
	left: 3%;
	height: 70%;
	overflow-y: auto;
}

.page-content-row {
	margin-right: 0;
	margin-left: 0;
}
p[speaker=${memberVO.name}]{
	color:#2E55C7;
}
</style>
<script>
	var schedule = {
            Itine_No: <%=itineNo%>,
            itineItems: [
            	<jsp:useBean id="itineItemSvc" scope="page" class="com.itineItem.model.ItineItemService"/>
            	<jsp:useBean id="attraSvc" scope="page" class="com.attraction.model.AttractionService"/>
            	<c:forEach var="itineItemVO" items="${itineItemSvc.findByItineNo(param.itineNo)}">
                {
                    Attra_No: "${itineItemVO.attraNo}",
                    Attra_Name: "${attraSvc.getOne(itineItemVO.attraNo).attraName}",
                    Start_Time: "${itineItemVO.startTime}",
                    End_Time: "${itineItemVO.endTime}",
                    Note: "${itineItemVO.note}",
                    Is_Traffic_Tool: ${attraSvc.isTrafficTool(itineItemVO.attraNo)}
                },
                </c:forEach>

            ]
        };
		
			
			
		function initSearch(){
			$("#searchfor").keyup(function(){
				if($("#searchfor").val().trim().length!==0){
				$.ajax({
					url: "<%=request.getContextPath()%>/itinery/attraction.do?action=search&sort="+$("#sort").val()+"&searchfor="+$("#searchfor").val(),
					type: "GET",
					success: function(data){
					
						let info = JSON.parse(data);
						$(".attraction-list").html("");

						for(let i =0; i<info.length;i++){
						$(".attraction-list").prepend(
								`<div class="attraction border " attraNo="${'$'}{info[i].attraNo}">${'$'}{info[i].attraName}</div>
									<div attradescr="attraDescr">
									<div>地點:<br>${'$'}{info[i].location}</div>
									<div>描述:<br>${'$'}{info[i].descr}</div>
									<div>照片:<br><img src="${'$'}{info[i].attraPic1===""?'https://picsum.photos/id/1025/300/200':info[i].attraPic1}"
										style="width: 100%"></div>
								</div>`);						
						}
						initAccordion();
						initDraggableDroppable();
						 
					},
					error: function(){
						alert("failed")
					},
				});
				}
			});
		}
			

		function initSaveBtn(){
			$("#savebtn").click(function(){
				$.ajax({
					url: "<%=request.getContextPath()%>/itinery/itineItem.do",
					type: "POST",
					data: 
// 						schedule,
// 						JSON.stringify(schedule),
						{
						"action": "saveFile",
						"schedule": JSON.stringify(schedule),
						},
// 					traditional:true,
// 					processData: false,
// 					contenType: false,
					success: function(data){
						swal("太棒了",data,"success");
					},
					error: function(){
						window.alert("好像失敗了...");
					},
					
					
				});
				console.log(schedule);
				console.log(schedule.toString());
				console.log("JSON.stringify(schedule): "+JSON.stringify(schedule));
				
			});
		}
			
			
	
		let myDateSet = new Set();
        for (let k = 0; k < schedule.itineItems.length; k++) {
            myDateSet.add(schedule.itineItems[k].Start_Time.substring(0, 10));
        }
        function renderDOM() {
			
            console.log(myDateSet.size);
            for (let date of myDateSet) {
                addTabByDate(date);
                console.log(date);
                console.log(date + "時間欄位有幾個?" + $("#" + date + " .itine-time-content").length);
                for (let i = 0; i < $("#" + date + " .itine-time-content").length; i++) {
                    let startTime = parseInt($($("#" + date + " .itine-time-content")[i]).attr("st"));
                    for (let j = 0; j < schedule.itineItems.length; j++) {
                    	 let thisEndTime = parseInt(schedule.itineItems[j].End_Time.substring(11,13));
                         let thisStartTime = parseInt(schedule.itineItems[j].Start_Time.substring(11,13));
                        if (schedule.itineItems[j].Start_Time.substring(0, 10) === date &&
                            thisStartTime === startTime) {
                            let fold = thisEndTime - thisStartTime - 1;
                            console.log("fold in circuit= " + fold);
                            console.log("#" + date + " .itine-time-content");
                            console.log($($("#" + date + " .itine-time-content")[i]));
                            $($("#" + date + " .itine-time-content")[i]).prepend(
                            		`<div class='itine-item resizable' attraNo='${'$'}{schedule.itineItems[j].Attra_No}' fold='${'$'}{fold}'>
                            			${'$'}{schedule.itineItems[j].Attra_Name}
                              		<div class='delete-itine-item'><i class='fas fa-times'></i></div></div>`);
                            if(schedule.itineItems[j].Is_Traffic_Tool){
                            	$($("#" + date + " .itine-time-content")[i]).find(".itine-item").addClass("traffic-tool");
                            	console.log("there is a traffic-tool");
                            }
                            if (fold > 0) {
                                let dom = $($("#" + date + " .itine-time-content")[i]).find(".itine-item");
                                dom.css('height', (38 + 40 * fold) + 'px');
                                dom.css('line-height', (38 + 40 * fold) + 'px');
                            }
                            break;
                        }
                    }
                }
            }
            console.log("runned renderDOM()");


        }
        function addTabByDate(date) {
            var tabTitle = $("#tab_title"),
                tabContent = $("#tab_content"),
                tabTemplate = "<li><a href='${'#'}{href}'>${'#'}{label}</a> <i class='fas fa-times'></i></li>";
            var tabs = $("#tabs").tabs();
            var label = date,
                // id = "tabs-" + tabCounter,
                id = label,
                li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label)),
                tabContentHtml = `<div class="row">
                                <div class="col-1 ">
                                    <div class="itine-time">08:00</div>
                                    <div class="itine-time">09:00</div>
                                    <div class="itine-time">10:00</div>
                                    <div class="itine-time">11:00</div>
                                    <div class="itine-time">12:00</div>
                                    <div class="itine-time">13:00</div>
                                    <div class="itine-time">14:00</div>
                                    <div class="itine-time">15:00</div>
                                    <div class="itine-time">16:00</div>
                                    <div class="itine-time">17:00</div>
                                    <div class="itine-time">18:00</div>
                                    <div class="itine-time">19:00</div>
                                    <div class="itine-time">20:00</div>
                                    <div class="itine-time">21:00</div>
                                </div>
                                <div class="col-11 itine-table">
                                    <div style="height:12px">&nbsp;</div>
                                    <div class="itine-time-content droppable " st="8" et="9"></div>
                                    <div class="itine-time-content droppable  "  st="9" et="10"></div>
                                    <div class="itine-time-content droppable  "  st="10" et="11"></div>
                                    <div class="itine-time-content droppable  " st="11" et="12"></div>
                                    <div class="itine-time-content droppable  " st="12" et="13"></div>
                                    <div class="itine-time-content droppable  " st="13" et="14"></div>
                                    <div class="itine-time-content droppable  " st="14" et="15"></div>
                                    <div class="itine-time-content droppable  " st="15" et="16"></div>
                                    <div class="itine-time-content droppable  " st="16" et="17"></div>
                                    <div class="itine-time-content droppable  " st="17" et="18"></div>
                                    <div class="itine-time-content droppable  " st="18" et="19"></div>
                                    <div class="itine-time-content droppable  " st="19" et="20"></div>
                                    <div class="itine-time-content droppable  " st="20" et="21"></div>
                                </div>
                            </div>`;
            tabs.find(".ui-tabs-nav").append(li);
            tabs.append("<div id='" + id + "' style='height:445px; overflow-y: auto;'>" + tabContentHtml + "</div>");
            tabs.tabs("refresh");


            console.log("行程日期是: " + tabTitle.val());
            console.log("label= " + label);
            console.log("li= " + li);
            console.log(".ui-tabs-nav= " + $(".ui-tabs-nav"));
            console.log("id= " + id);


        }
</script>

<!-- tabs script-->
<script>
        $(function () {
            var tabTitle = $("#tab_title"),
                tabContent = $("#tab_content"),
                tabTemplate = "<li><a href='${'#'}{href}'>${'#'}{label}</a> <i class='fas fa-times'></i></li>",
                tabCounter = 1;

            var tabs = $("#tabs").tabs();

            // Modal dialog init: custom buttons and a "close" callback resetting the form inside
            var dialog = $("#dialog").dialog({
                autoOpen: false,
                modal: true,
                buttons: {
                   	 確認: function () {
                        if (tabTitle.val() === "") {
                            swal("噢噢~","請填寫日期","warning");
                        } else {
                            addTab();
                            $(this).dialog("close");
                        }
                    },
                                                     取消: function () {
                        $(this).dialog("close");
                    }
                },
                close: function () {
                    form[0].reset();
                }
            });

            // AddTab form: calls addTab function on submit and closes the dialog
            var form = dialog.find("form").on("submit", function (event) {
                addTab();
                dialog.dialog("close");
                event.preventDefault();
            });

            // Actual addTab function: adds new tab using the input from the form above
            function addTab() {
                var label = tabTitle.val() || "Tab " + tabCounter,
                    // id = "tabs-" + tabCounter,
                    id = label,
                    li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label)),
                    tabContentHtml = `<div class="row">
                                    <div class="col-1 ">
                                        <div class="itine-time">08:00</div>
                                        <div class="itine-time">09:00</div>
                                        <div class="itine-time">10:00</div>
                                        <div class="itine-time">11:00</div>
                                        <div class="itine-time">12:00</div>
                                        <div class="itine-time">13:00</div>
                                        <div class="itine-time">14:00</div>
                                        <div class="itine-time">15:00</div>
                                        <div class="itine-time">16:00</div>
                                        <div class="itine-time">17:00</div>
                                        <div class="itine-time">18:00</div>
                                        <div class="itine-time">19:00</div>
                                        <div class="itine-time">20:00</div>
                                        <div class="itine-time">21:00</div>
                                    </div>
                                    <div class="col-11 itine-table">
                                        <div style="height:12px">&nbsp;</div>
                                        <div class="itine-time-content droppable " st="8" et="9"></div>
                                        <div class="itine-time-content droppable  "  st="9" et="10"></div>
                                        <div class="itine-time-content droppable  "  st="10" et="11"></div>
                                        <div class="itine-time-content droppable  " st="11" et="12"></div>
                                        <div class="itine-time-content droppable  " st="12" et="13"></div>
                                        <div class="itine-time-content droppable  " st="13" et="14"></div>
                                        <div class="itine-time-content droppable  " st="14" et="15"></div>
                                        <div class="itine-time-content droppable  " st="15" et="16"></div>
                                        <div class="itine-time-content droppable  " st="16" et="17"></div>
                                        <div class="itine-time-content droppable  " st="17" et="18"></div>
                                        <div class="itine-time-content droppable  " st="18" et="19"></div>
                                        <div class="itine-time-content droppable  " st="19" et="20"></div>
                                        <div class="itine-time-content droppable  " st="20" et="21"></div>
                                    </div>
                                </div>`;
                tabs.find(".ui-tabs-nav").append(li);
                tabs.append("<div id='" + id + "' style='height:445px; overflow-y: auto;'>" + tabContentHtml + "</div>");
                tabs.tabs("refresh");
                tabCounter++;
                initDraggableDroppable();
                initResizable();


                console.log("行程日期是: " + tabTitle.val());
                console.log("label= " + label);
                console.log("li= " + li);
                console.log(".ui-tabs-nav= " + $(".ui-tabs-nav"));
                console.log("id= " + id);


            }

            // AddTab button: just opens the dialog
            $("#add_tab")
                .button()
                .on("click", function () {
                    if ($(this).parent().find("li:last a").text() === "") {
                        dialog.dialog("open");
                    } else {
                        //非第一次的話就新增日期加一的分頁
                        let thisDateArray = $(this).parent().find("li:last a").text().split("-");
                        thisDateArray[2] = (parseInt(thisDateArray[2]) + 1).toString();
                        console.log("thisDateArray[2] = " + thisDateArray[2]);
                        console.log("thisDateArray = " + thisDateArray.join("-"));
                        addTabByDate(thisDateArray.join("-"));
                        initDraggableDroppable();
                        initResizable();
                    }

                });

            // Close icon: removing the tab on click
            tabs.on("click", "i.fa-times", function () {
                var panelId = $(this).closest("li").remove().attr("aria-controls");
                $("#" + panelId).remove();
                tabs.tabs("refresh");
            });

            tabs.on("keyup", function (event) {
                if (event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE) {
                    var panelId = tabs.find(".ui-tabs-active").remove().attr("aria-controls");
                    $("#" + panelId).remove();
                    tabs.tabs("refresh");
                }
            });
        });
    </script>

<script>
        $(function () {
            renderDOM();
            initShowNote();
            initWebSocket();
        //以下只有有編輯權的人可以使用
        
<c:if test="${answer==2||itineVO.builder==memberVO.memberNo}">            
            initDraggableDroppable();
            initResizable();
            initSearch();
            initSaveBtn();
            initAddAttraction();
</c:if>            
        });
        let fold = 0;
        let nowFullDate;
        let nowYear;
        let nowMonth;
        let nowDate;

        //以下是顯示備註方法
        function initShowNote(){
            $(".itine-item").off("click").click(function(e){
              $("#note-wrapper").fadeToggle();  
              
              $.ajax({
            	 url: "<%=request.getContextPath()%>/itinery/attraction.do?action=getAttraInfo&attraNo="+$(this).attr("attraNo"),
            	 type: "GET" ,
            	 success: function(data){
            		 let attraVO = JSON.parse(data)
            		 $("#itine-item-note").attr("attraNo",attraVO.attraNo);
            		 $("#itine-item-note>img").attr("src",attraVO.attraPic1===""?"https://picsum.photos/id/1025/300/200":attraVO.attraPic1);
            		 $("#itine-item-note #attraNameInNote").text(attraVO.attraName);
            		 $("#itine-item-note #locationInNote").html("地點:<br>"+attraVO.location);
            		 $("#itine-item-note #descrInNote").html("描述:<br>"+attraVO.descr);
            		 
            	 },
            	 error: function(){
            		 window.alert("失敗");
            	 },
              });
              
              $.ajax({
            	  url:"<%=request.getContextPath()%>/itinery/itineItem.do?action=showNote&attraNo="+$(this).attr("attraNo")+"&itineNo=${param.itineNo}",
            	  type:"GET",
            	  success: function(data){
            		  let attraVO = JSON.parse(data);
            		  $("#itine-item-note #note-area").val(attraVO.note);
            	  },
            	  error: function(){
            		  window.alert("failed");
            	  }
              })
              
              
            });
            
            $("#note-wrapper").off("click").click(function(){
            	$("#note-wrapper").fadeOut();
            });
            $("#itine-item-note").off("click").click(function(e){
            	e.stopPropagation();
            })
            
            
        }
		
        //以下是風箱方法
		function initAccordion () {
            $(".attraction-list").accordion({
                collapsible: true,
                icons: { "header": "ui-icon-plus", "activeHeader": "false" }
            }).accordion("refresh");
            
        }
        
        //以下是可變動大小
        function initResizable() {
            for (date of myDateSet) {
                $("#" + date + " .resizable").resizable({
                    containment: "#" + date + " .itine-table",
                    // handles: 'n, s',
                    helper: "ui-resizable-helper",
                    grid: 40, // 38 + 2
                    resize: function (event, ui) {
                        ui.size.width = ui.originalSize.width;
                    },
                    start: function (event, ui) {
                        $(event.target).css('border', '1px solid transparent');
                    },
                    stop: function (event, ui) {
                        fold = Math.ceil(ui.size.height / 40) - 1;
                        $(event.target).css('border', '1px solid gray');
                        // $(event.target).css('width', 'inherit');
                        $(event.target).css('left', 'inherit');
                        $(event.target).css('height', (38 + 40 * fold) + 'px');
                        $(event.target).css('line-height', (38 + 40 * fold) + 'px');
                        $(event.target).attr('fold', fold);
                        console.log("fold now is : " + fold);
                        console.log("this itine-item's fold is : " + $(event.target).attr('fold'));

                        nowFullDate = $(".ui-tabs-active a").text();
                        nowYear = $(".ui-tabs-active a").text().split("-")[0];
                        nowMonth = $(".ui-tabs-active a").text().split("-")[1];
                        nowDate = $(".ui-tabs-active a").text().split("-")[2];
                        
                        for (let i = 0; i < schedule.itineItems.length; i++) {
                            if (schedule.itineItems[i].Attra_No === event.target.getAttribute("attraNo")) {
                                schedule.itineItems[i].Start_Time = ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ $(event.target).parent().attr("st")+":00:00";
                                schedule.itineItems[i].End_Time = ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt($(event.target).parent().attr("st")) + 1 + fold)+":00:00";
                                console.log("這是修改");
                                console.log(schedule);
                                
                                let itineItemVO = {
                                		action:"update",
										attraNo: event.target.getAttribute("attraNo"),
										attraName: event.target.innerText.trim(),
										startTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ $(event.target).parent().attr("st")+":00:00",
										endTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt($(event.target).parent().attr("st")) + 1 + fold)+":00:00",
										isTrafficTool: $(event.target).hasClass("traffic-tool")||$(event.target).hasClass("traffic"),
                                }
                                pushItineTable(itineItemVO);
                            }
                        }
                    }
                });
            }
        }


        // 以下是行程表拖拉。
        function initDraggableDroppable() {

            $(".itine-item").draggable({
                revert: "invalid", containment: $('body'),
                // helper: 'clone'
            }).draggable("enable");
            $(".attraction").draggable({
                revert: "invalid", containment: $('body'),
                helper: 'clone',
                
            }).draggable("enable");
           
            $(".traffic").draggable({
                revert: "invalid", containment: $('body'),
                helper: 'clone',
                start: function(event,ui){
                	console.log("drag-start!!!");
                	let thisTraffic = $(this);
                	$.ajax({
                		url:"<%=request.getContextPath()%>/itinery/attraction.do",
                		type: "POST",
                		data: {
                			action:"addTrafficTool",
                			attraName: thisTraffic.text(),
                			sort:"交通",
                			descr:"",
                			location:"",
                			isOnShelf:2,
                		},
                		success: function(data){
                			console.log("編號為:"+data);
                			thisTraffic.attr("attrano",data);
                		},
                		error: function(){
                			console.log("新增交通失敗")
                		},
                	});
                	
                }
            }).draggable("enable");

            $(".droppable").droppable({
                classes: {
                    "ui-droppable-active": "ui-state-hover",
                    "ui-droppable-hover": "ui-state-active"
                },
                drop: function (event, ui) {
                    console.log($(this));//droppable物件
                    console.log(ui.helper[0]); //draggable物件
                    console.log(ui.draggable[0]); // draggable原物件
                    console.log("景點編號是: " + ui.draggable[0].getAttribute("attraNo"));


                    $(this).html(`<div class='itine-item resizable' attraNo='${'$'}{ui.draggable[0].getAttribute("attraNo")}' fold='${'$'}{fold}'>${'$'}{$(ui.draggable).text()}
                                                            <div class='delete-itine-item'><i class='fas fa-times'></i></div></div>`);
					
                    if($(ui.draggable[0]).attr("class")=="traffic ui-draggable ui-draggable-handle"){
						console.log("從交通工具來的");
						$(this).html(`<div class='itine-item traffic-tool resizable' attraNo='${'$'}{ui.draggable[0].getAttribute("attraNo")}' fold='${'$'}{fold}'>${'$'}{$(ui.draggable).text()}
                        <div class='delete-itine-item'><i class='fas fa-times'></i></div></div>`);
					}


                    console.log($(this).find(".itine-item"));
                    $(this).find(".itine-item").css('height', (38 + 40 * fold) + 'px');
                    $(this).find(".itine-item").css('line-height', (38 + 40 * fold) + 'px');


                    //刪除被拖走的地方(交通方式不刪)
                    console.log("$(ui.draggable).attr('class'):" + $(ui.draggable).attr("class"));
                    console.log("$(ui.draggable).next().attr('attradescr') = "+$(ui.draggable).next().attr('attradescr'))
                    if($(ui.draggable).attr('class')!=="traffic ui-draggable ui-draggable-handle"){
                    	if($(ui.draggable).next().attr('attradescr')==="attraDescr"){
                    		$(ui.draggable).next().remove();
                    	}
                    	$(ui.draggable).remove();
                    }



                    nowFullDate = $(".ui-tabs-active a").text();
                    nowYear = $(".ui-tabs-active a").text().split("-")[0];
                    nowMonth = $(".ui-tabs-active a").text().split("-")[1];
                    nowDate = $(".ui-tabs-active a").text().split("-")[2];

                    console.log("this date is : " + nowYear + "-" + nowMonth + "-" + nowDate);

                    ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ $(event.target).parent().attr("st")+":00:00";
                    //新增變數到schedule
                    var isNewItineItem = true;
                    for (let i = 0; i < schedule.itineItems.length; i++) {
                        if (schedule.itineItems[i].Attra_No === ui.draggable[0].getAttribute("attraNo")) {
                            schedule.itineItems[i].Start_Time = ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ this.getAttribute("st")+":00:00";
                            schedule.itineItems[i].End_Time = ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt(this.getAttribute("st")) + 1 + fold)+":00:00";
                            console.log("這是修改");
                            isNewItineItem = false;
                            
                            let itineItemVO = {
                            		action:"update",
                            		attraNo: ui.draggable[0].getAttribute("attraNo"),
                            		attraName: $(ui.draggable).text().trim(),
                            		startTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ this.getAttribute("st")+":00:00",
                            		endTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt(this.getAttribute("st")) + 1 + fold)+":00:00",
                            		isTrafficTool: $(ui.draggable).hasClass("traffic-tool")||$(ui.draggable).hasClass("traffic"),
                            } 
                            console.log("isTrafficTool?"+$(ui.draggable).hasClass("traffic-tool")||$(ui.draggable).hasClass("traffic") );
                            pushItineTable(itineItemVO);
                        }
                    }
                    if (isNewItineItem == true) {
                        schedule.itineItems.push({
                            Attra_No: ui.draggable[0].getAttribute("attraNo"),
                            Start_Time: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ this.getAttribute("st")+":00:00",
                            End_Time: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt(this.getAttribute("st")) + 1 + fold)+":00:00",
                        	Note:""
                        });
                        myDateSet.add(nowYear + "-" + nowMonth + "-" + nowDate);
                        
                        $.ajax({
                        	url: "<%=request.getContextPath()%>/itinery/itineItem.do",
                        	type: "POST",
                        	data: {
                        		action: "addByAjax",
                        		Itine_No: "${param.itineNo}",
                        		Attra_No: ui.draggable[0].getAttribute("attraNo"),
                                Start_Time: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ this.getAttribute("st")+":00:00",
                                End_Time: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt(this.getAttribute("st")) + 1 + fold)+":00:00",
                        	},
                        	success: function(data){
                        		console.log(data);
                        	},
                        	error: function(){
                        		window.alert("新增行程明細失敗唷!")
                        	},
                        
                    	});
                        console.log("這是新增");
                        
                        let itineItemVO ={
                        		action: "add",
                        		attraNo: ui.draggable[0].getAttribute("attraNo"),
                        		attraName: $(ui.draggable).text().trim(),
                        		startTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ this.getAttribute("st")+":00:00",
                        		endTime: ""+nowYear+"-"+nowMonth+"-"+nowDate+" "+ (parseInt(this.getAttribute("st")) + 1 + fold)+":00:00",
                        		note: "",
                        		isTrafficTool: $(ui.draggable).hasClass("traffic-tool")||$(ui.draggable).hasClass("traffic"),
                        }
                        pushItineTable(itineItemVO);

                	}
                    	console.log(schedule);


                    deleteEvents();
                    initDraggableDroppable();
                    initResizable();
                    initShowNote();
                }
            });

            deleteEvents();


        }

        function deleteEvents() {
            $(".itine-item").off('hover').hover(function () {
                $(this).children(".delete-itine-item").show();
            }, function () {
                $(this).children(".delete-itine-item").hide();
            });

            $(".delete-itine-item").off('click').click(function () {
                for (let i = 0; i < schedule.itineItems.length; i++) {
                    if (schedule.itineItems[i].Attra_No === $(this).parent().attr("attraNo")) {
                        schedule.itineItems.splice(i, 1);
                        console.log("這是刪除");
                        console.log(schedule);
                        let itineItemVO = {
                        		action : "delete",
                        		attraNo : $(this).parent().attr("attraNo"),
                        }
                        pushItineTable(itineItemVO);
                        
                        $.ajax({
                        	url: "<%=request.getContextPath()%>/itinery/itineItem.do",
                        	type: "POST",
                        	data: {
                        		action: "deleteByAjax",
                        		Itine_No: "${param.itineNo}",
                        		Attra_No: $(this).parent().attr("attraNo"),
                        	},
                        	success: function(data){
                        		console.log(data);
                        	},
                        	error: function(){
                        		window.alert("刪除行程明細失敗唷!")
                        	},
                        
                    	});
                    }
                }
                $(this).parent().remove();

            });
        }
        function initAddAttraction(){
        	$("#openAddAttractionModel").click(function(){
        		let attraName = $("#searchfor").val().trim();
        		$("#attraction-name").val(attraName);
        		let sort = $("#sortinbar").val();
        		$("#sort").val(sort);
        		$('select[name=county] option:first-child').prop("selected",true);
        		$('select[name=district] option:first-child').prop("selected",true);
        	});
        	$("#addAttractionBtn").click(function(){
        		if($("#attraction-name").val().trim().length===0){
        			swal("噢噢","行程名稱不得為空白","warning");
        			return;
        		}
        		if($('select[name=district]').val().length==0){
        			swal("拜託","至少留個縣市","warning");
        			return;
        		}
        		
        		$.ajax({
        			url: "<%=request.getContextPath()%>/itinery/attraction.do",
        			type: "POST",
        			data: {
        				"action": "addAttraction",
        				"attraName": $("#attraction-name").val().trim(),
        				"sort": $("#sort").val(),
        				"descr": $("#attraction-descr").val(),
        				"county": $('select[name=county]').val(),
        				"district": $('select[name=district]').val(),
        				"shortLocation": $("#attraction-address").val(),
        				"isOnShelf": $('input[name=submitAttra]:checked').val()
        			},
        			success: function(data){
        				$(".attraction-list").prepend(
								`<div class="attraction border " attraNo="${'$'}{data}">
											${'$'}{$("#attraction-name").val().trim()}</div>
								<div attradescr="attraDescr">
									<div>地點:<br>${'$'}{$('select[name=county]').val()+$('select[name=district]').val()+$("#attraction-address").val()}</div>
									<div>描述:<br>${'$'}{$("#attraction-descr").val()}</div>
									<div>照片:<br></div>
								</div>`);						
        				$("#addAttractionMadal").modal('hide');
        				$("#attraction-address").val("");
        				$("#attraction-descr").val("");
						initDraggableDroppable();
        				initAccordion();
						
        			},
        			error: function(){
        				window.alert("新增景點失敗")	
        			}
        		});
        	});
        	
        	$("#itineName").blur(function(){
        		if($(this).val().trim().length===0){
        			swal("行程名稱不得為空白");
        			return;
        		}
        		$.ajax({
        			url:"<%=request.getContextPath()%>/itinery/itine.do?itineName="+$(this).val()+"&itineNo=${param.itineNo}&action=changeName",
        			type:"GET",
        			success:function(data){
        				swal("太棒了",data,"success");
        			},
        			error:function(){
        				window.alert("出錯唷");
        			}
        			
        		});
        		
        	});
        	
        	$("#note-area").blur(function(){
        		$.ajax({
        			url: "<%=request.getContextPath()%>/itinery/itineItem.do?",
        			type: "GET",
        			data: {
        				"action": "saveNote",
        				"itineNo": "${param.itineNo}",
        				"attraNo": $("#itine-item-note").attr("attraNo"),
        				"note": $(this).val(),
        			},
        			success: function(data){
        				swal("太棒了",data,"success");
        			},
        			error: function(){
        				window.alert("失敗唷");
        			},
        		});
        	});
        	
        	
        }
        
        	let webSocket;
        function initWebSocket(){
        	let myPoint = "/ItineWebSocket/${itineVO.itineNo}";
        	let host = location.host;
        	let path = location.pathname;
        	let webCtx = path.substring(0,path.indexOf("/",1));
        	let endPointURL = "ws://"+host+webCtx+myPoint;
        	
        	connect();
        	
        	function connect(){
        		
        		//creat a webSocket 
        		console.log(endPointURL);
        		webSocket = new WebSocket(endPointURL);
        		
        		webSocket.onopen = function(event){
//         			alert("messageWebSocket connected!");
        		}
        		
        		webSocket.onmessage = function(event){
//         			alert(event.data);
        			let messageData = JSON.parse(event.data);
        			
        			
        			
        			if(messageData.action=="message"){
        				let pattern = `<p class="m-0" speaker="${'$'}{messageData.speaker}">${'$'}{messageData.speaker}:&nbsp;${'$'}{messageData.message}</p>`;
        				$(".msgboard-content").append(pattern);
        				return;
        			}
        			
        			if(messageData.action=="delete"){
        				//修改變數
//         				$.each(schedule.itineItems,function(idx,itineItem){
//         					if(itineItem.Attra_No==messageData.attraNo){
//         						schedule.itineItems.splice(idx,1);
//         						console.log("delete success");
//         						return;
//         					}
//         				});
        				for(let i = 0; i<schedule.itineItems.length; i++){
        					if(schedule.itineItems[i].Attra_No==messageData.attraNo){
        						schedule.itineItems.splice(i,1);
        						
        					}
        				}
        				//修改dom
        				$(".itine-item").each(function(){
        					if($(this).attr("attrano")==messageData.attraNo){
        						$(this).remove();
        					}
        				});
        					console.log(schedule);
        			}
        			
        			if(messageData.action=="update"){
//         				alert("update");
        				//修改變數
        				$.each(schedule.itineItems,function(idx,itineItem){
        					if(itineItem.Attra_No==messageData.attraNo){
        						
        						schedule.itineItems[idx].Start_Time=messageData.startTime;
        						schedule.itineItems[idx].End_Time=messageData.endTime;
        					}
        				});
        				//先清除原本的dom
        				$(".itine-item").each(function(){
        					if($(this).attr("attrano")==messageData.attraNo){
        						$(this).remove();
        					}
        				});
        				//再插入新的dom
        				let thisDate = messageData.startTime.substring(0,10);
        				let thisStartTime = parseInt(messageData.startTime.substring(11,13));
        				let thisEndTime = parseInt(messageData.endTime.substring(11,13));
        				let fold = thisEndTime - thisStartTime -1;
        				$("#"+thisDate+" .itine-time-content").each(function(){

        					let startTime = parseInt($(this).attr("st"));
        					if(startTime === thisStartTime){
        						$(this).prepend(
        							`<div class='itine-item resizable' attraNo='${"$"}{messageData.attraNo}' fold='${"$"}{fold}'>
                            			${'$'}{messageData.attraName}
                              		<div class='delete-itine-item'><i class='fas fa-times'></i></div></div>`
        						);
        						if(messageData.isTrafficTool){
        							$(this).find(".itine-item").addClass("traffic-tool");
        						}
        						
        						if (fold > 0) {
                                    let dom = $(this).find(".itine-item");
                                    dom.css('height', (38 + 40 * fold) + 'px');
                                    dom.css('line-height', (38 + 40 * fold) + 'px');
                                }
        					}
        				});
                        initShowNote();
                        
                        <c:if test="${answer==2||itineVO.builder==memberVO.memberNo}">            
                        initDraggableDroppable();
                        initResizable();
                       
            			</c:if>      
        				console.log(schedule);
        				
        			}
        			if(messageData.action=="add"){
//         				alert("add");
        				//修改變數
        				let itineItem = {
        						Attra_No: messageData.attraNo,
        						Attra_Name: messageData.attraName,
        						Start_Time: messageData.startTime,
        						End_Time: messageData.endTime,
        						Note: ""
        				}
        				schedule.itineItems.push(itineItem);
        				//先清除原本的dom
        				$(".itine-item").each(function(){
        					if($(this).attr("attrano")==messageData.attraNo){
        						$(this).remove();
        					}
        				});
        				//插入新的dom
        				let thisDate = messageData.startTime.substring(0,10);
        				let thisStartTime = parseInt(messageData.startTime.substring(11,13));
        				let thisEndTime = parseInt(messageData.endTime.substring(11,13));
        				let fold = thisEndTime - thisStartTime -1;
        				$("#"+thisDate+" .itine-time-content").each(function(){

        					let startTime = parseInt($(this).attr("st"));
        					if(startTime === thisStartTime){
        						$(this).prepend(
        							`<div class='itine-item resizable' attraNo='${"$"}{messageData.attraNo}' fold='${"$"}{fold}'>
                            			${'$'}{messageData.attraName}
                              		<div class='delete-itine-item'><i class='fas fa-times'></i></div></div>`
        						);
        						if(messageData.isTrafficTool){
        							$(this).find(".itine-item").addClass("traffic-tool");
        						}
        						if (fold > 0) {
                                    let dom = $(this).find(".itine-item");
                                    dom.css('height', (38 + 40 * fold) + 'px');
                                    dom.css('line-height', (38 + 40 * fold) + 'px');
                                }
        					}
        				});
                        initShowNote();
                        
                        <c:if test="${answer==2||itineVO.builder==memberVO.memberNo}">            
                        initDraggableDroppable();
                        initResizable();
                       
            			</c:if>      
        				console.log(schedule);
        			}
        		}
        		
        		webSocket.onclose = function(event){
//         			alert("messageWebSocket Disconnected!");
        		}
        		
        	}
        	
        	$("#send-message").off("click").click(sendBoardMessage);
        	$("#send-message").prev().keyup(function(event){
        		if(event.keyCode==13){
        			sendBoardMessage();
        		}
        	})
        	
        	
        }
			
        
        function sendBoardMessage(){
			let messageData ={
				"action": "message",
				"itineID":${param.itineNo},
				"speaker":"${memberVO.name}",
				"message":$("#send-message").prev().val()
			};
			
			$("#send-message").prev().val("");
			$("#send-message").prev().focus();
			webSocket.send(JSON.stringify(messageData));
			
			
		}        	
        function pushItineTable(itineItemVO){

       		webSocket.send(JSON.stringify(itineItemVO));
       	}
      
        	
        
		
$(document).ready(function(){
  new TwCitySelector({
	  el: 'div[role=tw-city-selector]',
	  elCounty: 'select[name=county]',
	  elDistrict: 'select[name=district]',
	  countyValue: '${param.county}',
	  districtValue: '${param.district}'
  });
});
       
	</script>

</head>

<body>
	<!-- Topbar -->
	<jsp:include page="/front-end/myNavBar.jsp"/>

	<!-- End of Topbar -->

	<!-- page-content start-->
	<div class="page-content " style="margin-top: 70px">
		<div class="row page-content-row">
			<div class="col-3">
				<div>
					<input type="text" class="form-control"
						value="${itineVO.itineName}" id="itineName">
				</div>
				<div class="search-attraction">
					<input type="text" class="form-control" placeholder="搜尋你要去的地方"
						name="searchfor" id="searchfor">
					<div class="input-group">
						<select name="sort" id="sortinbar" class="custom-select">
							<option value="景點">景點</option>
							<option value="餐廳">餐廳</option>
							<option value="住宿">住宿</option>
						</select>
						<button class="btn btn-primary" type="button" data-toggle="modal"
							data-target="#addAttractionMadal" id="openAddAttractionModel">新增</button>
					</div>
				</div>
				<div class="attraction-list border border-2 border-primary ">

				</div>
				<!-- Button trigger modal -->
				交通方式

				<!-- 新增景點Modal -->
				<div class="modal fade " id="addAttractionMadal" tabindex="-1"
					aria-labelledby="addAttractionModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">

							<form>
								<div class="modal-body">
									<div class="form-group">
										<label for="attraction-name" class="col-form-label">名稱:</label>
										<input type="text" class="form-control" id="attraction-name">
									</div>
									<div class="form-group">
										<label for="sort">分類:</label> <select id="sort"
											class="custom-select">
											<option value="景點">景點</option>
											<option value="住宿">住宿</option>
											<option value="餐廳">餐廳</option>
										</select>
									</div>
									<div class="form-group">
										<label for="attraction-address" class="col-form-label">地址:</label>
										<div role="tw-city-selector" data-bootstrap-style >
											<div class="form-group" style="display:inline-block;width:25%;">
												<select class="form-control county" name="county"></select>
											</div>
											<div class="form-group" style="display:inline-block;width:25%;">
												<select class="form-control district" name="district"></select>
											</div>
										</div>
										<input type="text" class="form-control"
											id="attraction-address">
									</div>
									<div class="form-group">
										<label for="attraction-descr" class="col-form-label">描述:</label>
										<textarea class="form-control" id="attraction-descr"></textarea>
									</div>
									<div class="form-group">
										<label for="attraction-submit">是否提交景點建議給我們?:</label> <input
											type="radio" id="attraction-submit1" name="submitAttra"
											value="0" checked><label for="attraction-submit1">好的</label>
										<input type="radio" id="attraction-submit2" name="submitAttra"
											value="2"><label for="attraction-submit2">不了</label>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">取消</button>
									<button type="button" class="btn btn-primary"
										id="addAttractionBtn">新增</button>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div class="traffic-list border border-2 border-primary   ">
					<div class="traffic-box text-center">
						<c:forEach var="i" begin="11" end="18">
							<div class="traffic" attrano=${attraSvc.getOne(i).attraNo }>${attraSvc.getOne(i).attraName }</div>
						</c:forEach>

					</div>
				</div>
			</div>
			<div class="col-9 right-side">
				<div class="row">
					<!-- 行程表區 -->
					<div class="col-9  p-0">
						<div id="dialog" title="開啟新分頁">
							<form>
								<fieldset class="ui-helper-reset">
									<label for="tab_title">請選擇日期</label> <input type="date"
										name="tab_title" id="tab_title"
										class="ui-widget-content ui-corner-all">
								</fieldset>
							</form>
						</div>

						<!-- <button id="add_tab">Add Tab</button> -->
						<div id="tabs">
							<ul>
								<button id="add_tab" class="py-2">+</button>
							</ul>
						</div>

					</div>
					<div class="col-3 justify-content-center d-flex">
						<div class="row right-side-row">
							<div class="col-12 border-3 border-bottom border-secondary ">
								<button type="button" class="btn btn-primary m-3 btns"
									id="savebtn">存檔</button>
							</div>
							<div class="col-11 " style="height: 150px">
								<h5>可編輯</h5>
								<jsp:useBean id="itineMemberSvc1" scope="page"
									class="com.itineMember.model.ItineMemberService" />
								<jsp:useBean id="memberSvc" scope="page"
									class="com.member.model.MemberService" />

								<p class="m-0" style="color: orange;">&nbsp;${memberSvc.getOneMember(itineVO.builder).name }</p>

								<c:forEach var="itineMemberVO"
									items="${itineMemberSvc1.findByItineNo(param.itineNo) }">
									<c:if test="${itineMemberVO.isEditable==2}">

										<p class="m-0" style="color: pink;">&nbsp;${memberSvc.getOneMember(itineMemberVO.memberNo).name }</p>

									</c:if>
								</c:forEach>
								<h5>僅可觀看</h5>
								<c:forEach var="itineMemberVO"
									items="${itineMemberSvc1.findByItineNo(param.itineNo) }">
									<c:if test="${itineMemberVO.isEditable==1}">

										<p class="m-0" style="color: rgb(161, 161, 64);">
											&nbsp;${memberSvc.getOneMember(itineMemberVO.memberNo).name }</p>

									</c:if>
								</c:forEach>

							</div>
							<div
								class="col-11 border border-5 border-primary p-0 rounded msgboard">
								<div
									class="msgboard-title text-center border-bottom border-2 border-dark"
									style="background-color: #2195E2; color: white;">留言板</div>
								<div class="msgboard-content">
									
									<c:forEach var="msgRow" items="${msgList }">
									<p class="m-0" speaker='${msgRow[0]}'>${msgRow[1]}</p>
									</c:forEach>
								</div>
								<div class="row">
									<div class="input-group mx-2">
										<input type="text" class="form-control" placeholder="說點什麼吧">
										<button class="btn btn-primary" type="button"
											id="send-message">送出</button>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
	<div id="note-wrapper">
		<div class="card" id="itine-item-note" style="width: 18rem;">
			<img src="" class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title" id="attraNameInNote"></h5>
				<div id="locationInNote"></div>
				<div id="descrInNote"></div>
				<h5 style="margin-top: 10px;">備註:</h5>
				<textarea name="note-area" id="note-area" cols="25" rows="10"
					placeholder="寫點什麼吧!"></textarea>
			</div>
		</div>
	</div>
</body>

</html>