package com.friendInvit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.friendInvit.model.FriendInvitService;
import com.friendInvit.model.FriendInvitVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class FriendInvitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FriendInvitServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("------FriendInvitServlet------");
//		System.out.println("action---" + action);

		if ("addFriendInvite".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer loginNo = new Integer(req.getParameter("loginNo").trim());
			Integer memNO = new Integer(req.getParameter("memNO").trim());
			Integer adder = loginNo;
			Integer confirmer = memNO;

			JSONArray array = new JSONArray();

			if (adder != null && confirmer != null) {
				// insert data to friendInvite
				System.out.println("insert data to friendInvite....");
				JSONObject obj = new JSONObject();

				try {
					MemberService MemberSvc = new MemberService();
					MemberVO adderVO = MemberSvc.getOneMember(adder);
					Integer notifyPerson = confirmer;
					String notifyContent = adderVO.getName() + " 要求加入好友";
					Timestamp notifyTime = new Timestamp(System.currentTimeMillis());
					FriendInvitService friendInvitSvc = new FriendInvitService();
					FriendInvitVO friendInvitVO = friendInvitSvc.addFInvitWithNotify(adder, confirmer, notifyPerson,
							notifyContent, notifyTime);

					if (friendInvitVO != null) {
						obj.put("result", "true");
					} else {
						obj.put("result", "false");
					}

					array.put(obj);

				} catch (JSONException e) {
					e.printStackTrace();
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(array.toString());
				out.flush();
				out.close();
			}

		} else if ("getFriendInvitByMemberNo".equals(action)) {
//			System.out.println("---in--action:" + action);
			String memNoStr = req.getParameter("memNo");
			JSONArray array = new JSONArray();

			if (memNoStr != null && !memNoStr.isEmpty()) {
				Integer memberNo = new Integer(memNoStr);
				FriendInvitService friendInvitSvc = new FriendInvitService();
				List<FriendInvitVO> listFriendInvit = friendInvitSvc.getOneFInvitByMemNo(memberNo);

				for (FriendInvitVO friendInvitVO : listFriendInvit) {
					MemberService memSvc = new MemberService();
					MemberVO adderVO = memSvc.getOneMember(friendInvitVO.getAdder());
					adderVO.getMemberPic();
					JSONObject obj = new JSONObject();

					try {
						System.out.println("friendInvitNo" + friendInvitVO.getFriendInvitNo());
						System.out.println("adder" + friendInvitVO.getAdder());
						System.out.println("confirmer" + friendInvitVO.getConfirmer());
						System.out.println("memberNo" + adderVO.getMemberNo());
						System.out.println("name" + adderVO.getName());
						obj.put("friendInvitNo", friendInvitVO.getFriendInvitNo());
						obj.put("adder", friendInvitVO.getAdder());
						obj.put("confirmer", friendInvitVO.getConfirmer());
						obj.put("adderNo", adderVO.getMemberNo());
						obj.put("adderName", adderVO.getName());
						obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id="
								+ adderVO.getMemberNo() + "' style='width: 50px; height: 50px;'>");
						obj.put("comfirmToFriend",
								"<button class=\"btn btn-outline-primary\" id=\"comfirmToFriendBtn\" name=\"comfirmToFriendBtn\">確認</button>");
						array.put(obj);
					} catch (JSONException e) {
						e.printStackTrace();
					}
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
