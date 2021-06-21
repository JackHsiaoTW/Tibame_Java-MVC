package com.message.webSocket;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class WebSocketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public WebSocketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("friendChat".equals(action)) {
			String friendUserNoStr = req.getParameter("friendNo");
			if (friendUserNoStr != null && !friendUserNoStr.isEmpty()) {
				Integer friendUserNo = new Integer(friendUserNoStr);
				MemberService memberSvc = new MemberService();
				MemberVO friendVO = memberSvc.getOneMember(friendUserNo);
				String friendUserName = friendVO.getName();

				req.setAttribute("friendUserNo", friendUserNo);
				req.setAttribute("friendUserName", friendUserName);
				req.setAttribute("userName", ((MemberVO) req.getSession().getAttribute("memberVO")).getName());

				RequestDispatcher dispatcher = req.getRequestDispatcher("/front-end/message/chatNew.jsp");
				dispatcher.forward(req, res);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
