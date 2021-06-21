package com.itineItem.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.attraction.model.AttractionService;
import com.attraction.model.AttractionVO;
import com.itine.model.ItineService;
import com.itine.model.ItineVO;
import com.itineItem.model.ItineItemService;
import com.itineItem.model.ItineItemVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class ItineItemServlet
 */

public class ItineItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItineItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("itineItemController");
		System.out.println("---action:"+action);
		HttpSession session = request.getSession();
		//設定會員session
		if(request.getParameter("memberNo")!=null) {
		String memberNoStr = request.getParameter("memberNo");
		Integer memberNo = new Integer(memberNoStr);
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMember(memberNo);
		session.setAttribute("memberVO", memberVO);
		}
		
		if("listAllItineItem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理 (這邊沒有錯誤處理)**********************/
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			/***************************2.開始永續層存取*****************************************/
			ItineItemService itineItemSvc = new ItineItemService();
			List<ItineItemVO> list = itineItemSvc.findByItineNo(itineNo);
			/***************************3.完成,準備轉交(Send the Success view)*************/		
			session.setAttribute("itineNo", itineNo);
			request.setAttribute("list", list);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listItineAllItineItem.jsp");
			successView.forward(request,response);
		
		
		
		}
		
//		if("add".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			request.setAttribute("errorMsgs", errorMsgs);
//			String requestURL = request.getParameter("requestURL");
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				Integer itineNo = new Integer(request.getParameter("itineNo"));
//				System.out.println(itineNo);
//				Integer attraNo = new Integer(request.getParameter("attraNo"));
//				System.out.println(attraNo);
//				java.sql.Timestamp startTime = null;
//				System.out.println(request.getParameter("startTime"));
//				try {
//					startTime = java.sql.Timestamp.valueOf(request.getParameter("startTime").trim());
//				} catch (IllegalArgumentException e) {
//					startTime=new java.sql.Timestamp(System.currentTimeMillis());
////					errorMsgs.add("請輸入開始時間!");
//				}
//				System.out.println(startTime);
//				java.sql.Timestamp endTime = null;
//				try {
//					endTime = java.sql.Timestamp.valueOf(request.getParameter("endTime").trim());
//				} catch (IllegalArgumentException e) {
//					endTime=new java.sql.Timestamp(System.currentTimeMillis());
////					errorMsgs.add("請輸入結束時間!");
//				}
//				System.out.println(endTime);
//				String note = request.getParameter("note");
//				System.out.println(note);
//				Integer manager = new Integer(request.getParameter("manager"));
//				System.out.println(manager);
//				Boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
//				System.out.println(isDone);
//				java.sql.Date finishDate;
//				try {
//					finishDate = java.sql.Date.valueOf(request.getParameter("finishDate").trim());
//				} catch (Exception e) {
//					finishDate =new java.sql.Date(System.currentTimeMillis());
//				}
//				System.out.println(finishDate);
//				String taskNote = request.getParameter("taskNote");
//				System.out.println(taskNote);
//				System.out.println("參數都有拿到嗎?");
//				ItineItemVO itineItemVO = new ItineItemVO();
//				itineItemVO.setItineNo(itineNo);
//				itineItemVO.setAttraNo(attraNo);
//				itineItemVO.setStartTime(startTime);
//				itineItemVO.setEndTime(endTime);
//				itineItemVO.setNote(note);
//				itineItemVO.setManager(manager);
//				itineItemVO.setIsDone(isDone);
//				itineItemVO.setFinishDate(finishDate);
//				itineItemVO.setTaskNote(taskNote);
//				System.out.println(itineItemVO);
//				
//				if(!errorMsgs.isEmpty()) {
//					request.setAttribute("itineItemVO", itineItemVO);
//					System.out.println(itineItemVO);
//					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/addItineItem.jsp");
//					failureView.forward(request,response);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				ItineItemService itineItemSvc = new ItineItemService();
//				itineItemSvc.addItineItem(itineNo, attraNo, startTime, endTime);
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
//				successView.forward(request, response);
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				System.out.println("是你嗎?");
//				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/addItineItem.jsp");
//				failureView.forward(request, response);
//			}
//		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理 (這邊沒有錯誤處理)**********************/
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			Integer attraNo = new Integer((String)request.getParameter("attraNo"));
			/***************************2.開始永續層存取*****************************************/
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo,attraNo);
			/***************************3.完成,準備轉交(Send the Success view)*************/		
