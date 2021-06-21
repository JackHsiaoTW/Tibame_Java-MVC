package com.serviceMsg.model;

import java.sql.Timestamp;

public class SvcMsgVO {
	private Integer svcMsgNo;
	private Integer memberNo;
	private Integer empNo;
	private String content;
	private Timestamp time;
	private Boolean direction;

	public SvcMsgVO() {
		super();
	}

	public Integer getSvcMsgNo() {
		return svcMsgNo;
	}

	public void setSvcMsgNo(Integer svcMsgNo) {
		this.svcMsgNo = svcMsgNo;
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getDirection() {
		return direction;
	}

	public void setDirection(Boolean direction) {
		this.direction = direction;
	}

}
