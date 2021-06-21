package com.member.model;

import java.util.List;

import com.friendList.model.FriendListVO;

public interface MemberDAO_interface {
	public void insert(MemberVO member);

	public void update(MemberVO member);

	public void updateLise(MemberVO member);

	public void delete(Integer member_no);

	public MemberVO findByPrimaryKey(Integer member_no);

	public List<MemberVO> findByName(String name);

	public MemberVO findByEmail(String email);
	
	public MemberVO findByAccount(String account);

	public List<FriendListVO> findByNameForFreinds(Integer memNO, String name);

	public List<MemberVO> getAll();
}
