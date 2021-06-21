package com.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.friendList.model.FriendListService;
import com.friendList.model.FriendListVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import redis.clients.jedis.Jedis;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberServlet() {
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("action---" + action);

		if ("addtMem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String account = req.getParameter("account").trim();
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String password = req.getParameter("password").trim();
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}

				String name = req.getParameter("name");
				String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_, 且長度必需在2到10之間");
				}

				String idNumber = req.getParameter("idNumber").trim();
				if (idNumber == null || idNumber.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				java.sql.Date birthDate = null;
				try {
					birthDate = java.sql.Date.valueOf(req.getParameter("birthDate").trim());
				} catch (IllegalArgumentException e) {
					birthDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				String email = null;
				try {
					email = new String(req.getParameter("email").trim());
					String emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				} catch (NumberFormatException e) {
					email = "";
					errorMsgs.add("email格式不正確");
				}

				byte[] memberPic = null;
//				Collection<Part> parts = req.getParts();
//				for (Part part : parts) {
//					System.out.println("----------------====---");
//					String fileName = getFileNameFromPart(part);
//					if (fileName != null && part.getContentType() != null) {
//						String ContentType = part.getContentType();
//						long size = part.getSize();
//						System.out.println("fileName: " + fileName);
//						System.out.println("ContentType: " + ContentType);
//						System.out.println("size: " + size);
//
//						// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
//						InputStream in = part.getInputStream();
//						memberPic = new byte[in.available()];
//						in.read(memberPic);
//						in.close();
//						System.out.println("memberPic length: " + memberPic.length);
//					}
//				}

				Part memPicPart = req.getPart("memberPic");
				String memPicName = getFileNameFromPart(memPicPart);
				if (memPicName != null && memPicPart.getContentType() != null) {
					InputStream in = memPicPart.getInputStream();
					memberPic = new byte[in.available()];
					in.read(memberPic);
					in.close();
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setName(name);
				memberVO.setIdNumber(idNumber);
				memberVO.setBirthDate(birthDate);
				memberVO.setPhone(phone);
				memberVO.setEmail(email);
				memberVO.setMemberPic(memberPic);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/Register.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMember(account, password, name, idNumber, birthDate, phone, email, 0, memberPic,
						null, null, null, null, null, null);

				ServletContext context = getServletContext();
				String configPath = context.getRealPath("/config.properties");
				String activatePagePath = context.getRealPath("/front-end/member/ActivateMember.html");
//				context.getContextPath();
				HashMap<String, String> confMap = new HashMap<String, String>();
				confMap.put("configPath", configPath);
				confMap.put("activatePagePath", activatePagePath);
				confMap.put("contextPath", context.getContextPath());
				confMap.put("subject", "會員註冊確認信");
				confMap.put("toMail", email);
				confMap.put("account", account);
				confMap.put("whichMail", "activateMail");
				// 寄認證信
				new util.MailUtil().sendMail(confMap);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String forwardUrl = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(forwardUrl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/Register.jsp");
				failureView.forward(req, res);
			}

		} else if ("updateMem".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer memberNo = new Integer(req.getParameter("memberNo").trim());
				String account = req.getParameter("account").trim();
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}

				String password = req.getParameter("password").trim();
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}

				String name = req.getParameter("name");
				String nameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_, 且長度必需在2到10之間");
				}

				String idNumber = req.getParameter("idNumber").trim();
				if (idNumber == null || idNumber.trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}

				java.sql.Date birthDate = null;
				try {
					birthDate = java.sql.Date.valueOf(req.getParameter("birthDate").trim());
				} catch (IllegalArgumentException e) {
					birthDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}

				String email = null;
				try {
					email = new String(req.getParameter("email").trim());
					String emailReg = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$";
				} catch (NumberFormatException e) {
					email = "";
					errorMsgs.add("email格式不正確");
				}

				byte[] memberPic = null;

				Part memPicPart = req.getPart("memberPic");
				String memPicName = getFileNameFromPart(memPicPart);
				if (memPicName != null && memPicPart.getContentType() != null) {
					InputStream in = memPicPart.getInputStream();
					memberPic = new byte[in.available()];
					in.read(memberPic);
					in.close();
				}

				MemberVO memberVO = new MemberVO();
				memberVO.setAccount(account);
				memberVO.setPassword(password);
				memberVO.setName(name);
				memberVO.setIdNumber(idNumber);
				memberVO.setBirthDate(birthDate);
				memberVO.setPhone(phone);
				memberVO.setEmail(email);
				memberVO.setMemberPic(memberPic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/EditProfile.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memPicVO = memSvc.getOneMember(memberNo);
				if (memberPic == null) {
					memberPic = memPicVO.getMemberPic();
					memberVO.setMemberPic(memberPic);
				}
				memberVO = memSvc.updateMmeber(memberNo, account, password, name, idNumber, birthDate, phone, email, 1,
						memberPic, null, null, null, null, null, null);

				HttpSession session = req.getSession(false);
				session.setAttribute("memberVO", memberVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String forwardUrl = "/front-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(forwardUrl);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/EditProfile.jsp");
				failureView.forward(req, res);
			}
		} else if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberVO memberVO = (MemberVO) req.getSession().getAttribute("memberVO");
			req.setAttribute("memberVO", memberVO);
			String url = "/front-end/member/EditProfile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} else if ("getAll".equals(action)) {

		} else if ("queryMemberForAddFriend".equals(action)) {
			Integer memNO = new Integer(req.getParameter("memNO").trim());
			String searchMember = req.getParameter("searchMember").trim();

			if (searchMember != null && !searchMember.isEmpty()) {
				MemberService memSvc = new MemberService();
				List<FriendListVO> queryMemResults = memSvc.getAllByNameForFreinds(memNO, searchMember);

				req.setAttribute("queryMemResults", queryMemResults);

			} else {
				FriendListService fListSvc = new FriendListService();
				List<FriendListVO> queryMemResults = fListSvc.getOneFriendListByMemNo(memNO);
				req.setAttribute("queryMemResults", queryMemResults);
			}

			req.setAttribute("isFist", "N");

			String forwardUrl = "/front-end/friendList/FriendList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(forwardUrl);
			successView.forward(req, res);

		} else if ("queryMemberForAddFriend_ajax".equals(action)) {
			Integer memNO = new Integer(req.getParameter("memNO").trim());
			String searchMember = req.getParameter("searchMember").trim();

			List<FriendListVO> queryMemResults = null;
			JSONArray array = new JSONArray();

			if (searchMember != null && !searchMember.isEmpty()) {
				MemberService memSvc = new MemberService();
				queryMemResults = memSvc.getAllByNameForFreinds(memNO, searchMember);
			} else {
				FriendListService fListSvc = new FriendListService();
				queryMemResults = fListSvc.getOneFriendListByMemNo(memNO);
			}

			req.setAttribute("isFist", "N");

			for (FriendListVO usb : queryMemResults) {
				try {
					JSONObject obj = new JSONObject();
					obj.put("memberNo", usb.getMemberNo());
					obj.put("friendNo", usb.getFriendNo());
					obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + usb.getFriendNo()
							+ "' style='width: 50px; height: 50px;'>");
					array.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();

		} else if ("registerCheck".equals(action)) {
//			System.out.println("---in--action:" + action);
			String email = "";
			Jedis jedis = null;
			String token = req.getParameter("token");
			try {
				jedis = new Jedis("localhost", 6379);
				jedis.auth("123456");
				email = jedis.get(token);
				jedis.del(token);
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneByEmail(email);

			memberSvc.updateMmeber(memberVO.getMemberNo(), memberVO.getAccount(), memberVO.getPassword(),
					memberVO.getName(), memberVO.getIdNumber(), memberVO.getBirthDate(), memberVO.getPhone(), email, 1,
					memberVO.getMemberPic(), memberVO.getLiscePic1(), memberVO.getLiscePic2(), memberVO.getLiscePic3(),
					memberVO.getLisceName1(), memberVO.getLisceName2(), memberVO.getLisceName3());

			String url = "/front-end/member/Welcome.html";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		} else if ("checkIsRegister".equals(action)) {
//			System.out.println("---in--action:" + action);
			String account = req.getParameter("account").trim();
			String email = req.getParameter("email").trim();
//			System.out.println("account:" + account);
//			System.out.println("email:" + email);

			JSONArray array = new JSONArray();
			MemberService memSvc = new MemberService();

			if (account != null  && email != null) {
				MemberVO memberVOByAccount = memSvc.getOneByAccount(account);
				JSONObject obj = new JSONObject();

				try {
					if (memberVOByAccount != null) {
//						System.out.println("memberVOByAccount is exist");
						obj.put("accountCheck", "此帳號已被註冊!");
					}

					MemberVO memberVOByEmail = memSvc.getOneByEmail(email);
					if (memberVOByEmail != null) {
//						System.out.println("memberVOByEmail is exist");
						obj.put("emailCheck", "此Email已被註冊!");
					}
					array.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();

		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
