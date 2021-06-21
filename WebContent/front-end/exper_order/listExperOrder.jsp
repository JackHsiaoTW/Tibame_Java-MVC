<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.exper_order.model.*"%>
<%@ page import="com.experience.model.*"%>
<%@ page import="com.exper_photo.model.*"%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <title>達人體驗</title>
    <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/js/splide.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@latest/dist/css/splide.min.css">
    <style>
        *{
            box-sizing: border-box;
        }
        
        body{
            margin: 0;
            padding: 0;
            background-image:url("<%=request.getContextPath()%>/image/Ski-1.jpg");
               background-attachment: fixed;
             background-size: cover;
             background-repeat: no-repeat;
             background-position: bottom;
        }
        
/* /*         table_list樣式開始  */
        .listTable{
            /* border: 1px solid red; */
            text-align:center;
            margin: 100px auto;
            padding: 10px;
            border-radius: 5px;
            width: 95%;
            background-color: #ffffffab;;
            box-shadow: 0px 5px 40px -22px #8b8b8b;
        }
        .listTable td{
            /* overflow: hidden; */
            /* white-space: nowrap; */
            /* text-overflow: ellipsis; */
            /* max-width: 200px; */
            /* min-width: 100px; */
            padding: 10px;
        }
        .listTable td:nth-child(1){
            /* border: 1px solid blue; */
            width: 250px;
            overflow: hidden;
            padding: 10px 15px;
        }
        .listTable td > img{
            width: 200px;
            height: 150px;
/*             object-fit: cover; */
        }
        .listTable > td:nth-last-child(1), td:nth-last-child(2){
            width: 100px;
        }
        .btn3{
            font-weight: 600;
            padding: 10px;
            outline: none;
            cursor: pointer;
            width: 100px;
            background-color: #1E90FF !important;
            border: 3px solid #1E90FF !important;
            padding:10px !important;
            border-radius:0px !important;
            color: #332926;
            transition: all 0.2s ease-in-out;
        }
        .btn3:hover{
            border: 3px solid #1E90FF !important;
            background-color: #fff !important;
        }
        .btn1{;
            padding: 10px;
            outline: none;
            cursor: pointer;
            width: 100px;
            background-color: #1E90FF !important;
            border: 3px solid #1E90FF !important;
            padding:10px !important;
            border-radius:0px !important;
            color: #332926;
            transition: all 0.2s ease-in-out;
        }
        .btn1:hover{
        font-weight: 600;
            padding: 10px;
            outline: none;
            cursor: pointer;
            background-color: #1E90FF !important;
            border: 3px solid #1E90FF !important;
            padding:10px !important;
            border-radius:0px !important;
            color: #332926;
            transition: all 0.2s ease-in-out;
        }
        .submitBtn{
            font-weight: 600;
            letter-spacing: 2px;
            padding: 10px;
            outline: none;
            cursor: pointer;
            width: 80px;
            background-color: #1E90FF;
            border: 3px solid #1E90FF;
            color: white;
            transition: all 0.2s ease-in-out;
        }
        .submitBtn:hover{
            border: 3px solid #1E90FF;
            background-color: #fff;
            color: #1E90FF;
        }
        /* table_list樣式結束 */
        /* 燈箱樣式開始 */
        .itemLightboxBg{
            background-color: #0000008f;
            height: 100vh;
            width: 100%;
            position: fixed;
            z-index: 99;
            top: 0;
            left: 0;
            display:none;
            
        }
        .itemLightbox{
            /* border: 1px solid red;  */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 55%;
            height: 450px;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-radius: 5px;
            background-color: #fff;
            /* box-shadow: 0px 5px 40px -22px #8b8b8b; */
            box-shadow: 0px 5px 34px -19px #000000;
            z-index: 100;
            display:none;
        }
        .close{
            position: absolute;
            top: 5px;
            right: 20px;
            font-family: monospace;
            font-size: 28px !important;
            color: #797979 !important;
            cursor: pointer !important;
            background:none !important;
        }
        .itemContainer{
            /* border: 1px solid blue; */
            width: 97%;
            height: 400px;
            margin: 0 auto;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .caro{
            width: 600px;
            display: flex;
            overflow: hidden;
        }
        .splide{
            min-width: 500px;
        }
        .splide__list{
            width: 500px;
        }
        .itemData{
            /* border: 1px solid gold; */
            width: 400px;
            height: 90%;
            margin-left: 20px;
        }
        .itemForm{
            text-align: right;
        }
        .itemTable th, #itemTable td{
            /* border: 1px solid red; */
            /* border-bottom: 1px solid #ccc; */
            text-align: left;
            padding: 15px;
        }
        .itemForm #submitBtn{
            margin: 15px;
        }
        .splide--fade>.splide__track>.splide__list>.splide__slide{
            height: 350px;
        }
        table.itemTable td{
        margin-top:5px;
        display: grid;
        }
        table.itemTable input{
        border-style: solid;
        }
        div.carousel-inner img {
        height:650px;
        }
       
        /* 燈箱樣式結束 */
        
		/*查無資料文字 */
		.noQuery{
		margin-top: 45px;
    	text-align: center;
    	background-color: #c5aa33c4;
     	 animation: changeColor 1s infinite linear alternate; 
		}
		
		@keyframes changeColor {
            from {
                background-color: : #c5aa33c4;
            }
            to {
                background-color: #000000a6;
            }
          }
		
		
		.noQuery h3{
		font-weight:bold;
		color: #fff;
		}
		#room{
		display:none;
		} */
		
		
    </style>
