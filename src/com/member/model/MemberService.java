package com.member.model;

import java.util.List;

import com.friendList.model.FriendListVO;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
//		dao = new MemberJBDCDAO();
		dao = new MemberDAO();
	}

	public MemberVO addMember(String account, String password, String name, String idNumber, java.sql.Date birthDate,
			String phone, String email, Integer memberState, byte[] memberPic, byte[] liscePic1, byte[] liscePic2,
			byte[] liscePic3, String lisceName1, String lisceName2, String lisceName3) {
		MemberVO memberVO = new MemberVO();
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setName(name);
		memberVO.setIdNumber(idNumber);
		memberVO.setBirthDate(birthDate);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setMemberState(memberState);
		memberVO.setMemberPic(memberPic);
		memberVO.setLiscePic1(liscePic1);
		memberVO.setLiscePic2(liscePic2);
		memberVO.setLiscePic3(liscePic3);
		memberVO.setLisceName1(lisceName1);
		memberVO.setLisceName2(lisceName2);
		memberVO.setLisceName3(lisceName3);
		dao.insert(memberVO);
		return memberVO;
	}

	public MemberVO updateMmeber(Integer memberNo, String account, String password, String name, String idNumber,
			java.sql.Date birthDate, String phone, String email, Integer memberState, byte[] memberPic,
			byte[] liscePic1, byte[] liscePic2, byte[] liscePic3, String lisceName1, String lisceName2,
			String lisceName3) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberNo(memberNo);
		memberVO.setAccount(account);
		memberVO.setPassword(password);
		memberVO.setName(name);
		memberVO.setIdNumber(idNumber);
		memberVO.setBirthDate(birthDate);
		memberVO.setPhone(phone);
		memberVO.setEmail(email);
		memberVO.setMemberState(memberState);
		memberVO.setMemberPic(memberPic);
		memberVO.setLiscePic1(liscePic1);
		memberVO.setLiscePic2(liscePic2);
		memberVO.setLiscePic3(liscePic3);
		memberVO.setLisceName1(lisceName1);
		memberVO.setLisceName2(lisceName2);
		memberVO.setLisceName3(lisceName3);
		dao.update(memberVO);
		memberVO = dao.findByPrimaryKey(memberNo);
		return memberVO;
	}

	public MemberVO updateMmeberForLise(Integer memberNo, byte[] liscePic1, byte[] liscePic2, byte[] liscePic3,
			String lisceName1, String lisceName2, String lisceName3) {
		MemberVO memberVO = new MemberVO();
		memberVO.setMemberNo(memberNo);
		memberVO.setLiscePic1(liscePic1);
		memberVO.setLiscePic2(liscePic2);
		memberVO.setLiscePic3(liscePic3);
		memberVO.setLisceName1(lisceName1);
		memberVO.setLisceName2(lisceName2);
		memberVO.setLisceName3(lisceName3);
		dao.updateLise(memberVO);
		memberVO = dao.findByPrimaryKey(memberNo);
		return memberVO;
	}

//	public void deleteMember(Integer memberNo) {
//		dao.delete(memberNo);
//	}

	public MemberVO getOneMember(Integer memberNo) {
		return dao.findByPrimaryKey(memberNo);
	}

	public List<MemberVO> getAllByName(String name) {
		return dao.findByName(name);
	}

	public MemberVO getOneByEmail(String email) {
		return dao.findByEmail(email);
	}

	public MemberVO getOneByAccount(String account) {
		return dao.findByAccount(account);
	}

	public List<FriendListVO> getAllByNameForFreinds(Integer memNO, String name) {
		return dao.findByNameForFreinds(memNO, name);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
