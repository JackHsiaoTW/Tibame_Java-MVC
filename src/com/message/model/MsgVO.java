package com.message.model;

import java.sql.Timestamp;

public class MsgVO {
	private Integer msgNo;
	private Integer speackerNo;
	private Integer receiverNo;
	private String content;
	private Timestamp time;
	private Boolean direction;
	private Boolean isRead;

	public MsgVO() {
		super();
	}

	public Integer getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(Integer msgNo) {
		this.msgNo = msgNo;
	}

	public Integer getSpeackerNo() {
		return speackerNo;
	}

	public void setSpeackerNo(Integer speackerNo) {
		this.speackerNo = speackerNo;
	}

	public Integer getReceiverNo() {
		return receiverNo;
	}

	public void setReceiverNo(Integer receiverNo) {
		this.receiverNo = receiverNo;
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

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

}
