package com.exper_type.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exper_type.model.*;

public class ExperTypeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//Integer exper_type_no =new Integer(req.getParameter("exper_type_no"));
				
				String str = req.getParameter("exper_type_no");
				if (str == null || (str.trim()).isEmpty() == true) {
					errorMsgs.add("請輸入體驗種類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_type/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_type_no = null;
				try {
					exper_type_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗種類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_type/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				ExperTypeService extypeSvc = new ExperTypeService();
				ExperTypeVO extypeVO = extypeSvc.getOneExperType(exper_type_no);
				if (extypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_type/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("extypeVO", extypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_type/listOneExperType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_type/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_type_no =new Integer(req.getParameter("exper_type_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperTypeService extypeSvc = new ExperTypeService();
				ExperTypeVO extypeVO = extypeSvc.getOneExperType(exper_type_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("extypeVO", extypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_type/update_ExperType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_type/listAllExperType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ExperTypeVO extypeVO=null;
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer exper_type_no =new Integer(req.getParameter("exper_type_no"));
				
				String exper_type_name=req.getParameter("exper_type_name");
				String nameReg = "^[(\u4e00-\u9fa5)]{2,30}$";
				
				if (exper_type_name == null || exper_type_name.trim().length() == 0) {
					errorMsgs.add("體驗名稱: 請勿空白");
				} else if (!exper_type_name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("體驗名稱只能是中文");
				}


				extypeVO = new ExperTypeVO();
				extypeVO.setExper_type_no(exper_type_no);
				extypeVO.setExper_type_name(exper_type_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("extypeVO", extypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_type/update_ExperType_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ExperTypeService extypeSvc = new ExperTypeService();
				extypeSvc.update(extypeVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("extypeVO", extypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/exper_type/listOneExperType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_type/update_ExperType_input.jsp");
				failureView.forward(req, res);
			}
		}

	        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String exper_type_name=req.getParameter("exper_type_name");
					String nameReg = "^[(\u4e00-\u9fa5)]{2,30}$";
					
					if (exper_type_name == null || exper_type_name.trim().length() == 0) {
						errorMsgs.add("體驗名稱: 請勿空白");
					} else if (!exper_type_name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("體驗名稱只能是中文且兩個字以上");
					}

					ExperTypeVO extypeVO = new ExperTypeVO();
					extypeVO.setExper_type_name(exper_type_name);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("extypeVO", extypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/exper_type/addExperType.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ExperTypeService extypeSvc = new ExperTypeService();
					extypeSvc.insert(exper_type_name);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/back-end/exper_back_index.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_type/addExperType.jsp");
					failureView.forward(req, res);
				}
			}

	}
}
