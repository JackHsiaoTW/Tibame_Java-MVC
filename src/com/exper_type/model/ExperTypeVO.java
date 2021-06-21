package com.exper_type.model;

public class ExperTypeVO implements java.io.Serializable {

	private Integer exper_type_no;
	private String exper_type_name;
	
	public ExperTypeVO() {
		super();
	}
	public ExperTypeVO(Integer exper_type_no, String exper_type_name) {
		super();
		this.exper_type_no = exper_type_no;
		this.exper_type_name = exper_type_name;
	}
	public Integer getExper_type_no() {
		return exper_type_no;
	}
	public void setExper_type_no(Integer exper_type_no) {
		this.exper_type_no = exper_type_no;
	}
	public String getExper_type_name() {
		return exper_type_name;
	}
	public void setExper_type_name(String exper_type_name) {
		this.exper_type_name = exper_type_name;
	}

}
