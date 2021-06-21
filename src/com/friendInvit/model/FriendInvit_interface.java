package com.friendInvit.model;

import java.util.List;

import com.notify.model.NotifyVO;

public interface FriendInvit_interface {
	public void insert(FriendInvitVO friendInvitVO);

//	public void update(FriendInvitVO friendInvitVO);

	public void delete(Integer friendInvitNo);

	public FriendInvitVO findByPrimaryKey(Integer friendInvitNo);

	public FriendInvitVO findBy2MemNo(Integer memberNo1, Integer memberNo2);

	public List<FriendInvitVO> findByMemNo(Integer memberNo);

	public List<FriendInvitVO> getAll();

	public Integer insertWithNotify(FriendInvitVO friendInvitVO, NotifyVO notifyVO);

	public void delete2(Integer friendInvitNo, java.sql.Connection con);

	public void delete3(Integer friendInvitNo, NotifyVO notifyVO, java.sql.Connection con);

}