</head>

<body>
    <!-- 表格開始 -->
<%-- <%@include file="/front-end/NavPage.jsp"%> --%>
<%@include file="/front-end/myNavBar.jsp"%>
    <%int i=0; %>
    <jsp:useBean id="expordSvc" scope="page" class="com.exper_order.model.ExperOrderService" />
    <jsp:useBean id="experSvc" scope="page" class="com.experience.model.ExperienceService" />
    <jsp:useBean id="expphoSvc" scope="page" class="com.exper_photo.model.ExperPhotoService" />

    <div>
        <c:forEach var="expordVO" items="${not empty queryFinalList&&queryFinalList.size()!=0? queryFinalList :expordSvc.all}">
            <table class="listTable">
                <tr>
                    <td rowspan="2"><img class="list-photo" src="<%=request.getContextPath()%>/ExperPhoto/ExperPhoto.do?exper_no=${expordVO.exper_no}&action=getListExperPhoByExperNo"></td>
                    <td>體驗名稱</td>
                    <td>體驗開始時間</td>
                    <td>體驗結束時間</td>
                    <td>體驗價格</td>
               
                </tr>
                <tr>
                    <td>${experSvc.getOneExperience(expordVO.exper_no).name}</td>
                    <td>${expordVO.exper_order_start}</td>
                    <td>${expordVO.exper_order_end}</td>
                    <td>${expordVO.exper_now_price}</td>
                    <td><button class="btn3" type="button">前往報名</button></td>
             <%--        <td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/FavoriteExper/FavoriteExper.do">
						<input type="hidden" name="member_no" value="${memberVO.memberNo}">
						<input type="hidden" name="exper_no" value="${expordVO.exper_no}">
						<input type="hidden" name="action" value="insert">
						<button class="btn1" type="submit" style="margin-top: 15px;">加入收藏</button>
					</FORM></td> --%>
                </tr>
            </table>
            
            <!-- 表格結束 -->
            <!-- 燈箱開始 -->
            <div class="itemLightbox">
                <span class="close">x</span>
                <div class="itemContainer">
                    <!-- 這是商品資料 -->
                    <div class="itemData">
                        <table class="itemTable">
                            <form method="post" action="<%=request.getContextPath()%>/ExperApplication/ExperApplication.do">
                                <tr>
                                    <td>體驗介紹:</td>
                                </tr>
                                <tr>
                                    <td>${experSvc.getOneExperience(expordVO.exper_no).exper_descr}</td>
                                </tr>
                                 <c:choose>
                                     <c:when test="${expordVO.exper_apply_sum<expordVO.exper_max_limit}">
                                         <tr>
                                    		<td>參與人數:</td>
                                    		<td><input type="number" name="number" min="1" max="100" step="1" value="1"></td>
                                		</tr>
                                		<!-- <tr>
                                    		<td>會員ID:</td>
                                    		<td><input type="number" name="member_no" ></td>
                                		</tr> -->
                                		</tr>
                               			<input type="hidden" name="member_no" value="${memberVO.memberNo}">
                                		<tr>
                               			 <tr>
                                    		<td>備註: <input type="textarea" name="exper_appli_memo" style="height: 80px;"></td>
                                		</tr>
                                		<input type="hidden" name="action" value="insertByMember">
                               			<input type="hidden" name="exper_order_no" value="${expordVO.exper_order_no}">
                                		<tr>
                                			<td><input type="submit" class="submitBtn" value="參加"></td>
                                		</tr>
                                    </c:when>
                                    <c:otherwise>
                                                    		<tr><td class="noQuery"><h3>報名已額滿</h3></td></tr>
                                     </c:otherwise>
                                 </c:choose>
                            </form>
                        </table>
                        <!-- button先放這 -->
                    </div>
                </div>
            </div>
            <!-- 燈箱結束 -->
            <!-- 這是燈箱背景 -->
            <div class="itemLightboxBg"></div>
        </c:forEach>
    </div>
    <script>
    
        
        
   
        let viewBtn = document.getElementsByClassName('btn3');
        document.addEventListener('DOMContentLoaded', function() {
            for (let i = 0; i < viewBtn.length; i++) {

                let splide = new Splide('.splide' + i, {
                    'cover': true,
                    'fixedHeight': '70%',
                    'width': '100%',
                    'rewind': true,
                    'type': 'fade',
                    'speed': 1600, // 切換時的秒數
                    'autoplay': true, // 自動播放
                    'interval': 3500, // 間格秒數(ms)
                    'arrows': false,
                    'pagination': false,
                })
                splide.on('autoplay:playing', function(rate) {
                    // console.log( rate ); // 0-1
                });
                splide.mount();
            }
        });
        // 燈箱開關
        let closeBtn = document.getElementsByClassName('close');
        let lightbox = document.getElementsByClassName('itemLightbox');
        let bg = document.getElementsByClassName('itemLightboxBg');
        for (let i = 0; i < viewBtn.length; i++) {
            lightbox[i].style.display = 'none';
            bg[i].style.display = 'none';
            window.addEventListener('load', function() {
                viewBtn[i].addEventListener('click', function() {
                    lightbox[i].style.display = 'block';
                    bg[i].style.display = 'block';
                });
                closeBtn[i].addEventListener('click', function() {
                    lightbox[i].style.display = 'none';
                    bg[i].style.display = 'none';
                });
            });
        }
    </script>
</body>

</html>