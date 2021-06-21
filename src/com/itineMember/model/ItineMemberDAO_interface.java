package com.itineMember.model;

import java.util.List;

public interface ItineMemberDAO_interface {
	void add(ItineMemberVO itineMember);
	void update(ItineMemberVO itineMember);
	List<ItineMemberVO> findByItineNo(Integer itineNo);
	List<ItineMemberVO> findByMemberNo(Integer memberNo);
}
