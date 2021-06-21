package com.exper_application.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exper_application.model.*;
import com.exper_application.model.ExperApplicationService;
import com.exper_application.model.ExperApplicationVO;
import com.exper_order.model.ExperOrderService;
import com.exper_order.model.ExperOrderVO;
import com.member.model.MemberVO;

public class ExperApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertByMember".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接受請求參數----檢查資料格式
				Integer exper_order_no = new Integer(req.getParameter("exper_order_no"));
				Integer member_no = new Integer(req.getParameter("member_no"));
				String exper_appli_memo = req.getParameter("exper_appli_memo");

				Integer number = null;
				try {
					number = Integer.parseInt(req.getParameter("number"));
				} catch (NumberFormatException ne) {
					errorMsgs.add("人數請輸入數字");
				}
				if (number == 0) {
					errorMsgs.add("參加人數不得為零");
				}

				ExperOrderService expordSvc = new ExperOrderService();
				ExperOrderVO expordVO = expordSvc.getOneExperOrder(exper_order_no);
				Integer exper_now_price = expordVO.getExper_now_price();
				Integer sum = number * exper_now_price;
				Integer exper_appli_status = 0;// 已確認出發
				Integer exper_payment_status = 0;// 付款狀態為 未付款

				ExperApplicationVO expapVO = new ExperApplicationVO();
				expapVO.setMember_no(member_no);
				expapVO.setExper_order_no(exper_order_no);
				expapVO.setNumber(number);
				expapVO.setSum(sum);
				expapVO.setExper_appli_status(exper_appli_status);
				expapVO.setExper_payment_status(exper_payment_status);
				expapVO.setExper_appli_memo(exper_appli_memo);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_appli/addExperAppli.jsp");
					failureView.forward(req, res);
					return;
				}
				ExperApplicationService expapSvc = new ExperApplicationService();
				expapSvc.addExperAppli(member_no, exper_order_no, number, sum, exper_appli_status, exper_payment_status,
						exper_appli_memo);
				String url = "/front-end/exper_appli/listOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("其他錯誤發生");
				e.printStackTrace();
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("exper_appli_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入報名訂單編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_appli/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_appli_no = null;
				try {
					exper_appli_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("報名訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_appli/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperApplicationService expapSvc = new ExperApplicationService();
				ExperApplicationVO expapVO = expapSvc.getOneApplication(exper_appli_no);
				if (expapVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_appli/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expapVO", expapVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/exper_appli/listOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneSouvenirOrder.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/exper_appli/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("memCancelExperAppli".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_appli_no = new Integer(req.getParameter("exper_appli_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperApplicationService expapSvc = new ExperApplicationService();
				ExperApplicationVO expapVO = expapSvc.getOneApplication(exper_appli_no);
				expapVO.setExper_appli_status(1);// 設定 訂單為 已取消狀態
				if (expapVO.getExper_payment_status() == 4) {// 若已付款才更改
					expapVO.setExper_payment_status(2);// 設定付款狀態為 退款中

				}
				expapSvc.changeExperApplitionStatus(expapVO);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/front-end/exper_appli/listOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/exper_appli/listOneExperAppli.jsp");
				failureView.forward(req, res);
			}
		}

		if ("hostnoConfirmRefund".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_appli_no = new Integer(req.getParameter("exper_appli_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperApplicationService expapSvc = new ExperApplicationService();
				ExperApplicationVO expapVO = expapSvc.getOneApplication(exper_appli_no);
				expapVO.setExper_appli_status(1);// 設定 訂單為 已取消狀態
				expapVO.setExper_payment_status(3);// 設定付款狀態為 已退款
				expapSvc.changeExperApplitionStatus(expapVO);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/exper_appli/sellListOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_appli/sellListOneExperAppli.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("hostnoConfirmPay".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_appli_no = new Integer(req.getParameter("exper_appli_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperApplicationService expapSvc = new ExperApplicationService();
				ExperApplicationVO expapVO = expapSvc.getOneApplication(exper_appli_no);
				expapVO.setExper_appli_status(0);// 設定 訂單為 確定報名
				expapVO.setExper_payment_status(4);// 設定付款狀態為 已確認完成付款
				expapSvc.changeExperApplitionStatus(expapVO);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				String url = "/back-end/exper_appli/sellListOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_appli/sellListOneExperAppli.jsp");
				failureView.forward(req, res);
			}
		}
		
		

//		會員付款=============================================================================

		if ("memPayTheBill".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				// 確認一般會員登入狀態
				HttpSession session = req.getSession();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

				Integer memberNo = memberVO.getMemberNo();
				Integer exper_appli_no = new Integer(req.getParameter("exper_appli_no"));
				ExperApplicationService expapSvc = new ExperApplicationService();
				ExperApplicationVO expapVO = expapSvc.getOneApplication(exper_appli_no);

				expapVO.setExper_payment_status(1);// 更改狀態為已付款

				/*************************** 2.開始新增資料 ***************************************/

				expapSvc.changeExperApplitionStatus(expapVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/exper_appli/listOneExperAppli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/exper_appli/addExperAppli.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
