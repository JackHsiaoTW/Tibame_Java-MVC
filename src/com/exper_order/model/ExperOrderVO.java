package com.exper_order.model;

import java.sql.Timestamp;

public class ExperOrderVO implements java.io.Serializable {
	private Integer exper_order_no;
	private Integer exper_no;
	private Timestamp apply_start;
	private Timestamp apply_end;
	private Timestamp exper_order_start;
	private Timestamp exper_order_end;
	private Integer exper_max_limit;
	private Integer exper_min_limit;
	private Integer exper_now_price;
	private Integer exper_order_status;
	private Integer exper_apply_sum;
	
	public Integer getExper_order_no() {
		return exper_order_no;
	}
	public void setExper_order_no(Integer exper_order_no) {
		this.exper_order_no = exper_order_no;
	}
	public Integer getExper_no() {
		return exper_no;
	}
	public void setExper_no(Integer exper_no) {
		this.exper_no = exper_no;
	}
	public Timestamp getApply_start() {
		return apply_start;
	}
	public void setApply_start(Timestamp apply_start) {
		this.apply_start = apply_start;
	}
	public Timestamp getApply_end() {
		return apply_end;
	}
	public void setApply_end(Timestamp apply_end) {
		this.apply_end = apply_end;
	}
	public Timestamp getExper_order_start() {
		return exper_order_start;
	}
	public void setExper_order_start(Timestamp exper_order_start) {
		this.exper_order_start = exper_order_start;
	}
	public Timestamp getExper_order_end() {
		return exper_order_end;
	}
	public void setExper_order_end(Timestamp exper_order_end) {
		this.exper_order_end = exper_order_end;
	}
	public Integer getExper_max_limit() {
		return exper_max_limit;
	}
	public void setExper_max_limit(Integer exper_max_limit) {
		this.exper_max_limit = exper_max_limit;
	}
	public Integer getExper_min_limit() {
		return exper_min_limit;
	}
	public void setExper_min_limit(Integer exper_min_limit) {
		this.exper_min_limit = exper_min_limit;
	}
	public Integer getExper_now_price() {
		return exper_now_price;
	}
	public void setExper_now_price(Integer exper_now_price) {
		this.exper_now_price = exper_now_price;
	}
	public Integer getExper_order_status() {
		return exper_order_status;
	}
	public void setExper_order_status(Integer exper_order_status) {
		this.exper_order_status = exper_order_status;
	}
	public Integer getExper_apply_sum() {
		return exper_apply_sum;
	}
	public void setExper_apply_sum(Integer exper_apply_sum) {
		this.exper_apply_sum = exper_apply_sum;
	}
	
	
}
