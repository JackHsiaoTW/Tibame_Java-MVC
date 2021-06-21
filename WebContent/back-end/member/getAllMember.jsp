<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>back-end All Member</title>

<!-- Custom fonts for this template-->
<link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min_backend.css" rel="stylesheet">

<style>
    .bg-gradient-primary{
        background-color: #009ab9;
        background-image: none;
    }
    .collapse-bg{
        /* background-color: #5cd0ff; */
    }
    
    table img{
    	width:80px;
    	height:80px;
	}
	
	table.allMemberInfo th {
		vertical-align: middle;
	  	text-align: center;
	}
	
	table.allMemberInfo td {
		font-size: 12px;
		vertical-align: middle;
	  	text-align: center;
	}
</style>
    
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
       		<jsp:include page="/back-end/sideBar.jsp"/>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/resources/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Content Row -->
                    <div class="row">
						<%
							MemberService dao = new MemberService();
							pageContext.setAttribute("list", dao.getAll());
						%>
						
						<table class="table table-striped allMemberInfo">
							<tr>
								<th>會員ID</th>
								<th>帳號</th>
								<th>密碼</th>
								<th>姓名</th>
								<th>身分證字號</th>
								<th>出生日期</th>
								<th>電話</th>
								<th>email</th>
								<th>會員狀態</th>
								<th>會員照片</th>
							</tr>
					
							<c:forEach var="memberVO" items="${list}">
								<tr>
									<td>${memberVO.memberNo}</td>
									<td>${memberVO.account}</td>
									<td>${memberVO.password}</td>
									<td>${memberVO.name}</td>
									<td>${memberVO.idNumber}</td>
									<td>${memberVO.birthDate}</td>
									<td>${memberVO.phone}</td>
									<td>${memberVO.email}</td>
									<td>${memberVO.memberState}</td>
									<td><img src="<%=request.getContextPath()%>/GetPicture?id=${memberVO.memberNo}"></td>
								</tr>
							</c:forEach>
						</table>
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; GuideMe 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Logout Modal-->
    <jsp:include page="/back-end/logoutModal.jsp"/>

    <!-- Bootstrap core JavaScript-->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>

</body>

</html>