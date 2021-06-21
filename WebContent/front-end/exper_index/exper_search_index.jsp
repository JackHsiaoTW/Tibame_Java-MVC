<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="author" content="colorlib.com">
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/resources/css/main.css" rel="stylesheet" />
    <!--      Custom fonts for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

<!--     Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/hamburgers.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/nav-bar.css">

<!--     Bootstrap core JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/jquery-ui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/bootstrap.bundle.min.js"></script>
<!--     Core plugin JavaScript -->
    <script src="<%=request.getContextPath()%>/resources/js/jquery.easing.min.js"></script>

<!--     Custom scripts for all pages -->
    <script src="<%=request.getContextPath()%>/resources/js/sb-admin-2.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/nav-bar.js"></script>
    <style>
    .s002 {
  min-height: 100vh;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-pack: center;
      justify-content: center;
  -ms-flex-align: center;
      align-items: center;
  font-family: 'Poppins', sans-serif;
  background-image:url("<%=request.getContextPath()%>/resources/img/Searchs_002.png");
  background-size: cover;
  background-position: center center;
  padding: 15px;
}
    </style>
  </head>
  <body>
<%@include file="/front-end/myNavBar.jsp"%>
    <div class="s002">
      <form METHOD="post" ACTION="<%=request.getContextPath()%>/ExperOrder/ExperOrder.do" name="form1" id="foract">
        <c:set var="key_word" scope="page" value="${param.key_word}" />
        <c:set var="datefilter1" scope="page" value="${param.datefilter1}" />
        <fieldset>
          <legend>由專家舉辦的特色體驗</legend>
        </fieldset>
        <div class="inner-form">
          <div class="input-field first-wrap">
            <div class="icon-wrap">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"></path>
              </svg>
            </div>
            <input name="key_word" type="text" placeholder="體驗關鍵字：" />
          </div>
          <div class="input-field second-wrap">
            <div class="icon-wrap">
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                <path d="M17 12h-5v5h5v-5zM16 1v2H8V1H6v2H5c-1.11 0-1.99.9-1.99 2L3 19c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2h-1V1h-2zm3 18H5V8h14v11z"></path>
              </svg>
            </div>
            <input name="datefilter1" class="datepicker" id="depart" type="text" placeholder="體驗日期：" />
          </div>

          <div class="input-field fifth-wrap">
          <input type="hidden" name="action" value="getExperOrderByKeyDate">
            <button class="btn-search" type="submit">搜尋</button>
          </div>
        </div>
      </form>
    </div>
    <script src="<%=request.getContextPath()%>/resources/js/choices.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/flatpickr.js"></script>
    <script>
      flatpickr(".datepicker",
      {});

    </script>
    <script>
      const choices = new Choices('[data-trigger]',
      {
        searchEnabled: false,
        itemSelectText: '',
      });

    </script>
  </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
