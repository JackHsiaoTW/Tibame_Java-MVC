package com.itineItem.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ItineItemVO implements Serializable{
	private Integer itineNo;
	private Integer attraNo;
	private Timestamp startTime;
	private Timestamp endTime;
	private String note;
	private Integer manager;
	private boolean isDone;
	private Date finishDate;
	private String taskNote;
	public ItineItemVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItineItemVO(Integer itineNo, Integer attraNo, Timestamp startTime, Timestamp endTime, String note, Integer manager,
			boolean isDone, Date finishDate, String taskNote) {
		super();
		this.itineNo = itineNo;
		this.attraNo = attraNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.note = note;
		this.manager = manager;
		this.isDone = isDone;
		this.finishDate = finishDate;
		this.taskNote = taskNote;
	}
	public Integer getItineNo() {
		return itineNo;
	}
	public void setItineNo(Integer itineNo) {
		this.itineNo = itineNo;
	}
	public Integer getAttraNo() {
		return attraNo;
	}
	public void setAttraNo(Integer attraNo) {
		this.attraNo = attraNo;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public boolean getIsDone() {
		return isDone;
	}
	public void setIsDone(boolean isDone) {
		this.isDone = isDone;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getTaskNote() {
		return taskNote;
	}
	public void setTaskNote(String taskNote) {
		this.taskNote = taskNote;
	}
	
	
}
