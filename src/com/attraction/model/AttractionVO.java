package com.attraction.model;

import java.io.Serializable;

public class AttractionVO implements Serializable{
	private int attraNo;
	private String sort;
	private String attraName;
	private String descr;
	private String location;
	private int isOnShelf;
	private String attraPic1;
	
	
	public AttractionVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AttractionVO(int attraNo, String sort, String attraName, String descr, String location, int isOnShelf,
			String attraPic1) {
		super();
		this.attraNo = attraNo;
		this.sort = sort;
		this.attraName = attraName;
		this.descr = descr;
		this.location = location;
		this.isOnShelf = isOnShelf;
		this.attraPic1 = attraPic1;
	}


	public int getAttraNo() {
		return attraNo;
	}


	public void setAttraNo(int attraNo) {
		this.attraNo = attraNo;
	}


	public String getSort() {
		return sort;
	}


	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getAttraName() {
		return attraName;
	}


	public void setAttraName(String attraName) {
		this.attraName = attraName;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public int getIsOnShelf() {
		return isOnShelf;
	}


	public void setIsOnShelf(int isOnShelf) {
		this.isOnShelf = isOnShelf;
	}


	public String getAttraPic1() {
		return attraPic1;
	}


	public void setAttraPic1(String attraPic1) {
		this.attraPic1 = attraPic1;
	}
	
	
	
	
}
