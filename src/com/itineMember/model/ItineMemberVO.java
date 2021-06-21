package com.itineMember.model;

public class ItineMemberVO {
	private Integer itineNo;
	private Integer memberNo;
	private Integer isEditable;
	public ItineMemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItineMemberVO(Integer itineNo, Integer memberNo, Integer isEditable) {
		super();
		this.itineNo = itineNo;
		this.memberNo = memberNo;
		this.isEditable = isEditable;
	}
	public Integer getItineNo() {
		return itineNo;
	}
	public void setItineNo(Integer itineNo) {
		this.itineNo = itineNo;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getIsEditable() {
		return isEditable;
	}
	public void setIsEditable(Integer isEditable) {
		this.isEditable = isEditable;
	}
	
	
}
