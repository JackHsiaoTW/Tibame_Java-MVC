package com.friendInvit.model;

import com.notify.model.NotifyVO;

public class TestFriendInvit {

	public static void main(String[] args) {
		//addFInvitWithNotify
//		FriendInvitVO friendInvitVO = new FriendInvitVO();
//		friendInvitVO.setAdder(1);
//		friendInvitVO.setConfirmer(10);
//		
//		NotifyVO notifyVO = new NotifyVO();
//		notifyVO.setNotifPerson(10);
//		notifyVO.setNotifContent("ssssssssss");
//		notifyVO.setNotifTime(new java.sql.Timestamp(System.currentTimeMillis()));
		
		FriendInvitService friendInvitSvc = new FriendInvitService();
		FriendInvitVO friendInvitVO = friendInvitSvc.addFInvitWithNotify(1, 10, 10, "XXX", new java.sql.Timestamp(System.currentTimeMillis()));

		System.out.println(friendInvitVO.getFriendInvitNo());
		System.out.println(friendInvitVO.getAdder());
		System.out.println(friendInvitVO.getConfirmer());
	}

}
