package com.attraction.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.json.JSONObject;

import com.attraction.model.AttractionService;
import com.attraction.model.AttractionVO;

/**
 * Servlet implementation class AttractionServlet
 */

public class AttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttractionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("this is Attraction.do");
		System.out.println("action --" + action);
		if ("add".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String sort = request.getParameter("sort");
				if (sort == null || "".equals(sort)) {
					errorMsgs.add("請選擇分類 ");
				}
				String attraName = request.getParameter("attraName").trim();
				if (attraName == null || attraName.length() == 0) {
					errorMsgs.add("景點名稱請勿空白");
				}
				String descr = request.getParameter("descr");
				String shortLocation = request.getParameter("shortLocation");
				String county = request.getParameter("county");
				String district = request.getParameter("district");
				String location = county+district+shortLocation;
				if(district.length()==0) {
					errorMsgs.add("請至少選擇縣市");
				}
				String attraPic1 = request.getParameter("attraPic1");

				AttractionVO attractionVO = new AttractionVO();
				attractionVO.setSort(sort);
				attractionVO.setAttraName(attraName);
				attractionVO.setDescr(descr);
				attractionVO.setLocation(location);
				attractionVO.setAttraPic1(attraPic1);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("attraVO", attractionVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/itinery/addAttraction_full.jsp");
					failureView.forward(request, response);
					return;
				}
				AttractionService attraSvc = new AttractionService();
				int isOnShelf = 1;
				String attraNo = attraSvc.addAttra(sort, attraName, descr, location, isOnShelf, attraPic1);
				int whichPage = Integer.parseInt(attraNo)/5+1;
				
				String url = "/back-end/itinery/listAllAttraction_full.jsp?whichPage="+whichPage+"&attraNo="+attraNo;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/back-end/itinery/addAttraction_full.jsp");
				failureView.forward(request, response);
			}

		}
		if("searchForAttra".equals(action)) {
		
			
			String attraName = request.getParameter("attraName").trim();
			String sort = request.getParameter("sort");
			
			AttractionService attraSvc = new AttractionService();
			List<AttractionVO> list = attraSvc.getSearchFor(attraName, sort);
			
			request.setAttribute("list", list);
			RequestDispatcher successView = request.getRequestDispatcher("/back-end/itinery/listSearchForAttraction_full.jsp");
			successView.forward(request, response);
			return;
			
			
			
		}

		if ("search".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			request.setAttribute("errorMsgs",errorMsgs);
//			
//			
			String searchfor = request.getParameter("searchfor").trim();
//			if(searchfor.isEmpty()) {
//				errorMsgs.add("搜尋字元不得為空白");
//			}

			String sort = request.getParameter("sort");

//			if(!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/attraction_select_page.jsp");
//				failureView.forward(request, response);
//				return;
//			}

			AttractionService attraSvc = new AttractionService();
			List<AttractionVO> list = attraSvc.getSearchForIsOnShelf(searchfor, sort);

			String jsonstr = new JSONArray(list).toString();
			System.out.println(jsonstr);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(jsonstr);
			out.flush();
			out.close();

//			request.setAttribute("list", list);
//			RequestDispatcher failureView = request.getRequestDispatcher("/front-end/itinery/listSearchForAttraction.jsp");
//			failureView.forward(request, response);
//			return;

		}
		if ("getOne_For_Submit".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				int attraNo = Integer.parseInt(request.getParameter("attraNo"));

				AttractionService attraSvc = new AttractionService();
				AttractionVO attraVO = attraSvc.getOne(attraNo);

				request.setAttribute("attraVO", attraVO);
				String url = "/back-end/itinery/submit_attra_input_full.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/ititnery/listUncheckedAttraction_full.jsp");
				failureView.forward(request, response);
			}

		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				int attraNo = Integer.parseInt(request.getParameter("attraNo"));

				AttractionService attraSvc = new AttractionService();
				AttractionVO attraVO = attraSvc.getOne(attraNo);

				request.setAttribute("attraVO", attraVO);
				String url = "/back-end/itinery/update_attra_input_full.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/back-end/ititnery/listAllAttraction_full.jsp");
				failureView.forward(request, response);
			}

		}
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			String whichPage = request.getParameter("whichPage");
			System.out.println("whichPage :"+whichPage);
			String requestURL = request.getParameter("requestURL");
			String finishURL = request.getParameter("finishURL");

			try {
				String sort = request.getParameter("sort");
				System.out.println(sort);
				String attraName = request.getParameter("attraName1").trim();
				if (attraName == null || attraName.length() == 0) {
					errorMsgs.add("景點名稱請勿空白");
				}
				System.out.println(attraName);
				String descr = request.getParameter("descr");
				System.out.println(descr);
				String shortLocation = request.getParameter("shortLocation");
				String county = request.getParameter("county");
				String district = request.getParameter("district");
				String location = county+district+shortLocation;
				if(district.length()==0) {
					errorMsgs.add("請至少選擇縣市");
				}
				System.out.println(location);
				System.out.println(request.getParameter("isOnShelf"));
				int isOnShelf = Integer.parseInt(request.getParameter("isOnShelf"));
				String attraPic1 = request.getParameter("attraPic1");
				System.out.println(attraPic1);
				int attraNo = Integer.parseInt(request.getParameter("attraNo"));
				AttractionVO attractionVO = new AttractionVO();
				attractionVO.setSort(sort);
				attractionVO.setAttraName(attraName);
				attractionVO.setDescr(descr);
				attractionVO.setLocation(location);
				attractionVO.setIsOnShelf(isOnShelf);
				attractionVO.setAttraPic1(attraPic1);
				attractionVO.setAttraNo(attraNo);

				System.out.println(errorMsgs);
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("attraVO", attractionVO);
					RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
					failureView.forward(request, response);
					return;
				}
				AttractionService attraSvc = new AttractionService();
				attractionVO = attraSvc.updateAttra(sort, attraName, descr, location, isOnShelf, attraPic1, attraNo);

				System.out.println("paramAttraName:"+request.getParameter("paramAttraName"));
				System.out.println("paramSort:"+request.getParameter("paramSort"));
				
				List<AttractionVO> list = attraSvc.getSearchFor(request.getParameter("paramAttraName"), request.getParameter("paramSort"));
				HttpSession session = request.getSession();
				session.setAttribute("list",list);
				
				request.setAttribute("attraVO", attractionVO);
				RequestDispatcher successView = request.getRequestDispatcher(finishURL+"?whichPage="+whichPage);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				System.out.println(errorMsgs);
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}

		}

		if ("notAvailable".equals(action)) {


			int attraNo = Integer.parseInt(request.getParameter("attraNo"));
			AttractionService attraSvc = new AttractionService();
			AttractionVO attractionVO = attraSvc.getOne(attraNo);
			String sort = attractionVO.getSort();
			String attraName = attractionVO.getAttraName();
			String descr = attractionVO.getDescr();
			String location = attractionVO.getLocation();
			int isOnShelf = 2;
			String attraPic1 = attractionVO.getAttraPic1();

			attractionVO.setSort(sort);
			attractionVO.setAttraName(attraName);
			attractionVO.setDescr(descr);
			attractionVO.setLocation(location);
			attractionVO.setIsOnShelf(isOnShelf);
			attractionVO.setAttraPic1(attraPic1);
			attractionVO.setAttraNo(attraNo);

			attractionVO = attraSvc.updateAttra(sort, attraName, descr, location, isOnShelf, attraPic1, attraNo);
			
			System.out.println("paramAttraName:"+request.getParameter("paramAttraName"));
			System.out.println("paramSort:"+request.getParameter("paramSort"));
			List<AttractionVO> list = attraSvc.getSearchFor(request.getParameter("paramAttraName"), request.getParameter("paramSort"));
			HttpSession session = request.getSession();
			session.setAttribute("list",list);
			
			request.setAttribute("attraVO", attractionVO);
			
			String whichPage= request.getParameter("whichPage");
			String finishURL = request.getParameter("finishURL");
			RequestDispatcher successView = request.getRequestDispatcher(finishURL+"?whichPage="+whichPage);
			successView.forward(request, response);

		}
		if ("addAttraction".equals(action)) {
			String attraName = request.getParameter("attraName");
			String sort = request.getParameter("sort");
			String descr = request.getParameter("descr");
			String county = request.getParameter("county");
			String district = request.getParameter("district");
			String shortLocation = request.getParameter("shortLocation");
			if(shortLocation==null) {shortLocation = "";}
			String location = county+district+shortLocation;
			AttractionService attraSvc = new AttractionService();
			String isOnShelfStr = request.getParameter("isOnShelf");
			int isOnShelf = Integer.parseInt(isOnShelfStr);
			
			String attraPic1 = "";
			int attraNo = Integer.parseInt(attraSvc.addAttra(sort, attraName, descr, location, isOnShelf, attraPic1));

			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(attraNo));
			out.flush();

		}
		if ("addTrafficTool".equals(action)) {
			String attraName = request.getParameter("attraName");
			String sort = request.getParameter("sort");
			String descr = request.getParameter("descr");
			String location = request.getParameter("location");
			AttractionService attraSvc = new AttractionService();
			String isOnShelfStr = request.getParameter("isOnShelf");
			int isOnShelf = Integer.parseInt(isOnShelfStr);
			List<AttractionVO> attraVOList = attraSvc.getSearchFor(attraName, sort);
			
			String attraPic1 = attraVOList.get(0).getAttraPic1();
			int attraNo = Integer.parseInt(attraSvc.addAttra(sort, attraName, descr, location, isOnShelf, attraPic1));

			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(String.valueOf(attraNo));
			out.flush();

		}
		if ("getAttraInfo".equals(action)) {
			int attraNo = Integer.parseInt(request.getParameter("attraNo"));
			AttractionService attraSvc = new AttractionService();
			AttractionVO attraVO = attraSvc.getOne(attraNo);
			String attraName = attraVO.getAttraName();
			String location = attraVO.getLocation();
			String descr = attraVO.getDescr();

			JSONObject obj = new JSONObject(attraVO);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(obj.toString());
			out.flush();
			out.close();

		}

	}

}
