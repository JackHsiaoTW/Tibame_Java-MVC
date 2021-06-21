package com.experience.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.experience.model.ExperienceService;
import com.experience.model.ExperienceVO;
import com.member.model.MemberService;


@WebServlet("/ExperienceServlet")
@MultipartConfig
public class ExperienceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
				String str = req.getParameter("exper_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入體驗編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_no = null;
				try {
					exper_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperienceService experSvc = new ExperienceService();
				ExperienceVO experVO = experSvc.getOneExperience(exper_no);
				if (experVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("experVO", experVO);
				String url = "/back-end/experience/listOneExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_no = new Integer(req.getParameter("exper_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperienceService experSvc = new ExperienceService();
				ExperienceVO experVO = experSvc.getOneExperience(exper_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("experVO", experVO); 
				String url = "/back-end/experience/update_exper_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/listAllExperience.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer exper_no = new Integer(req.getParameter("exper_no").trim());
				Integer checker_no = new Integer(req.getParameter("checker_no").trim());
				Integer exper_status = new Integer(req.getParameter("exper_status").trim());
				Integer host_no = new Integer(req.getParameter("host_no").trim());
				Integer exper_type_no = new Integer(req.getParameter("exper_type_no").trim());
				
					
//				String name = req.getParameter("name");
//				String exper_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
//				if (name == null || name.trim().length() == 0) {
//					errorMsgs.add("體驗名稱請勿空白");
//				} else if (!name.trim().matches(exper_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("體驗名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//				}
				
				String name = req.getParameter("name").trim();
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("體驗名稱請勿空白");
				}	

//				Integer host_no = null;
//				try {
//					host_no = new Integer(req.getParameter("host_no").trim());
//				} catch (NumberFormatException e) {
//					host_no = 0;
//					errorMsgs.add("發表人ID請填數字");
//				}

//				Integer checker_no = null;
//				try {
//					checker_no = new Integer(req.getParameter("checker_no").trim());
//				} catch (NumberFormatException e) {
//					checker_no = 0;
//					errorMsgs.add("審核者ID請填數字");
//				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
					if(price<0) errorMsgs.add("請填入正整數");
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("體驗價格請填數字");
				}
				
//				String exper_descr = req.getParameter("exper_descr");
//				String exper_descrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
//				if (exper_descr == null || exper_descr.trim().length() == 0) {
//					errorMsgs.add("體驗敘述請勿空白");
//				} else if (!exper_descr.trim().matches(exper_descrReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("體驗敘述: 只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
//				}
				String exper_descr = req.getParameter("exper_descr").trim();
				if (exper_descr == null || exper_descr.trim().length() == 0) {
					errorMsgs.add("體驗敘述請勿空白");
				}	

			
//				Integer exper_status = null;
//				try {
//					exper_status = new Integer(req.getParameter("exper_status").trim());
//				} catch (NumberFormatException e) {
//					exper_status = 0;
//					errorMsgs.add("體驗狀態請勿空白");
//				}
				
//				Integer exper_type_no= null;
//				try {
//					exper_type_no= new Integer(req.getParameter("exper_type_no").trim());
//				} catch (NumberFormatException e) {
//					exper_type_no= 0;
//					errorMsgs.add("體驗種類ID請填數字");
//				}
				
				ExperienceVO experVO = new ExperienceVO();
				experVO.setExper_no(exper_no);
				experVO.setHost_no(host_no);
				experVO.setChecker_no(checker_no);				
				experVO.setName(name);
				experVO.setPrice(price);
				experVO.setExper_descr(exper_descr);
				experVO.setExper_status(exper_status);
				experVO.setExper_type_no(exper_type_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("experVO", experVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/update_exper_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ExperienceService experSvc = new ExperienceService();
				experSvc.update(experVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("experVO", experVO); 
				String url = "/back-end/experience/listOneExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/update_exper_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer host_no = new Integer(req.getParameter("host_no").trim());
				String name = req.getParameter("name");
				String exper_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("體驗名稱請勿空白");
				} else if (!name.trim().matches(exper_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("體驗名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
				}

//				Integer host_no = null;
//				try {
//					host_no = new Integer(req.getParameter("host_no").trim());
//				} catch (NumberFormatException e) {
//					host_no = 0;
//					errorMsgs.add("發表人ID請填數字");
//				}

//				Integer checker_no = null;
//				try {
//					checker_no = new Integer(req.getParameter("checker_no").trim());
//				} catch (NumberFormatException e) {
//					checker_no = 0;
//					errorMsgs.add("審核者ID請填數字");
//				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("體驗價格請填數字");
				}
				
				String exper_descr = req.getParameter("exper_descr");
//				String exper_descrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,300}$";
//				if (exper_descr == null || exper_descr.trim().length() == 0) {
//					errorMsgs.add("體驗敘述請勿空白");
//				} else if (!exper_descr.trim().matches(exper_descrReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("體驗敘述: 只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
//				}

								
//				Integer exper_status = null;
//				try {
//					exper_status = new Integer(req.getParameter("exper_status").trim());
//				} catch (NumberFormatException e) {
//					exper_status = 0;
//					errorMsgs.add("體驗狀態請填數字");
//				}
				
				Integer exper_type_no= null;
				try {
					exper_type_no= new Integer(req.getParameter("exper_type_no").trim());
				} catch (NumberFormatException e) {
					exper_type_no= 0;
					errorMsgs.add("體驗種類ID請填數字");
				}

				ExperienceVO experVO = new ExperienceVO();
				experVO.setHost_no(host_no);
//				experVO.setChecker_no(checker_no);				
				experVO.setName(name);
				experVO.setPrice(price);
				experVO.setExper_descr(exper_descr);
//				experVO.setExper_status(exper_status);
				experVO.setExper_type_no(exper_type_no);
				
//				MemberVO memVO = new MemberVO();
//				memVO.setLiscePic1



				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("experVO", experVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/addExperience.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ExperienceService experSvc = new ExperienceService();
//				experVO = experSvc.addExper(host_no,checker_no, name, price,
//						exper_descr,exper_status,exper_type_no);
				experSvc.insert(experVO);
//				MemberService memSvc = new MemberService();
//				memSvc.updateMmeberForLise(host_no, liscePic1, liscePic2, liscePic3, lisceName1, lisceName2, lisceName3)
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/experience/listOneExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/addExperience.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer exper_no = new Integer(req.getParameter("exper_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ExperienceService experSvc = new ExperienceService();
				experSvc.deleteExper(exper_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/experience/listAllExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/experience/listAllExperience.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
