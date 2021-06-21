package com.itineMember.model;

import java.util.List;

public class ItineMemberTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ItineMemberService itineMemberSvc = new ItineMemberService();
		
		//新增
		itineMemberSvc.addItineMember(3, 5, 0);
		System.out.println("新增成功");
		//修改
		itineMemberSvc.updateItineMember(3, 5, 1);
		System.out.println("修改成功");
		
		// 用行程查團員有誰
		List<ItineMemberVO> list = itineMemberSvc.findByItineNo(1);
		for (ItineMemberVO itineMemberVO : list) {
			System.out.print(itineMemberVO.getMemberNo() + " ");
			System.out.print(itineMemberVO.getIsEditable() + " ");
			System.out.println();
		}
		System.out.println("=============");

		// 用會員查參加哪些行程
		List<ItineMemberVO> list1 = itineMemberSvc.findByMemberNo(1);
		for (ItineMemberVO itineMemberVO : list1) {
			System.out.print(itineMemberVO.getItineNo() + " ");
			System.out.print(itineMemberVO.getIsEditable() + " ");
			System.out.println();
		}
		System.out.println("=============");
	}

}
