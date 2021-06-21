package com.itine.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ItineVO implements Serializable{
	
	private Integer itineNo;
	private String itineName;
	private Integer builder;
	private Timestamp updateTime;
	private Integer itineStatus;
	private String itineBoard;
	public ItineVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItineVO(Integer itineNo, String itineName, Integer builder, Timestamp updateTime, Integer itineStatus, String itineBoard) {
		super();
		this.itineNo = itineNo;
		this.itineName = itineName;
		this.builder = builder;
		this.updateTime = updateTime;
		this.itineStatus = itineStatus;
		this.itineBoard = itineBoard;
	}
	public Integer getItineNo() {
		return itineNo;
	}
	public void setItineNo(Integer itineNo) {
		this.itineNo = itineNo;
	}
	public String getItineName() {
		return itineName;
	}
	public void setItineName(String itineName) {
		this.itineName = itineName;
	}
	public Integer getBuilder() {
		return builder;
	}
	public void setBuilder(Integer builder) {
		this.builder = builder;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getItineStatus() {
		return itineStatus;
	}
	public void setItineStatus(Integer itineStatus) {
		this.itineStatus = itineStatus;
	}
	public String getItineBoard() {
		return itineBoard;
	}
	public void setItineBoard(String itineBoard) {
		this.itineBoard = itineBoard;
	}
	
}
