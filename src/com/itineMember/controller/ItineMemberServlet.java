package com.itineMember.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

import com.friendList.model.FriendListService;
import com.itineMember.model.ItineMemberService;
import com.itineMember.model.ItineMemberVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.notify.model.NotifyService;

/**
 * Servlet implementation class ItineMemberServlet
 */
public class ItineMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItineMemberServlet() {
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

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("Controller: ItineMemberServlet");
		System.out.println("action---" + action);
		HttpSession session = request.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

		if ("listAllItineMember".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			List<ItineMemberVO> list = itineMemberSvc.findByItineNo(itineNo);
			// 成功轉交
			session = request.getSession();
			session.setAttribute("list", list);
			request.setAttribute("list", list);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listItineAllMember.jsp");
			successView.forward(request, response);
		}

		if ("updateMemberIsEditable".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			String memberNoStr = request.getParameter("memberNo");
			Integer memberNo = new Integer(memberNoStr);
			String isEditableStr = request.getParameter("isEditable");
			Integer isEditable = new Integer(isEditableStr);
			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			itineMemberSvc.updateItineMember(itineNo, memberNo, isEditable);
			List<ItineMemberVO> list = itineMemberSvc.findByItineNo(itineNo);
			// 成功轉交
//			request.setAttribute("list", list);
//			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listItineAllMember.jsp");
//			successView.forward(request, response);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("修改編輯狀態成功");
			out.flush();
			out.close();
		}

		if ("fakeDeleteMember".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			String memberNoStr = request.getParameter("memberNo");
			Integer memberNo = new Integer(memberNoStr);

			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			itineMemberSvc.updateItineMember(itineNo, memberNo, 3);
			List<ItineMemberVO> list = itineMemberSvc.findByItineNo(itineNo);
			// 成功轉交
//			request.setAttribute("list", list);
//			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listItineAllMember.jsp");
//			successView.forward(request, response);
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("假刪除團員成功");
			out.flush();
			out.close();
		}

		if ("acceptItine".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			String memberNoStr = request.getParameter("memberNo");
			Integer memberNo = new Integer(memberNoStr);

			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			itineMemberSvc.updateItineMember(itineNo, memberNo, 1);
//			List<ItineMemberVO> list = itineMemberSvc.findByItineNo(itineNo);

			// 成功轉交
//			request.setAttribute("list", list);
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
		}
		if ("declineItine".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			String memberNoStr = request.getParameter("memberNo");
			Integer memberNo = new Integer(memberNoStr);

			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			itineMemberSvc.updateItineMember(itineNo, memberNo, 3);

			// 成功轉交
			RequestDispatcher successView = request.getRequestDispatcher("/front-end/itinery/listMemberAllItine.jsp");
			successView.forward(request, response);
		}

		if ("addItineMember".equals(action)) {
			// 接收參數
			String itineNoStr = request.getParameter("itineNo");
			Integer itineNo = new Integer(itineNoStr);
			String memberNoStr = request.getParameter("memberNo");
			Integer memberNo = new Integer(memberNoStr);
			// 請求變更
			ItineMemberService itineMemberSvc = new ItineMemberService();
			try {
				itineMemberSvc.addItineMember(itineNo, memberNo, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				itineMemberSvc.updateItineMember(itineNo, memberNo, 0);
			}
			// 成功轉交
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("加入成功");
			out.flush();
			out.close();

		}

		if ("getAllItineMember".equals(action)) {
			Integer itineNo = new Integer(request.getParameter("itineNo"));
			ItineMemberService itineMemberSvc = new ItineMemberService();
			MemberService memberSvc = new MemberService();
			List<ItineMemberVO> memberList = itineMemberSvc.findByItineNo(itineNo);
			JSONArray arr = new JSONArray();
			for (ItineMemberVO itineMemberVO : memberList) {
				JSONObject obj = new JSONObject(itineMemberVO);
				try {
					Integer memberNo = obj.getInt("memberNo");
					MemberVO aMemberVO = memberSvc.getOneMember(memberNo);
					String name = aMemberVO.getName();
					obj.put("name", name);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				arr.put(obj);
			}
//			JSONArray arr = new JSONArray(memberList);

			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toString());
			out.flush();
			out.close();
		}

		if ("searchFriend".equals(action)) {
			Integer memberNo = new Integer(request.getParameter("memberNo"));
			String nameStr = request.getParameter("nameStr").trim();
			FriendListService friendListSvc = new FriendListService();
			List<MemberVO> list = friendListSvc.getFriendListByMemName(memberNo, nameStr);
			JSONArray arr = new JSONArray(list);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(arr.toString());
			out.flush();
			out.close();
		}

		if ("notifyItineMember".equals(action)) {
			Integer notifyPerson = new Integer(request.getParameter("notifyPerson"));
			String notifyContent = request.getParameter("notifyContent");
			System.out.println(notifyPerson);
			System.out.println(notifyContent);
			java.sql.Timestamp notifyTime = new Timestamp(System.currentTimeMillis());
			NotifyService notifySvc = new NotifyService();
			notifySvc.addNotify(notifyPerson, notifyContent, notifyTime);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("已通知對方");
			out.flush();
			out.close();
		}
		
		if("notifyBuilderYes".equals(action)) {
			Integer notifyPerson = new Integer(request.getParameter("notifyPerson"));
			String notifyContent = request.getParameter("notifyContent");
			System.out.println(notifyPerson);
			System.out.println(notifyContent);
			java.sql.Timestamp notifyTime = new Timestamp(System.currentTimeMillis());
			NotifyService notifySvc = new NotifyService();
			notifySvc.addNotify(notifyPerson, notifyContent, notifyTime);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("已通知對方");
			out.flush();
			out.close();
		}
		
		if("notifyBuilderNo".equals(action)) {
			Integer notifyPerson = new Integer(request.getParameter("notifyPerson"));
			String notifyContent = request.getParameter("notifyContent");
			System.out.println(notifyPerson);
			System.out.println(notifyContent);
			java.sql.Timestamp notifyTime = new Timestamp(System.currentTimeMillis());
			NotifyService notifySvc = new NotifyService();
			notifySvc.addNotify(notifyPerson, notifyContent, notifyTime);
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write("已通知對方");
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
