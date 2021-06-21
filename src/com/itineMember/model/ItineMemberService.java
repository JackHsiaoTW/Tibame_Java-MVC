package com.itineMember.model;

import java.util.List;

public class ItineMemberService {
	private ItineMemberDAO_interface dao;
	public ItineMemberService() {
		dao = new ItineMemberDAO();
//		dao = new ItineMemberJDBCDAO();
	}
	
	public ItineMemberVO addItineMember(Integer itineNo, Integer memberNo, Integer isEditable) {
		ItineMemberVO itineMemberVO = new ItineMemberVO();
		itineMemberVO.setItineNo(itineNo);
		itineMemberVO.setMemberNo(memberNo);
		itineMemberVO.setIsEditable(isEditable);
		dao.add(itineMemberVO);
		
		return itineMemberVO;
	}
	
	public ItineMemberVO updateItineMember(Integer itineNo, Integer memberNo, Integer isEditable) {
		ItineMemberVO itineMemberVO = new ItineMemberVO();
		itineMemberVO.setItineNo(itineNo);
		itineMemberVO.setMemberNo(memberNo);
		itineMemberVO.setIsEditable(isEditable);
		dao.update(itineMemberVO);
		
		return itineMemberVO;
	}
	
	public List<ItineMemberVO> findByItineNo(Integer itineNo){
		
		return dao.findByItineNo(itineNo);
	}
	
	public List<ItineMemberVO> findByMemberNo(Integer memberNo){
		return dao.findByMemberNo(memberNo);
	}
	public Integer thisMemberIsEditable(Integer itineNo, Integer memberNo) {
		Integer answer = null;
		List<ItineMemberVO> list = dao.findByItineNo(itineNo);
		for(ItineMemberVO aItineMemberVO : list) {
			if(aItineMemberVO.getMemberNo().equals(memberNo)) {
				answer=aItineMemberVO.getIsEditable();
			}
		}
		return answer;
	}
}
