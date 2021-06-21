
package com.exper_order.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exper_order.model.*;


import util.*;

public class ExperOrderServlet extends HttpServlet {
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
				String str = req.getParameter("exper_order_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入體驗梯次編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				Integer exper_order_no = null;
				try {
					exper_order_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗梯次編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperOrderService expordSvc = new ExperOrderService();
				ExperOrderVO expordVO = expordSvc.getOneExperOrder(exper_order_no);
				if (expordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_order/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expordVO", expordVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_order/listOneExperOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_order/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Insert".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_no = new Integer(req.getParameter("exper_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ExperOrderVO expordVO = new ExperOrderVO();
				expordVO.setExper_no(exper_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("expordVO", expordVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_order/addExperOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_order/listOneExperOrder.jsp");
				failureView.forward(req, res);
			}
		}

	
		 if("getExperOrderByKeyDate".equals(action)) { // 來自listAllEmp.jsp的請求
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
						ExperOrderService expordSvc = new ExperOrderService();
						List<ExperOrderVO>datefilter1List=null;
						Map<String, String[]> comQueMap=new TreeMap<String,String[]>();
			
						try {
							/*************************** 1.接收請求參數 ****************************************/
							String key_word=req.getParameter("key_word").trim();
							if(key_word.isEmpty()) {
								System.out.println("使用者未輸入key_word");
							}
							
							String checkString = "^[(\u4e00-\u9fa5)]{1,}$";
							String checkNum="^[(0-9)]{1,}$";
							
							if(key_word.matches(checkNum)) {
								comQueMap.put("price", new String[]{key_word});
							}else if(key_word.matches(checkString)) {
								comQueMap.put("word", new String[]{key_word});
							}
							
							
							String datefilter1 = req.getParameter("datefilter1");
							System.out.println(datefilter1);
							if(datefilter1 == null || datefilter1.trim().length() < 1) {
								System.out.println("使用者未輸入時間 送他全部");
							} else {
								comQueMap.put("datefilter1",new String[]{datefilter1});
								
							}
							List<ExperOrderVO>queryFinalList=expordSvc.getAll(comQueMap);
							String url = "/front-end/exper_order/listExperOrder.jsp";
							req.setAttribute("queryFinalList", queryFinalList);
							RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
							successView.forward(req, res);
			
							/*************************** 其他可能的錯誤處理 **********************************/
						} catch (Exception e) {
							errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/select_page.jsp");
							failureView.forward(req, res);
						}
					}
		

		if ("upDateExperOrderStatus".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer exper_order_no = new Integer(req.getParameter("exper_order_no"));
				Integer exper_order_status = new Integer(req.getParameter("exper_order_status"));


				/*************************** 2.開始新增資料 ***************************************/
				ExperOrderService expordSvc = new ExperOrderService();
				expordSvc.upDateExperPerStatus(exper_order_no, exper_order_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/exper_order/listOneExperOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_order/listOneExperOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer exper_no = new Integer(req.getParameter("exper_no").trim());

	
				java.sql.Timestamp apply_start = new java.sql.Timestamp(
						java.sql.Date.valueOf(req.getParameter("apply_start")).getTime());
				java.sql.Timestamp apply_end = new java.sql.Timestamp(
						java.sql.Date.valueOf(req.getParameter("apply_end")).getTime());
				if(apply_start.after(apply_end)) {
					errorMsgs.add("報名結束時間不得早於報名開始時間");
				}
				java.sql.Timestamp exper_order_start = java.sql.Timestamp
						.valueOf(req.getParameter("exper_order_start") + ":00.0");
				if(apply_end.after(exper_order_start)) {
					errorMsgs.add("體驗開始時間不得早於報名結束時間");
				}
				java.sql.Timestamp exper_order_end = java.sql.Timestamp
						.valueOf(req.getParameter("exper_order_end") + ":00.0");
				if(exper_order_end.before(exper_order_start)) {
					errorMsgs.add("體驗結束時間不得早於體驗開始時間");
				}
				Integer exper_max_limit=null;
				Integer exper_min_limit =null;
				Integer exper_now_price =null;
				
				String checkNum="^[(0-9)]{1,}$";
				
				
				String str=req.getParameter("exper_max_limit");
				if(str.matches(checkNum)) {
					exper_max_limit = Integer.parseInt(req.getParameter("exper_max_limit"));
				}else {
					errorMsgs.add("人數上限請輸入數字");
				}
				
				
				str=req.getParameter("exper_min_limit");
				if(str.matches(checkNum)) {
					exper_min_limit = Integer.parseInt(req.getParameter("exper_min_limit"));
				}else {
					errorMsgs.add("人數下限請輸入數字");
				}
				
				
				str=req.getParameter("exper_now_price");
				if(str.matches(checkNum)) {
					exper_now_price = Integer.parseInt(req.getParameter("exper_now_price"));
					if(exper_now_price <=0) errorMsgs.add("價格不得小於等於0");
				}else {
					errorMsgs.add("價錢請輸入數字");
				}
				
				Integer exper_order_status, exper_apply_sum;
				if (req.getParameter("exper_order_status") == null) {
					exper_order_status = 1;
				} else {
					exper_order_status = Integer.parseInt(req.getParameter("exper_order_status"));
				}
				
				if (req.getParameter("exper_apply_sum") == null) {
					exper_apply_sum = 0;
				} else {

					exper_apply_sum = Integer.parseInt(req.getParameter("exper_apply_sum"));
				}

				ExperOrderVO expordVO = new ExperOrderVO();
				expordVO.setExper_no(exper_no);
				expordVO.setApply_start(apply_start);
				expordVO.setApply_end(apply_end);
				expordVO.setExper_order_start(exper_order_start);
				expordVO.setExper_order_end(exper_order_end);
				expordVO.setExper_max_limit(exper_max_limit);
				expordVO.setExper_min_limit(exper_min_limit);
				expordVO.setExper_now_price(exper_now_price);
				expordVO.setExper_order_status(exper_order_status);
				expordVO.setExper_apply_sum(exper_apply_sum);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("expordVO", expordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_order/addExperOrder.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ExperOrderService expordSvc = new ExperOrderService();
				expordSvc.insert(exper_no, apply_start, apply_end,
						exper_order_start, exper_order_end, exper_max_limit, exper_min_limit,
						exper_now_price, exper_order_status, exper_apply_sum);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/experience/listOneExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_order/addExperOrder.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/*************************** 1.接收請求參數 ***************************************/
//				String mem_id = req.getParameter("mem_id");
//
//				/*************************** 2.開始刪除資料 ***************************************/
//				MemberService memSvc = new MemberService();
//				memSvc.deleteMem(mem_id);
//
//				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/front-end/member/listAllMem.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//
//				/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				RequestDispatcher failureView;
//				failureView = req.getRequestDispatcher("/front-end/member/listAllMem.jsp");
//				failureView.forward(req, res);
//			}
//		}
//	}
	}
}
