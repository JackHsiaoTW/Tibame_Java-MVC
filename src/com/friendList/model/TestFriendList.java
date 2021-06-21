package com.friendList.model;

import java.util.List;

import com.member.model.MemberVO;

public class TestFriendList {
	public static void main(String[] args) {
		FriendListService dao = new FriendListService();

		// insert
//		FriendListVO friendListVO = dao.addFriendList(3, 5);

		// delete
//		dao.deleteFriendList(3, 5);
		
		//findByMemberName
//		List<MemberVO> list = dao.getFriendListByMemName(1, "s");
//		for (MemberVO memberVO : list) {
//			System.out.println("MemberNo:" + memberVO.getMemberNo());
//			System.out.println("Name:" + memberVO.getName());
//			System.out.println("------------");
//		}
		
		//getOneFriendList
//		FriendListVO friendListVO = dao.getOneFriendList(1, 2);
//		System.out.println("MemberNo:"+friendListVO.getMemberNo());
//		System.out.println("FriendNo:"+friendListVO.getFriendNo());

		// getOneFriendListByMemNo
//		List<FriendListVO> list = dao.getOneFriendListByMemNo(1);
//		for (FriendListVO friendListVO : list) {
//			System.out.println("MemberNo:" + friendListVO.getMemberNo());
//			System.out.println("FriendNo:" + friendListVO.getFriendNo());
//			System.out.println("------------");
//		}

		// getAll
//		List<FriendListVO> list = dao.getAll();
//		for (FriendListVO friendListVO : list) {
//			System.out.println("MemberNo:" + friendListVO.getMemberNo());
//			System.out.println("FriendNo:" + friendListVO.getFriendNo());
//			System.out.println("------------");
//		}
		
		//insertFriListWithDelFriInvit
//		FriendListService friendListSvc = new FriendListService();
//		friendListSvc.addFriendListWithDelFriInvit(1, 8, 4);
		
		//insertFriListWithDelFriInvitAndNotify
		FriendListService friendListSvc = new FriendListService();
		friendListSvc.addFriendListWithDelFriInvitAndNotify(1, 8, 1, 8, "1已成為您的好友", new java.sql.Timestamp(System.currentTimeMillis()));
		
	}
}
