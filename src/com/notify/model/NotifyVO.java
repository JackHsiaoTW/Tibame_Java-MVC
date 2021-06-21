package com.notify.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class NotifyVO implements Serializable {
	private Integer notifyNo;
	private Integer notifyPerson;
	private String notifyContent;
	private Timestamp notifyTime;

	public NotifyVO() {
	}

	public Integer getNotifyNo() {
		return notifyNo;
	}

	public void setNotifyNo(Integer notifyNo) {
		this.notifyNo = notifyNo;
	}

	public Integer getNotifyPerson() {
		return notifyPerson;
	}

	public void setNotifyPerson(Integer notifyPerson) {
		this.notifyPerson = notifyPerson;
	}

	public String getNotifyContent() {
		return notifyContent;
	}

	public void setNotifyContent(String notifyContent) {
		this.notifyContent = notifyContent;
	}

	public Timestamp getNotifyTime() {
		return notifyTime;
	}

	public void setNotifyTime(Timestamp notifyTime) {
		this.notifyTime = notifyTime;
	}
}
