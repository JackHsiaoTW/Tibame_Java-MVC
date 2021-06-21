package com.friendList.model;

import java.util.List;

import com.member.model.MemberVO;
import com.notify.model.NotifyVO;

public interface FriendList_interface {
	public void insert(FriendListVO friendListVO);

//	public void update(FriendListVO friendListVO);

	public void delete(Integer memberNo, Integer friendNo);

	public FriendListVO findByPrimaryKey(Integer memberNo, Integer friendNo);

	public List<MemberVO> findByMemberName(Integer memberNo, String name);

	public List<FriendListVO> findByMemberNo(Integer memberNo);

	public List<FriendListVO> getAll();

	public void insertFriListWithDelFriInvit(FriendListVO friendListVO, Integer friendInvitNo);

	public void insertFriListWithDelFriInvitAndNotify(FriendListVO friendListVO, Integer friendInvitNo,
			NotifyVO notifyVO);
}
