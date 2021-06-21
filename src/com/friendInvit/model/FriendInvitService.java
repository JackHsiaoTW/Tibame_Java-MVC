package com.friendInvit.model;

import java.sql.Timestamp;
import java.util.List;

import com.notify.model.NotifyVO;

public class FriendInvitService {
	private FriendInvit_interface dao;

	public FriendInvitService() {
//		dao = new FriendInvitJDBCDAO();
		dao = new FriendInvitDAO();
	}

	public FriendInvitVO addFInvit(Integer adder, Integer confirmer) {
		FriendInvitVO friendInvitVO = new FriendInvitVO();
		friendInvitVO.setAdder(adder);
		friendInvitVO.setConfirmer(confirmer);
		dao.insert(friendInvitVO);
		return friendInvitVO;
	}

	public void delete(Integer friendInvitNo) {
		dao.delete(friendInvitNo);
	}

	public FriendInvitVO getOneFInvit(Integer friendInvitNo) {
		return dao.findByPrimaryKey(friendInvitNo);
	}

	public FriendInvitVO getOneFInvitBy2MemNo(Integer memberNo1, Integer memberNo2) {
		return dao.findBy2MemNo(memberNo1, memberNo2);
	}

	public List<FriendInvitVO> getOneFInvitByMemNo(Integer memberNo) {
		return dao.findByMemNo(memberNo);
	}

	public List<FriendInvitVO> getAll() {
		return dao.getAll();
	}

	public FriendInvitVO addFInvitWithNotify(Integer adder, Integer confirmer, Integer notifyPerson,
			String notifyContent, Timestamp notifyTime) {
		FriendInvitVO friendInvitVO = new FriendInvitVO();
		friendInvitVO.setAdder(adder);
		friendInvitVO.setConfirmer(confirmer);

		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNotifyPerson(notifyPerson);
		notifyVO.setNotifyContent(notifyContent);
		notifyVO.setNotifyTime(notifyTime);

		Integer friendInviNo = dao.insertWithNotify(friendInvitVO, notifyVO);
		friendInvitVO.setFriendInvitNo(friendInviNo);

		return friendInvitVO;
	}

}
