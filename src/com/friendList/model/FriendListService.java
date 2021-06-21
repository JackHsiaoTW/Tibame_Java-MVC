package com.friendList.model;

import java.sql.Timestamp;
import java.util.List;

import com.member.model.MemberVO;
import com.notify.model.NotifyVO;

public class FriendListService {
	private FriendList_interface dao;

	public FriendListService() {
//		dao = new FriendListJDBCDAO();
		dao = new FriendListDAO();
	}

	public FriendListVO addFriendList(Integer memberNo, Integer friendNo) {
		FriendListVO friendListVO = new FriendListVO();
		friendListVO.setMemberNo(memberNo);
		friendListVO.setFriendNo(friendNo);
		dao.insert(friendListVO);

		return friendListVO;
	}

	public void deleteFriendList(Integer memberNo, Integer friendNo) {
		dao.delete(memberNo, friendNo);
	}

	public FriendListVO getOneFriendList(Integer memberNo, Integer friendNo) {
		return dao.findByPrimaryKey(memberNo, friendNo);
	}

	public List<MemberVO> getFriendListByMemName(Integer memberNo, String name) {
		return dao.findByMemberName(memberNo, name);
	}

	public List<FriendListVO> getOneFriendListByMemNo(Integer memberNo) {
		return dao.findByMemberNo(memberNo);
	}

	public List<FriendListVO> getAll() {
		return dao.getAll();
	}

	public void addFriendListWithDelFriInvit(Integer memberNo, Integer friendNo, Integer friendInvitNo) {
		FriendListVO friendListVO = new FriendListVO();
		friendListVO.setMemberNo(memberNo);
		friendListVO.setFriendNo(friendNo);

		dao.insertFriListWithDelFriInvit(friendListVO, friendInvitNo);
	}

	public void addFriendListWithDelFriInvitAndNotify(Integer memberNo, Integer friendNo, Integer friendInvitNo,
			Integer notifyPerson, String notifyContent, Timestamp notifyTime) {
		FriendListVO friendListVO = new FriendListVO();
		friendListVO.setMemberNo(memberNo);
		friendListVO.setFriendNo(friendNo);

		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNotifyPerson(notifyPerson);
		notifyVO.setNotifyContent(notifyContent);
		notifyVO.setNotifyTime(notifyTime);

		dao.insertFriListWithDelFriInvitAndNotify(friendListVO, friendInvitNo, notifyVO);
	}

}
