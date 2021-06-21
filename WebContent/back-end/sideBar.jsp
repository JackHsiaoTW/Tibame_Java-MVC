<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a  class="sidebar-brand d-flex align-items-center justify-content-center"
		href="#" style="padding:0">
		<div class="sidebar-brand-icon">
			<img src="<%=request.getContextPath() %>/resources/img/logo.PNG" alt="" style="width:100%">
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">一般業務</div>

	<!-- Nav Item - 一般業務 Collapse Menu -->
	<li class="nav-item">
		
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#shop"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-store"></i>
			<span>商城相關</span>
		</a>
		<div id="shop" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<h4 class="collapse-header">商品管理:</h4>
				<a class="collapse-item" href="#">上架商品</a> 
				<a class="collapse-item" href="#">修改商品</a> 
				<a class="collapse-item" href="#">下架商品</a>
				<div class="collapse-divider"></div>
				<h4 class="collapse-header">訂單管理:</h4>
				<a class="collapse-item" href="#">瀏覽訂單</a> 
				<a class="collapse-item" href="#">審核退款</a>
			</div>
		</div>
		
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#exper"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-running"></i>
			<span>體驗相關</span>
		</a>
		<div id="exper" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<a class="collapse-item" href="<%=request.getContextPath()%>/back-end/exper_report/listAllExperReport.jsp">體驗檢舉審查</a>
			</div>
		</div>
		
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#attraction"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-map-marked-alt"></i>
			<span>景點相關</span>
		</a>
		<div id="attraction" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/itinery/addAttraction_full.jsp">新增景點</a> 
				<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/itinery/listAllAttraction_full.jsp">檢視景點</a> 
				<a class="collapse-item" href="<%=request.getContextPath() %>/back-end/itinery/listUncheckedAttraction_full.jsp">審核使用者建議景點</a>
			</div>
		</div>
		
		<a class="nav-link" href="#"> 
		<i class="fas fa-comments"></i> 
		<span>線上客服回覆</span>
		</a>
		
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">前台首頁管理</div>

	<!-- Nav Item - 前台首頁 Collapse Menu -->
	<li class="nav-item">
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#news"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-home"></i>
			<span>最新消息管理</span>
		</a>
		<div id="news" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<a class="collapse-item" href="#">a1</a> 
				<a class="collapse-item" href="#">a2</a> 
				<a class="collapse-item" href="#">a3</a>
			</div>
		</div>
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#promote"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-home"></i>
			<span>主打促銷管理</span>
		</a>
		<div id="promote" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<a class="collapse-item" href="#">a1</a> 
				<a class="collapse-item" href="#">a2</a> 
				<a class="collapse-item" href="#">a3</a>
			</div>
		</div>
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#questions"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-home"></i>
			<span>常見問題管理</span>
		</a>
		<div id="questions" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<a class="collapse-item" href="#">a1</a> 
				<a class="collapse-item" href="#">a2</a> 
				<a class="collapse-item" href="#">a3</a>
			</div>
		</div>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">基本資料管理</div>

	<!-- Nav Item - Members -->
	<li class="nav-item">
		<a class="nav-link" href="<%=request.getContextPath()%>/back-end/member/getAllMember.jsp">
		<i class="fas fa-user-edit"></i> 
		<span>會員管理</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link collapsed" href="#"
			data-toggle="collapse" data-target="#empManage"
			aria-expanded="true" aria-controls="collapsePages"> 
			<i class="fas fa-briefcase"></i> 
			<span>員工管理</span>
		</a>
		<div id="empManage" class="collapse"
			aria-labelledby="headingPages" data-parent="#accordionSidebar">
			<div class="collapse-bg py-2 collapse-inner rounded">
				<h6 class="collapse-header">員工資料管理:</h6>
				<a class="collapse-item" href="#">新增員工資料</a> 
				<a class="collapse-item" href="#">修改員工資料</a> 
				<div class="collapse-divider"></div>
				<h6 class="collapse-header">員工權限管理:</h6>
				<a class="collapse-item" href="#">查看權限</a> 
			</div>
		</div>
	</li>
	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->
	<div class="sidebar-heading">其他功能</div>

	<!-- Nav Item - Charts -->
	<li class="nav-item">
		<a class="nav-link" href="#"> 
		<i class="fas fa-fw fa-chart-area"></i> 
		<span>Charts</span>
		</a>
	</li>

	<!-- Nav Item - Tables -->
	<li class="nav-item">
		<a class="nav-link" href="#"> 
		<i class="fas fa-fw fa-table"></i> 
		<span>Tables</span>
		</a>
	</li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>

</ul>