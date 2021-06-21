package com.friendList.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.friendList.model.FriendListService;
import com.friendList.model.FriendListVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class FriendListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FriendListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("action---" + action);

		if ("queryFriend".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer memberNo = new Integer(req.getParameter("memNO").trim());
			String searchFriend = req.getParameter("searchFriend").trim();
//			System.out.println("memberNo:" + memberNo);
//			System.out.println("searchFriend:" + searchFriend);

			JSONArray array = new JSONArray();
			FriendListService friendListSvc = new FriendListService();

			if (searchFriend != null && !searchFriend.isEmpty() && memberNo != null) {
				List<MemberVO> frineds = friendListSvc.getFriendListByMemName(memberNo, searchFriend);

				for (MemberVO mem : frineds) {
					try {

//						System.out.println("memberNo:" + mem.getMemberNo());
//						System.out.println("name:" + mem.getName());
//						System.out.println("----------------------");

						JSONObject obj = new JSONObject();
						obj.put("memberNo", mem.getMemberNo());
						obj.put("name", mem.getName());
						obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + mem.getMemberNo()
								+ "' style='width: 50px; height: 50px;'>");
						obj.put("unfriend",
								"<button class=\"btn btn-outline-danger\" id=\"unfriendBtn\" name=\"unfriend\">解除好友</button>");
						array.put(obj);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else if (searchFriend != null && searchFriend.isEmpty() && memberNo != null) {
				List<FriendListVO> friendListVO = friendListSvc.getOneFriendListByMemNo(memberNo);
				MemberService memberSvc = new MemberService();
				List<MemberVO> friends = new ArrayList<MemberVO>();
				MemberVO memberVO = null;

				for (FriendListVO friendsVO : friendListVO) {
					memberVO = memberSvc.getOneMember(friendsVO.getFriendNo());
					friends.add(memberVO);
				}

				for (MemberVO mem : friends) {
					try {

//						System.out.println("memberNo:" + mem.getMemberNo());
//						System.out.println("name:" + mem.getName());
//						System.out.println("----------------------");

						JSONObject obj = new JSONObject();
						obj.put("memberNo", mem.getMemberNo());
						obj.put("name", mem.getName());
						obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + mem.getMemberNo()
								+ "' style='width: 50px; height: 50px;'>");
						obj.put("unfriend",
								"<button class=\"btn btn-outline-danger\" id=\"unfriendBtn\" name=\"unfriend\">解除好友</button>");
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

		} else if ("listAllFriend".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer memNO = new Integer(req.getParameter("memNO").trim());
//			System.out.println("memNO:" + memNO);
			JSONArray array = new JSONArray();

			if (memNO != null) {
				FriendListService friendListSvc = new FriendListService();
				List<FriendListVO> friendListVO = friendListSvc.getOneFriendListByMemNo(memNO);
				MemberService memberSvc = new MemberService();
				List<MemberVO> friends = new ArrayList<MemberVO>();
				MemberVO memberVO = null;

				for (FriendListVO friendsVO : friendListVO) {
					memberVO = memberSvc.getOneMember(friendsVO.getFriendNo());
					friends.add(memberVO);
				}

				for (MemberVO mem : friends) {
					try {

//						System.out.println("memberNo:" + mem.getMemberNo());
//						System.out.println("name:" + mem.getName());
//						System.out.println("----------------------");

						JSONObject obj = new JSONObject();
						obj.put("memberNo", mem.getMemberNo());
						obj.put("name", mem.getName());
						obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + mem.getMemberNo()
								+ "' style='width: 50px; height: 50px;'>");
						obj.put("unfriend",
								"<button class=\"btn btn-outline-danger\" id=\"unfriendBtn\" name=\"unfriend\">解除好友</button>");
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

		} else if ("queryMemberForAddFriend".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer memNO = new Integer(req.getParameter("memNO").trim());
			String searchMember = req.getParameter("searchMember").trim();
//			System.out.println("memNO:" + memNO);
//			System.out.println("searchMember:" + searchMember);

			List<MemberVO> queryMemResults = null;
			JSONArray array = new JSONArray();

			if (searchMember != null && !searchMember.isEmpty()) {
				MemberService memSvc = new MemberService();
				queryMemResults = memSvc.getAllByName(searchMember);

//				for (MemberVO memberVO : queryMemResults) {
//					System.out.println("memberNo:" + memberVO.getMemberNo());
//					System.out.println("account:" + memberVO.getAccount());
//					System.out.println("name:" + memberVO.getName());
//				}

				/*---------------------------------------------*/
				for (MemberVO usb : queryMemResults) {
					try {
						JSONObject obj = new JSONObject();
						obj.put("memberNo", usb.getMemberNo());
						obj.put("name", usb.getName());
						obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + usb.getMemberNo()
								+ "' style='width: 50px; height: 50px;'>");

						FriendListService friendListSvc = new FriendListService();
						FriendListVO loginFriendListVO = friendListSvc.getOneFriendList(memNO, usb.getMemberNo());
						FriendInvitService friendInvitSvc = new FriendInvitService();
						FriendInvitVO friendInvitVO = friendInvitSvc.getOneFInvitBy2MemNo(memNO, usb.getMemberNo());
						if (loginFriendListVO == null) {
							System.out.println("memberNo:" + usb.getMemberNo() + "is not friend!");
//							obj.put("addFriend", "<button id=\"addFriend\" name=\"addFriend\">加入好友</button>");
							obj.put("friendTag", "");

						} else {
//							System.out.println("===memberNo:" + usb.getMemberNo() + "is friend!");
							obj.put("friendTag", "<i class=\"fas fa-heart\"></i>");
						}

						array.put(obj);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}

			} else {

			}

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();

		} else if ("getOneMemInfo".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer loginNo = new Integer(req.getParameter("loginNo").trim());
			Integer memNO = new Integer(req.getParameter("memNO").trim());
			MemberService memSvc = new MemberService();
			JSONArray memInfoArray = new JSONArray();

			if (memNO != null) {
				MemberVO memberVO = memSvc.getOneMember(memNO);
				JSONObject obj = new JSONObject();
				try {
					obj.put("memberNo", memberVO.getMemberNo());
					obj.put("name", memberVO.getName());
					obj.put("showLabel", "<label id='qMemberId' value='" + memberVO.getMemberNo() + "'>"
							+ memberVO.getName() + "</label>");
					obj.put("imgTag", "<img src='" + req.getContextPath() + "/GetPicture?id=" + memberVO.getMemberNo()
							+ "' style='width: 100px; height: 100px;'>");
					FriendListService friendListSvc = new FriendListService();
					FriendListVO loginFriendListVO = friendListSvc.getOneFriendList(loginNo, memNO);
					FriendInvitService friendInvitSvc = new FriendInvitService();
					FriendInvitVO friendInvitVO = friendInvitSvc.getOneFInvitBy2MemNo(loginNo, memNO);
//					System.out.println("=============================");
//					System.out.println("loginFriendListVO:" + (loginFriendListVO == null));
//					System.out.println("friendInvitVO:" + (friendInvitVO == null));
//					if (loginFriendListVO == null && friendInvitVO == null) {
					obj.put("addFriendInvite",
							"<button class=\"btn btn-info\" id=\"addFriendInviteBtn\" name=\"addFriendInviteBtn\">加入好友</button>");
					if (loginFriendListVO == null && friendInvitVO == null) {
						if (loginNo.equals(memNO)) { // 排除可以加自己好友...
							obj.put("isInvitedOrIsFriend", "yes");
						} else {
							obj.put("isInvitedOrIsFriend", "no");
						}
					} else if (loginFriendListVO == null && friendInvitVO != null) {
						obj.put("isInvitedOrIsFriend", "yes");
					} else {
						obj.put("isInvitedOrIsFriend", "yes");
					}

					// 如果friendListt查的到資料＝>顯示解除好友按鈕
					if (loginFriendListVO != null) {
						obj.put("unfriend",
								"<button class=\"btn btn-outline-danger\" id=\"unfriendBtn\" name=\"unfriend\">解除好友</button>");
					}

					memInfoArray.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(memInfoArray.toString());
				out.flush();
				out.close();
			}

		} else if ("addFriend".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer loginNo = new Integer(req.getParameter("loginNo"));
			Integer friendNO = new Integer(req.getParameter("friendNO"));
			Integer friendInvitNo = new Integer(req.getParameter("friendInvitNo"));

			JSONArray array = new JSONArray();

			if (loginNo != null && friendNO != null && friendInvitNo != null) {
				// insert data to friendList
//				System.out.println("insert data to friendList....");
				JSONObject obj = new JSONObject();
				try {
					MemberService memSvc = new MemberService();
					MemberVO loginMemVO = memSvc.getOneMember(loginNo);

					Integer notifyPerson = friendNO;
					String notifyContent = "與" + loginMemVO.getName() + "已成為好友";
					Timestamp notifyTime = new Timestamp(System.currentTimeMillis());

					FriendListService friendListSvc = new FriendListService();
//					friendListSvc.addFriendListWithDelFriInvit(loginNo, friendNO, friendInvitNo);
					friendListSvc.addFriendListWithDelFriInvitAndNotify(loginNo, friendNO, friendInvitNo, notifyPerson,
							notifyContent, notifyTime);

					obj.put("result", "true");
					obj.put("friendName", memSvc.getOneMember(friendNO).getName());
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

		} else if ("unfriend".equals(action)) {
//			System.out.println("---in--action:" + action);
			Integer loginNo = new Integer(req.getParameter("loginNo").trim());
			Integer friendNo = new Integer(req.getParameter("friendNo").trim());

			JSONArray array = new JSONArray();

			if (loginNo != null && friendNo != null) {
				// delete data from friendList
//				System.out.println("delete data from friendList....");
				JSONObject obj = new JSONObject();
				try {
					MemberService memSvc = new MemberService();
					MemberVO friendVO = memSvc.getOneMember(friendNo);
					FriendListService friendListSvc = new FriendListService();
					friendListSvc.deleteFriendList(loginNo, friendNo);
					obj.put("friendName", friendVO.getName());
					obj.put("result", "true");
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
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