//			session.setAttribute("itineNo", itineNo);
			request.setAttribute("itineItemVO", itineItemVO);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/update_itineItem_input.jsp");
			successView.forward(request,response);
		
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer itineNo = new Integer(request.getParameter("itineNo"));
				System.out.println(itineNo);
				Integer attraNo = new Integer(request.getParameter("attraNo"));
				System.out.println(attraNo);
				java.sql.Timestamp startTime = null;
				System.out.println(request.getParameter("startTime"));
				try {
					startTime = java.sql.Timestamp.valueOf(request.getParameter("startTime").trim());
				} catch (IllegalArgumentException e) {
					startTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入開始時間!");
				}
				System.out.println(startTime);
				java.sql.Timestamp endTime = null;
				try {
					endTime = java.sql.Timestamp.valueOf(request.getParameter("endTime").trim());
				} catch (IllegalArgumentException e) {
					endTime=new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入結束時間!");
				}
				System.out.println(endTime);
				String note = request.getParameter("note");
				System.out.println(note);
				ItineService itineSvc = new ItineService();
				ItineVO itineVO = itineSvc.findByItineNo(itineNo);
				Integer manager = itineVO.getBuilder();
				Boolean isDone = false;
				java.sql.Date finishDate = new java.sql.Date(System.currentTimeMillis());
				String taskNote ="";
				
				ItineItemVO itineItemVO = new ItineItemVO();
				itineItemVO.setItineNo(itineNo);
				itineItemVO.setAttraNo(attraNo);
				itineItemVO.setStartTime(startTime);
				itineItemVO.setEndTime(endTime);
				itineItemVO.setNote(note);
				itineItemVO.setManager(manager);
				itineItemVO.setIsDone(isDone);
				itineItemVO.setFinishDate(finishDate);
				itineItemVO.setTaskNote(taskNote);
				System.out.println(itineItemVO);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("itineItemVO", itineItemVO);
					System.out.println(itineItemVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/addItineItem.jsp");
					failureView.forward(request,response);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ItineItemService itineItemSvc = new ItineItemService();
				itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				System.out.println("是你嗎?");
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/addItineItem.jsp");
				failureView.forward(request, response);
			}
			
		}
		if("updateFinishDate".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			java.sql.Date finishDate = java.sql.Date.valueOf(request.getParameter("finishDate"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			java.sql.Timestamp startTime = itineItemVO.getStartTime();
			java.sql.Timestamp endTime = itineItemVO.getEndTime();
			Integer manager = itineItemVO.getManager();
			String note = itineItemVO.getNote();
			Boolean isDone = itineItemVO.getIsDone();
			String taskNote = itineItemVO.getTaskNote();
			itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
			System.out.println("修改完成日期成功");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("修改預計完成日成功");
			out.flush();
			out.close();
		}
		
		if("updateManager".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			Integer manager = new Integer(request.getParameter("manager"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			java.sql.Timestamp startTime = itineItemVO.getStartTime();
			java.sql.Timestamp endTime = itineItemVO.getEndTime();
			String note = itineItemVO.getNote();
			Boolean isDone = itineItemVO.getIsDone();
			java.sql.Date finishDate = itineItemVO.getFinishDate();
			String taskNote = itineItemVO.getTaskNote();
			itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
			System.out.println("修改負責人成功");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("修改負責人成功");
			out.flush();
			out.close();
			
			
		}
		
		if("updateIsDone".equals(action)) {
			
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			Boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			java.sql.Timestamp startTime = itineItemVO.getStartTime();
			java.sql.Timestamp endTime = itineItemVO.getEndTime();
			String note = itineItemVO.getNote();
			Integer manager = itineItemVO.getManager();
			java.sql.Date finishDate = itineItemVO.getFinishDate();
			String taskNote = itineItemVO.getTaskNote();
			itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
			System.out.println("修改是否完成成功");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("修改是否完成成功");
			out.flush();
			out.close();
		}
		
		if("updateTaskNote".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			String taskNote = request.getParameter("taskNote");
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			java.sql.Timestamp startTime = itineItemVO.getStartTime();
			java.sql.Timestamp endTime = itineItemVO.getEndTime();
			String note = itineItemVO.getNote();
			Integer manager = itineItemVO.getManager();
			java.sql.Date finishDate = itineItemVO.getFinishDate();
			Boolean isDone = itineItemVO.getIsDone();
			itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
			System.out.println("修改分工備註成功");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("修改成功");
			out.flush();
			out.close();
			
		}
		
		if("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			String requestURL = request.getParameter("requestURL");
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer itineNo = new Integer(request.getParameter("itineNo"));
				Integer attraNo = new Integer(request.getParameter("attraNo"));
				
				
				if(!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/listItineAllItineItem.jsp");
					failureView.forward(request,response);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ItineItemService itineItemSvc = new ItineItemService();
				itineItemSvc.deleteItineItem(itineNo, attraNo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/listItineAllItineItem.jsp");
				failureView.forward(request, response);
			}			
			
		}
		
		if("showNote".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			JSONObject obj = new JSONObject(itineItemVO);
			AttractionService attraSvc = new AttractionService();
			AttractionVO attraVO = attraSvc.getOne(attraNo);
			String attraName = attraVO.getAttraName();
			try {
				obj.put("attraName", attraName);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
			
		}
		if("showTaskNote".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			JSONObject obj = new JSONObject(itineItemVO);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();
			
		}
		if("saveFile".equals(action)) {
				System.out.println("entered");

				System.out.println(request.getParameter("schedule"));
				try {
					JSONObject schedule = new JSONObject(request.getParameter("schedule"));
					Integer itineNo = schedule.getInt("Itine_No");
					JSONArray arr = schedule.getJSONArray("itineItems");
					System.out.println(arr);
					for(int i = 0 ; i<arr.length(); i++) {
					JSONObject itineItem = arr.getJSONObject(i);
					Integer attraNo = new Integer(itineItem.getString("Attra_No"));
					String startTimeStr = itineItem.getString("Start_Time");
					java.sql.Timestamp startTime= Timestamp.valueOf(startTimeStr);
					String endTimeStr =itineItem.getString("End_Time");
					java.sql.Timestamp endTime = Timestamp.valueOf(endTimeStr);
					String note = itineItem.getString("Note");
					System.out.println(""+itineNo+attraNo+startTime+endTime+note);
					MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
					Integer manager = memberVO.getMemberNo();
					Boolean isDone = false;
					java.sql.Date finishDate = Date.valueOf(startTimeStr.substring(0, 10));
					String taskNote = itineItem.getString("Task_Note");
					ItineItemService itineItemSvc = new ItineItemService();
					itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
					}
					System.out.println("修改成功");
					response.setContentType("text/plain; charset=utf-8");
					PrintWriter out = response.getWriter();
					out.write("存檔成功");
					out.flush();
					out.close();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			
		}
		if("addByAjax".equals(action)) {
			System.out.println("addByAjax entered");

			Integer itineNo = new Integer(request.getParameter("Itine_No"));
			Integer attraNo = new Integer(request.getParameter("Attra_No"));
			String startTimeStr = request.getParameter("Start_Time");
			java.sql.Timestamp startTime= Timestamp.valueOf(startTimeStr);
			String endTimeStr = request.getParameter("End_Time");
			java.sql.Timestamp endTime = Timestamp.valueOf(endTimeStr);
//			String note = request.getParameter("Note");
			MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
			Integer manager = memberVO.getMemberNo();
//			Boolean isDone = false;
//			java.sql.Date finishDate = Date.valueOf(startTimeStr.substring(0, 10));
//			String taskNote = "";
			ItineItemService itineItemSvc = new ItineItemService();
			itineItemSvc.addItineItem(itineNo, attraNo,startTime, endTime,manager);
			System.out.println("新增行程明細至資料庫了");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("新增此行程明細至資料庫了");
			out.flush();
			out.close();
		}
		if("deleteByAjax".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("Itine_No"));
			Integer attraNo = new Integer(request.getParameter("Attra_No"));
			ItineItemService itineItemSvc = new ItineItemService();
			itineItemSvc.deleteItineItem(itineNo, attraNo);
			System.out.println("刪除行程明細了");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("從資料庫刪除此行程明細了");
			out.flush();
			out.close();
			
		}
		if("saveNote".equals(action)) {
			String note = request.getParameter("note");
			Integer attraNo = new Integer(request.getParameter("attraNo"));
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineItemService itineItemSvc = new ItineItemService();
			ItineItemVO itineItemVO = itineItemSvc.findByItineNoAndAttraNo(itineNo, attraNo);
			java.sql.Timestamp startTime = itineItemVO.getStartTime();
			java.sql.Timestamp endTime = itineItemVO.getEndTime();
			Integer manager = itineItemVO.getManager();
			Boolean isDone = itineItemVO.getIsDone();
			java.sql.Date finishDate = itineItemVO.getFinishDate();
			String taskNote = itineItemVO.getTaskNote();
			itineItemSvc.updateItineItem(startTime, endTime, note, manager, isDone, finishDate, taskNote, itineNo, attraNo);
			System.out.println("已儲存備註成功");
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("已儲存備註");
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
