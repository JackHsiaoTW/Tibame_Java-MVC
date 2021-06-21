package com.exper_photo.model;

public class ExperPhotoVO implements java.io.Serializable{
	private Integer exper_photo_no;
	private Integer exper_no;
	private byte[] photo;

	public Integer getExper_photo_no() {
		return exper_photo_no;
	}
	public void setExper_photo_no(Integer exper_photo_no) {
		this.exper_photo_no = exper_photo_no;
	}
	public Integer getExper_no() {
		return exper_no;
	}
	public void setExper_no(Integer exper_no) {
		this.exper_no = exper_no;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
}
