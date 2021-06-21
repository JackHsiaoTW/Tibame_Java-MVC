package com.member.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import util.Util;

public class TestMember {
	public static void main(String[] args) {
		MemberDAO_interface dao = new MemberJBDCDAO();

//		Add
//		MemberService memSvc = new MemberService();
//		try {
//			byte[] pic = getPictureByteArray("src/items/FC_Barcelona.png");
//			memSvc.addMember("KING", "KING123", "KING","A123456789", java.sql.Date.valueOf("2021-01-25"), "09123456789", "KING@XXX.COM", 0, Util.toObjects(pic), Util.toObjects(pic), Util.toObjects(pic), Util.toObjects(pic), "證照一", "證照二", "證照三");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		"KING", "KING123", "KING", "A123456789", java.sql.Date.valueOf("2021-01-25"), "09123456789", "KING@XXX.COM", true
//		MemberVO memberVO = new MemberVO();
//		memberVO.setAccount("KING");
//		memberVO.setPassword("KING123");
//		memberVO.setName("KING");
//		memberVO.setId_number("A123456789");
//		memberVO.setBirth_date(java.sql.Date.valueOf("2021-01-25"));
//		memberVO.setTelephone("09123456789");
//		memberVO.setEmail("KING@XXX.COM");
//		memberVO.setMember_state(true);

//		Update
//		MemberService memSvc = new MemberService();
//		byte[] pic2;
//		try {
//			pic2 = getPictureByteArray("src/items/pikachu.png");
//			MemberVO memberVO = memSvc.updateMmeber(1, "KING", "KING456", "KING333","A123456789", java.sql.Date.valueOf("2021-01-25"), "09123456789", "KING@XXX.COM", 0, Util.toObjects(pic2), Util.toObjects(pic2), Util.toObjects(pic2), Util.toObjects(pic2), "證照一", "證照二", "證照三");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		delete
//		MemberService svc = new MemberService();
//		svc.deleteMember(1);
		
		// 查詢
//		MemberService memSvc = new MemberService();
//		Integer memberNo = 1;
//		MemberVO memberVO = memSvc.getOneMember(memberNo);
//		System.out.println("memberVO.getMember_no():" + memberVO.getMemberNo());
//		System.out.println("memberVO.getAccount():" + memberVO.getAccount());
//		System.out.println("memberVO.getPassword():" + memberVO.getPassword());
//		System.out.println("memberVO.getName():" + memberVO.getName());
//		System.out.println("memberVO.getId_number():" + memberVO.getIdNumber());
//		System.out.println("memberVO.getBirth_date():" + memberVO.getBirthDate());
//		System.out.println("memberVO.getTelephone():" + memberVO.getPhone());
//		System.out.println("memberVO.getEmail():" + memberVO.getEmail());
//		System.out.println("memberVO.isMember_state():" + memberVO.getMemberState());
//		System.out.println("memberVO.getLisceName1():" + memberVO.getLisceName1());
//		System.out.println("memberVO.getLisceName2():" + memberVO.getLisceName2());
//		System.out.println("memberVO.getLisceName3():" + memberVO.getLisceName3());
		
//		findByName
//		MemberService memSvc = new MemberService();
//		String name = "";
//		MemberVO memberVO = memSvc.getOneByName(name);
//		memberVO = dao.findByName("GG");
//		System.out.println("memberVO.getMember_no():" + memberVO.getMemberNo());
//		System.out.println("memberVO.getAccount():" + memberVO.getAccount());
//		System.out.println("memberVO.getPassword():" + memberVO.getPassword());
//		System.out.println("memberVO.getName():" + memberVO.getName());
//		System.out.println("memberVO.getId_number():" + memberVO.getIdNumber());
//		System.out.println("memberVO.getBirth_date():" + memberVO.getBirthDate());
//		System.out.println("memberVO.getTelephone():" + memberVO.getPhone());
//		System.out.println("memberVO.getEmail():" + memberVO.getEmail());
//		System.out.println("memberVO.isMember_state():" + memberVO.getMemberState());
//		System.out.println("memberVO.getLisceName1():" + memberVO.getLisceName1());
//		System.out.println("memberVO.getLisceName2():" + memberVO.getLisceName2());
//		System.out.println("memberVO.getLisceName3():" + memberVO.getLisceName3());

		// 查詢
		MemberService memSvc = new MemberService();
		List<MemberVO> list = memSvc.getAll();
		for (MemberVO memberVO : list) {
			System.out.println("memberVO.getMemberNo():" + memberVO.getMemberNo());
			System.out.println("memberVO.getAccount():" + memberVO.getAccount());
			System.out.println("memberVO.getPassword():" + memberVO.getPassword());
			System.out.println("memberVO.getName():" + memberVO.getName());
			System.out.println("memberVO.getIdNumber():" + memberVO.getIdNumber());
			System.out.println("memberVO.getBirthDate():" + memberVO.getBirthDate());
			System.out.println("memberVO.getPhone():" + memberVO.getPhone());
			System.out.println("memberVO.getEmail():" + memberVO.getEmail());
			System.out.println("memberVO.getMemberState():" + memberVO.getMemberState());
			System.out.println("memberVO.getLisceName1():" + memberVO.getLisceName1());
			System.out.println("memberVO.getLisceName2():" + memberVO.getLisceName2());
			System.out.println("memberVO.getLisceName3():" + memberVO.getLisceName3());
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
