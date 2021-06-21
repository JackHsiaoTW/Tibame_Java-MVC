package com.itine.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

import com.itine.model.ItineService;
import com.itine.model.ItineVO;
import com.itineItem.model.ItineItemService;
import com.itineItem.model.ItineItemVO;
import com.itineMember.model.ItineMemberService;
import com.itineMember.model.ItineMemberVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class ItineServlet
 */
public class ItineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItineServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	Timer timer;
	public void init() {
		timer = new Timer();
		Date firstTime = new GregorianCalendar(2021,2,1).getTime();
		long period = 24*60*60*1000; //一天更新一次
		TimerTask task = new TimerTask() {
			ItineItemService itineItemSvc = new ItineItemService();
			ItineService itineSvc = new ItineService();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<ItineItemVO> list = itineItemSvc.getAll();
				for(ItineItemVO itineItemVO : list) {
					Integer itineNo = itineItemVO.getItineNo();
					Integer attraNo = itineItemVO.getAttraNo();
					Long startTime = itineItemVO.getStartTime().getTime();
					Long now = System.currentTimeMillis();
					if(now>startTime) {
						ItineVO itineVO = itineSvc.findByItineNo(itineNo);
						String itineName = itineVO.getItineName();
						String itineBoard = itineVO.getItineBoard();
						Integer itineStatus = itineVO.getItineStatus();
						if (itineStatus==0||itineStatus==1){
						itineStatus = 3;
						itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
						System.out.println("已將行程編號 "+itineNo+" 改為已出遊");
						}
					}
				}
			}
		};
		timer.scheduleAtFixedRate(task, firstTime, period);
		
	}
	public void destroy() {
		timer.cancel();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		//設定會員session
		if(request.getParameter("memberNo")!=null) {
		String memberNoStr = request.getParameter("memberNo");
		Integer memberNo = new Integer(memberNoStr);
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.getOneMember(memberNo);
		session.setAttribute("memberVO", memberVO);
		}
		
		
		if("getOneMemberAllItine_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理 (這邊沒有錯誤處理)**********************/
				 String memberNoStr = request.getParameter("memberNo");
				 Integer memberNo = new Integer(memberNoStr);
				/***************************2.開始查詢資料*****************************************/
				
				//找出這個會員是哪些行程的創建人
				ItineService itineSvc = new ItineService();
				List<ItineVO> list = itineSvc.findByBuilder(memberNo);
				System.out.println(list);
				
				//要找出 這個會員是哪些行程的團員  
//				List<ItineVO> listI= new ArrayList();
				ItineMemberService itineMemberSvc = new ItineMemberService();
				List<ItineMemberVO> listM = itineMemberSvc.findByMemberNo(memberNo);
				for(ItineMemberVO itineMemberVO : listM) {
					ItineVO itineVO = itineSvc.findByItineNo(itineMemberVO.getItineNo());
					list.add(itineVO);
				}
				System.out.println(list);
				
				
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/front-end/itinery/itinery_select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("listMemberAllItine", list);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
				successView.forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/itinery_select_page.jsp");
				failureView.forward(request,response);
			} 
		}
		
		if("getOne_For_Update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/front-end/itinery/listMemberAllItine.jsp】 
			
			try {
				/*  接收參數*/
				System.out.println(request.getParameter("itineNo"));
				String itineNoStr = request.getParameter("itineNo");
				Integer itineNo = new Integer(itineNoStr);
				System.out.println(itineNo);
				/*  送出請求*/
				ItineService itineSvc = new ItineService();
				ItineVO itineVO = itineSvc.findByItineNo(itineNo);
				/*  轉交頁面*/
				request.setAttribute("itineVO", itineVO);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/update_itine_input.jsp");
				successView.forward(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgs.add("修改資料取出時失敗: "+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
				
			} 
			
			
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer itineNo = new Integer(request.getParameter("itineNo"));
				
				String itineName = request.getParameter("itineName");
				if(itineName==null||itineName.trim().length()==0) {
					errorMsgs.add("景點名稱請勿空白");
				}
				
				Integer itineStatus = new Integer(request.getParameter("itineStatus"));
				
				String itineBoard = request.getParameter("itineBoard");
				
				ItineVO itineVO  = new ItineVO();
				itineVO.setItineNo(itineNo);
				itineVO.setItineName(itineName);
				itineVO.setItineStatus(itineStatus);
				itineVO.setItineBoard(itineBoard);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("itineVO", itineVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/update_itine_input.jsp");
					failureView.forward(request, response);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ItineService itineSrc = new ItineService();
				itineSrc.updateItine(itineName, itineStatus, itineBoard, itineNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/		
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/update_itine_input.jsp");
				failureView.forward(request, response);
			} 
			
			
			
		}
		
		if("getOne_For_FakeDelete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");

			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			try {
				Integer itineNo = new Integer(request.getParameter("itineNo"));
				
				String itineName = request.getParameter("itineName");

				Integer itineStatus = new Integer(2);
				
				String itineBoard = request.getParameter("itineBoard");
				
				ItineVO itineVO  = new ItineVO();
				itineVO.setItineNo(itineNo);
				itineVO.setItineName(itineName);
				itineVO.setItineStatus(itineStatus);
				itineVO.setItineBoard(itineBoard);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("itineVO", itineVO);
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				ItineService itineSrc = new ItineService();
				itineSrc.updateItine(itineName, itineStatus, itineBoard, itineNo);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/		
				RequestDispatcher successView = request.getRequestDispatcher(requestURL);
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			} 
		}
		
		if("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String itineName = request.getParameter("itineName");
				if(itineName==null||itineName.trim().length()==0) {
					errorMsgs.add("行程名稱請勿空白");
				}
				
				Integer itineStatus = 0;
				
				String itineBoard = request.getParameter("itineBoard");
				
				ItineVO itineVO  = new ItineVO();
				itineVO.setItineName(itineName);
				itineVO.setItineStatus(itineStatus);
				itineVO.setItineBoard(itineBoard);
				
				if(!errorMsgs.isEmpty()) {
					request.setAttribute("itineVO", itineVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/addItinery.jsp");
					failureView.forward(request, response);
					return;
				}
				
				/***************************2.開始修改資料*****************************************/
				 session = request.getSession();
				ItineService itineSrc = new ItineService();
				 Integer memberNo = ((MemberVO)session.getAttribute("memberVO")).getMemberNo();				
				itineSrc.addItine(itineName, memberNo);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/		
				session.setAttribute("itineName", itineName);
				RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
				successView.forward(request, response);
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/update_itine_input.jsp");
				failureView.forward(request, response);
			} 
			
		}
		
		if("addTest".equals(action)) {
			System.out.println("test ok!");
			System.out.println(request.getParameter("itineName"));
			String itineName = request.getParameter("itineName").trim();
			Integer builder = ((MemberVO)session.getAttribute("memberVO")).getMemberNo();
			ItineService itineSvc = new ItineService();
			itineSvc.addItine(itineName, builder);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
			
		}
		if("finishItine".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineService itineSvc = new ItineService();
			ItineVO itineVO = itineSvc.findByItineNo(itineNo);
			String itineName = itineVO.getItineName();
			Integer itineStatus = 1;
			String itineBoard = itineVO.getItineBoard();
			itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
					
		}
		if("unfinishItine".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineService itineSvc = new ItineService();
			ItineVO itineVO = itineSvc.findByItineNo(itineNo);
			String itineName = itineVO.getItineName();
			Integer itineStatus = 0;
			String itineBoard = itineVO.getItineBoard();
			itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
					
		}
		if("fakeDelete".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineService itineSvc = new ItineService();
			ItineVO itineVO = itineSvc.findByItineNo(itineNo);
			String itineName = itineVO.getItineName();
			Integer itineStatus = 2;
			String itineBoard = itineVO.getItineBoard();
			itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
//			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
//			successView.forward(request, response);
			System.out.println("Received itineNo:"+request.getParameter("itineNo"));
			
			JSONArray array = new JSONArray();
			JSONObject obj = new JSONObject();
			try {
				obj.put("word","已確認刪除!");
				array.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		if("played".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineService itineSvc = new ItineService();
			ItineVO itineVO = itineSvc.findByItineNo(itineNo);
			String itineName = itineVO.getItineName();
			Integer itineStatus = 3;
			String itineBoard = itineVO.getItineBoard();
			itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
		}
		if("changeName".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineService itineSvc = new ItineService();
			ItineVO itineVO = itineSvc.findByItineNo(itineNo);
			String itineName = request.getParameter("itineName");
			Integer itineStatus = itineVO.getItineStatus();
			String itineBoard = itineVO.getItineBoard();
			itineSvc.updateItine(itineName, itineStatus, itineBoard, itineNo);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("更名成功");
			out.flush();
			out.close();
			
		}
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
