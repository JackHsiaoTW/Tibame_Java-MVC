package com.member.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test_M")
public class Test_M extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test_M() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDAO_interface dao = new MemberDAO();

//		MemberVO memberVO = new MemberVO();
//		memberVO.setAccount("DDD");
//		memberVO.setPassword("12345");
//		memberVO.setName("DDD");
//		memberVO.setId_number("A123456789");
//		memberVO.setBirth_date(Date.valueOf("2021-01-25"));
//		memberVO.setTelephone("09123456789");
//		memberVO.setEmail("XXX@YYY.ZZZ");
//		memberVO.setMember_state(true);
//		dao.add(memberVO);

//		MemberVO memberVO = new MemberVO();
//		memberVO = dao.findByPK(2);
//		System.out.println("memberVO.getMember_no():" + memberVO.getMember_no());
//		System.out.println("memberVO.getAccount():" + memberVO.getAccount());
//		System.out.println("memberVO.getPassword():" + memberVO.getPassword());
//		System.out.println("memberVO.getName():" + memberVO.getName());
//		System.out.println("memberVO.getId_number():" + memberVO.getId_number());
//		System.out.println("memberVO.getBirth_date():" + memberVO.getBirth_date());
//		System.out.println("memberVO.getTelephone():" + memberVO.getTelephone());
//		System.out.println("memberVO.getEmail():" + memberVO.getEmail());
//		System.out.println("memberVO.isMember_state():" + memberVO.isMember_state());

//		MemberVO memberVO = new MemberVO();
//		memberVO = dao.findByAccount("GG");
//		System.out.println("memberVO.getMember_no():" + memberVO.getMember_no());
//		System.out.println("memberVO.getAccount():" + memberVO.getAccount());
//		System.out.println("memberVO.getPassword():" + memberVO.getPassword());
//		System.out.println("memberVO.getName():" + memberVO.getName());
//		System.out.println("memberVO.getId_number():" + memberVO.getId_number());
//		System.out.println("memberVO.getBirth_date():" + memberVO.getBirth_date());
//		System.out.println("memberVO.getTelephone():" + memberVO.getTelephone());
//		System.out.println("memberVO.getEmail():" + memberVO.getEmail());
//		System.out.println("memberVO.isMember_state():" + memberVO.isMember_state());

		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO memberVO : list) {
			System.out.println("memberVO.getMemberNo():" + memberVO.getMemberNo());
			System.out.println("memberVO.getAccount():" + memberVO.getAccount());
			System.out.println("memberVO.getPassword():" + memberVO.getPassword());
			System.out.println("memberVO.getName():" + memberVO.getName());
			System.out.println("memberVO.getId_number():" + memberVO.getIdNumber());
			System.out.println("memberVO.getBirth_date():" + memberVO.getBirthDate());
			System.out.println("memberVO.getTelephone():" + memberVO.getPhone());
			System.out.println("memberVO.getEmail():" + memberVO.getEmail());
			System.out.println("memberVO.isMember_state():" + memberVO.getMemberState());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
