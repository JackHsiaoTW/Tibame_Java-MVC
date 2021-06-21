package com.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.JedisPoolUtil;

public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginOutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		JedisPool jedisPool = JedisPoolUtil.getJedisPool();

		if ("login".equals(action)) {
//			System.out.println("---in--action:" + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String email = req.getParameter("email").trim();
				if (email == null || email.isEmpty()) {
					errorMsgs.add("請輸入email!");
				}
				String password = req.getParameter("password");
				if (password == null || password.isEmpty()) {
					errorMsgs.add("請輸入密碼！");
				}
				if (!errorMsgs.isEmpty()) {
					String url = "/front-end/Login.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
				}
//				System.out.println("page---email:" + email);
//				System.out.println("page---password:" + password);

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneByEmail(email);
//				System.out.println("memberVO:" + memberVO);
//				System.out.println("db---email:" + memberVO.getEmail());
//				System.out.println("db---password:" + memberVO.getPassword());

				String forwardUrl = "";
				if ((memberVO == null) || ((memberVO != null) && (!password.equals(memberVO.getPassword())))) {
//					System.out.println("memberVO is null");
					forwardUrl = "/front-end/Login.jsp";
					errorMsgs.add("帳號密碼不正確！");
					RequestDispatcher forwarPage = req.getRequestDispatcher(forwardUrl);
					forwarPage.forward(req, res);
				} else {
//					System.out.println("memberVO is not null");
//					forwardUrl = "/front-end/index.jsp";
					HttpSession session = req.getSession();
					forwardUrl = (String) session.getAttribute("location");
					if (forwardUrl == null || forwardUrl.isEmpty()) {
						forwardUrl = req.getContextPath() + "/front-end/index.jsp";
					}
					session.setAttribute("memberVO", memberVO);
					session.setAttribute("notifyUserID", memberVO.getMemberNo());
					session.setAttribute("countAlert", 0);
					res.sendRedirect(forwardUrl);
				}

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/Login.jsp");
				failureView.forward(req, res);
			}

		} else if ("logout".equals(action)) {
			HttpSession session = req.getSession(false);
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/front-end/index.jsp");

		} else if ("forgotPassword".equals(action)) {
//			System.out.println("---in--action:" + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String email = req.getParameter("email").trim();
			if (email == null || email.isEmpty()) {
				errorMsgs.add("請填寫email!");
			}

			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneByEmail(email);
			if (memberVO == null) {
				errorMsgs.add("查無此email使用者!");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/front-end/RestPassword2.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}

			req.setAttribute("restPwdOption", "1");

			ServletContext context = getServletContext();
			String configPath = context.getRealPath("/config.properties");
			String activatePagePath = context.getRealPath("/front-end/RestPasswordMailPage.html");
			String forgetPwdUrl = context.getContextPath() + "/front-end/RestPassword2.jsp";
			HashMap<String, String> confMap = new HashMap<String, String>();
			confMap.put("configPath", configPath);
			confMap.put("activatePagePath", activatePagePath);
			confMap.put("contextPath", context.getContextPath());
//			confMap.put("imgUrl", context.getContextPath()+"/resources/img/logo.PNG");
			confMap.put("subject", "重設密碼信件");
			confMap.put("toMail", email);
//			confMap.put("account", account);
			confMap.put("whichMail", "forgotPasswordMail");
			confMap.put("forgetPwdUrl", forgetPwdUrl);
			// 寄網際密碼的信
			new util.MailUtil().sendMail(confMap);

			String forwardUrl = "/front-end/RestPassword2.jsp";
			RequestDispatcher forwardPage = req.getRequestDispatcher(forwardUrl);
			forwardPage.forward(req, res);

		} else if ("forgotPasswordCheck".equals(action) || "setNewPassword".equals(action)) { // --合併forgotPasswordCheck與setNewPassword
//			System.out.println("---in--action:" + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String token = req.getParameter("token");

			Jedis jedis = null;
			jedis = jedisPool.getResource();
			jedis.auth("123456");

			String memberMail = jedis.get(token);
			String newPassword = req.getParameter("newPassword");
			String forwardUrl = "/front-end/RestPassword2.jsp";
			RequestDispatcher forwardPage = req.getRequestDispatcher(forwardUrl);

			if (token != null && !token.isEmpty() && memberMail != null && !memberMail.isEmpty()) {

				req.setAttribute("restPwdOption", "2"); // 輸入新密碼
				req.setAttribute("memberMail", memberMail);
				req.setAttribute("restPwdToken", token);

				if ("forgotPasswordCheck".equals(action)) {
					forwardPage.forward(req, res);
					return;
				} else if ("setNewPassword".equals(action)) {
					try {
						if (newPassword == null || newPassword.isEmpty()) {
							errorMsgs.add("請輸入密碼!");
						}

						if (!errorMsgs.isEmpty()) {
							forwardPage.forward(req, res);
							return;
						}

						MemberService memberSvc = new MemberService();
						MemberVO memberVO = memberSvc.getOneByEmail(memberMail);
						memberSvc.updateMmeber(memberVO.getMemberNo(), memberVO.getAccount(), newPassword,
								memberVO.getName(), memberVO.getIdNumber(), memberVO.getBirthDate(),
								memberVO.getPhone(), memberMail, memberVO.getMemberState(), memberVO.getMemberPic(),
								memberVO.getLiscePic1(), memberVO.getLiscePic2(), memberVO.getLiscePic3(),
								memberVO.getLisceName1(), memberVO.getLisceName2(), memberVO.getLisceName3());

						req.setAttribute("restPwdOption", "3");
						jedis.del(token);
						forwardPage.forward(req, res);
						return;

					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						forwardPage.forward(req, res);
						return;
					}
				}
			}

			jedis.close();
			// 密碼重設連結已超過時效
			req.setAttribute("restPwdOption", "4");
			forwardPage.forward(req, res);

		} else if ("returnLogin".equals(action)) {
//			System.out.println("---in--action:" + action);
			req.removeAttribute("restPwdOption");
			String forwardUrl = req.getContextPath() + "/front-end/index.jsp";
			res.sendRedirect(forwardUrl);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}