package com.exper_report.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exper_report.model.ExperReportService;
import com.exper_report.model.ExperReportVO;


public class ExperReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExperReportServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// 取得網頁中name為action的傳遞值

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//				Integer report_no = new Integer(req.getParameter("report_no"));
				String reason = req.getParameter("reason");
				if (reason == null || reason.trim().length() == 0) {
					errorMsgs.add("檢舉理由不得為空");
				}
				
				Integer reporter_no = null;
				try {
					reporter_no = new Integer(req.getParameter("reporter_no").trim());
				} catch (NumberFormatException e) {
					reporter_no = 0;
					errorMsgs.add("檢舉人ID請填數字");
				}
				
				Integer reported_exper_no = null;
				try {
					reported_exper_no = new Integer(req.getParameter("reported_exper_no").trim());
				} catch (NumberFormatException e) {
					reporter_no = 0;
					errorMsgs.add("體驗編號請填數字");
				}
				
				ExperReportVO expreVO = new ExperReportVO();
				expreVO.setReporter_no(reporter_no);
				expreVO.setReported_exper_no(reported_exper_no);
				expreVO.setReason(reason);

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_report/addExperReport.jsp");
					failureView.forward(req, res);
					return;
				}

				// 新增資料
				ExperReportService expreSvc = new ExperReportService();
				expreSvc.addER(reporter_no, reported_exper_no, reason);
				// 完成新增轉交
				String url = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/exper_report/addExperReport.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("report_no");
				if (str == null || (str.trim()).isEmpty() == true) {
					errorMsgs.add("請輸入體驗檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_report/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer report_no = null;
				try {
					report_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_report/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperReportService expreSvc = new ExperReportService();
				ExperReportVO expreVO = expreSvc.getOneReportNo(report_no);
				if (expreVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_report/select_page_report.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expreVO", expreVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/exper_report/listOneExperReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/exper_report/select_page_report.jsp");
				failureView.forward(req, res);
			}

		}

		// step1.從listall.jsp中點選其中一筆
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				// 取得listall.jsp網頁中name為s_report_no的參數值
				Integer report_no = new Integer(req.getParameter("report_no"));

				// 開始查詢
				ExperReportService erSvc = new ExperReportService();
				ExperReportVO erVO = erSvc.getOneReportNo(report_no);

				// 查詢成功，轉交到update_mr.jsp
				req.setAttribute("erVO", erVO);
				String url = "/back-end/exper_report/update_ExperReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_report/listAllExperReport.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer report_no = new Integer(req.getParameter("report_no"));
				Integer reporter_no = new Integer(req.getParameter("reporter_no"));
				Integer reported_exper_no = new Integer(req.getParameter("reported_exper_no"));
				String reason = req.getParameter("reason");
				String reply_content = req.getParameter("reply_content");
				Integer is_checked = new Integer(req.getParameter("is_checked"));

//				String reply_content = req.getParameter("reply_content");
//				String contentReg = "^[(\u4e00-\u9fa5)]{2,300}$";
//
//				if (reply_content == null || reply_content.trim().length() == 0) {
//					errorMsgs.add("檢舉回覆: 請勿空白");
//				} else if (!reply_content.trim().matches(contentReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("檢舉回覆只能是中文");
//				}

				ExperReportVO erVO = new ExperReportVO();
				erVO.setReport_no(report_no);
				erVO.setReporter_no(reporter_no);
				erVO.setReported_exper_no(reported_exper_no);
				erVO.setReason(reason);
				erVO.setReply_content(reply_content);
				erVO.setIs_checked(is_checked);
				System.out.println("=====================");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("erVO", erVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_report/update_ExperReport_input.jsp");
					failureView.forward(req, res);
					return;
				}

				ExperReportService erSvc = new ExperReportService();
				erSvc.update(erVO);
				req.setAttribute("erVO", erVO);
				String url = "/back-end/exper_report/listAllExperReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_report/update_ExperReport_input.jsp");
				failureView.forward(req, res);
			}
		}

	}

}