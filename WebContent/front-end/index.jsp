<%@page import="com.member.model.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <jsp:useBean id="memberVO" scope="session" class="com.member.model.MemberVO"/> --%>
<jsp:useBean id="notifySvc" scope="request" class="com.notify.model.NotifyService"/>

<!DOCTYPE html> 
<html lang= "en" >

<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>index.jsp</title>

     <!-- Custom fonts for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/slick.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/slick-theme.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/slick.min.js"></script>
    <!-- Core plugin JavaScript-->
<%--     <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script> --%>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>

   

    <style>
        .search {
            position: absolute;
            z-index: 9;
            top: 45%;
            left: 25%;
        }

        #carouselExampleIndicators {
            z-index: 0;
        }

        .banner-area {
            position: relative;
            margin-top: 70px;
        }


        .promote-area {
            width: 95%;
            margin: 0 auto;
        }

        .promote-area div {
            margin: 10px;
        }

        .slick-prev:before,
        .slick-next:before {
            color: blue;
        }

        .text-p {
            margin: 30px;
            color: rgb(53, 12, 12);
        }

        .text-area {
            margin-left: 50px;
            line-height: 50px;
        }

        footer {
            background-color: #2E55C7;
            color: white;
        }

        .showmore {
            margin-left: 85%;
        }
    </style>
   

</head>

<body id="page-top">

    <c:import url="/front-end/myNavBar.jsp"/>



    <!-- Begin Page Content -->
    <div class="banner-area" id="banner-area">
        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="https://picsum.photos/id/684/1200/500" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="https://picsum.photos/id/100/1200/500" class="d-block w-100" alt="">
                </div>
                <div class="carousel-item">
                    <img src="https://picsum.photos/id/120/1200/500" class="d-block w-100" alt="">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <!-- End of Page Wrapper -->
        <!-- Searchbar -->
        <form class="search w-50 h-50 mx-auto">
            <div class="input-group">
                <input type="text" class="form-control bg-white border-0 small" placeholder="搜尋你最愛的..."
                    aria-label="Search" aria-describedby="basic-addon2">
                <div class="input-group-append">
                    <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                    </button>
                </div>
            </div>
        </form>
    </div>
    <div class="text-p">
        <h3><b>主打促銷</b></h3>
    </div>


    <div class="promote-area">
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem;">
            <img src="https://picsum.photos/id/684/300/200" alt="" class="card-img-top">
            <div class="card-body">
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's content.</p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
    </div>


    <div class="text-p">
        <h3><b>最新消息</b></h3>
    </div>
    <div class="text-area">
        <ul>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, laboriosam.</li>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, laboriosam.</li>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, laboriosam.</li>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, laboriosam.</li>
            <li>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, laboriosam.</li>
            <li>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eos soluta distinctio optio amet quos
                velit iusto eius labo</li>
        </ul>
    </div>
    <a href="" class="showmore">顯示更多</a>

    <!-- Footer -->
    <footer class="sticky-footer">
        <div class="container my-auto">
            <div class="copyright text-center my-auto">
                <span>Copyright &copy; Guide Me 2021</span>
            </div>
        </div>
    </footer>
    <!-- End of Footer -->
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

 <script>
        $(document).ready(function () {

            $(".promote-area").slick({
                dots: true,
                infinite: true,
                slidesToShow: 3,
                slidesToScroll: 3,
                autoplay: true,
                centerMode: true,
                centerPadding: '100px',
            });
        });

 </script>

</body>

</html>