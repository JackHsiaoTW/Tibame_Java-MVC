package com.serviceMsg.model;

import java.util.List;

public class TestSvcMsg {
	public static void main(String[] args) {
		SvcMsgService svcMsgService = new SvcMsgService();
		
		//insert
//		String dateTimeStr2 = "2018-11-29 20:25:58";
//		java.sql.Timestamp ts = java.sql.Timestamp.valueOf(dateTimeStr2);
//		svcMsgService.add(1, 5, "哈囉～～～～！", ts, false);
		
		//findByPrimaryKey
//		SvcMsgVO svcMsgVO = svcMsgService.getOneSvcMsg(12);
//		System.out.println("SvcMsgNo:"+svcMsgVO.getSvcMsgNo());
//		System.out.println("MemberNo:"+svcMsgVO.getMemberNo());
//		System.out.println("EmpNo:"+svcMsgVO.getEmpNo());
//		System.out.println("Content:"+svcMsgVO.getContent());
//		System.out.println("Time:"+svcMsgVO.getTime());
//		System.out.println("Direction:"+svcMsgVO.getDirection());
		
		//findByMemberNo
		List<SvcMsgVO> list = svcMsgService.getSvcMsgByMemberNo(1);
		for (SvcMsgVO svcMsgVO : list) {
			System.out.println("SvcMsgNo:" + svcMsgVO.getSvcMsgNo());
			System.out.println("MemberNo:" + svcMsgVO.getMemberNo());
			System.out.println("EmpNo:" + svcMsgVO.getEmpNo());
			System.out.println("Content:" + svcMsgVO.getContent());
			System.out.println("Time:" + svcMsgVO.getTime());
			System.out.println("Direction:" + svcMsgVO.getDirection());
			System.out.println("-------------------");
		}
		
		
		//getAll
//		List<SvcMsgVO> list = svcMsgService.getAll();
//		for (SvcMsgVO svcMsgVO : list) {
//			System.out.println("SvcMsgNo:"+svcMsgVO.getSvcMsgNo());
//			System.out.println("MemberNo:"+svcMsgVO.getMemberNo());
//			System.out.println("EmpNo:"+svcMsgVO.getEmpNo());
//			System.out.println("Content:"+svcMsgVO.getContent());
//			System.out.println("Time:"+svcMsgVO.getTime());
//			System.out.println("Direction:"+svcMsgVO.getDirection());
//			System.out.println("-------------------");
//		}
	}
}
