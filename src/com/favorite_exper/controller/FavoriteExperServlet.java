package com.favorite_exper.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import com.favorite_exper.model.*;
import com.favorite_exper.model.FavoriteExperService;
import com.favorite_exper.model.FavoriteExperVO;



public class FavoriteExperServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		
		
		
		
//		if ("getOne_For_Display".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("exper_no");
//				if (str == null || (str.trim()).isEmpty() == true) {
//					errorMsgs.add("請輸入體驗編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/exper_favor/select_pageFavorite.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer exper_no = null;
//				try {
//					exper_no = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("體驗編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/exper_favor/select_pageFavorite.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				FavoriteExperService favoriteSvc = new FavoriteExperService();
//				List<FavoriteExperVO> favorexpVO = favoriteSvc.getOneFavorite(exper_no);
//				if (favorexpVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/exper_favor/select_pageFavorite.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("favorexpVO", favorexpVO); 
//				String url = "/front-end/exper_favor/listOneFavorite.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/exper_favor/select_pageFavorite.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllFavorite.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer exper_no = new Integer(req.getParameter("exper_no"));
//				
//				/***************************2.開始查詢資料****************************************/
//				FavoriteExperService favoriteSvc = new FavoriteExperService();
//				List<FavoriteExperVO> favorexpVO = favoriteSvc.getOneFavorite(exper_no);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("favorexpVO",favorexpVO);         
//				String url = "/front-end/exper_favor/update_favorite_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/exper_favor/listAllFavorite.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		if ("update".equals(action)) { 
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer exper_no = new Integer(req.getParameter("exper_no").trim());
//				
//				Integer member_no = null;
//				try {
//					member_no = new Integer(req.getParameter("member_no").trim());
//				} catch (NumberFormatException e) {
//					member_no = 0;
//					errorMsgs.add("會員編號請填數字");
//				}
//				
//				FavoriteExperVO favorexpVO = new FavoriteExperVO();
//				favorexpVO.setExper_no(exper_no);
//				favorexpVO.setMember_no(member_no);
//				
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("favorexpVO", favorexpVO); 
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/front-end/exper_favor/update_favorite_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				FavoriteExperService favoriteSvc = new FavoriteExperService();
//				favorexpVO = favoriteSvc.updateFavorite(exper_no, member_no);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("favorexpVO", favorexpVO); 
//				String url = "/front-end/member/listOneFavorite.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/member/update_favorite_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

        if ("insert".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				System.out.print(req.getParameter("exper_no"));
				System.out.print(req.getParameter("member_no"));
				
				Integer exper_no = new Integer(req.getParameter("exper_no").trim());
				Integer member_no = new Integer(req.getParameter("member_no").trim());
				

				FavoriteExperVO favorexpVO = new FavoriteExperVO();
				favorexpVO.setMember_no(member_no);
				favorexpVO.setExper_no(exper_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("favorexpVO", favorexpVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/exper_order/listExperOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				FavoriteExperService favorexpSvc = new FavoriteExperService();
				favorexpSvc.insert(favorexpVO);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/exper_order/listExperOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/exper_order/listExperOrder.jsp");
				failureView.forward(req, res);
			}
		}
		
		
//		if ("delete".equals(action)) { // 來自listAllFavorite.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				Integer exper_no = new Integer(req.getParameter("exper_no"));
//				
//				/***************************2.開始刪除資料***************************************/
//				FavoriteExperService favoriteSvc = new FavoriteExperService();
//				favoriteSvc.deleteFavorite(exper_no);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/front-end/exper_favor/listAllFavorite.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front-end/exper_favor/listAllFavorite.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		if("addFavor".equals(action)){
			int collect = 0;
			Integer member_no = new Integer(req.getParameter("member_no"));
			Integer exper_no = new Integer(req.getParameter("exper_no"));
			JSONObject json = new JSONObject();
			FavoriteExperService favorexpSvc = new FavoriteExperService();
			try {
				collect = favorexpSvc.addFavor(member_no, exper_no);
				json.put("collect", collect);
			} catch (JSONException e) {
				collect = favorexpSvc.getCountFavor(exper_no);
				out.print(collect);
			}
			out.print(collect);
			out.flush();
			out.close();
			return;
		}
		
		if("deleteFavor".equals(action)){
			int collect = 0;
			Integer member_no = new Integer(req.getParameter("member_no"));
			Integer exper_no = new Integer(req.getParameter("exper_no"));
			JSONObject json = new JSONObject();
			FavoriteExperService favorexpSvc = new FavoriteExperService();
			try {
				collect = favorexpSvc.deleteFavor(member_no, exper_no);
				json.put("collect", collect);
			} catch (JSONException e) {
				collect = favorexpSvc.getCountFavor(exper_no);
				out.print(collect);
			}
			out.print(collect);
			out.flush();
			out.close();
			return;
		}
		
		
	}
}
