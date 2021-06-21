package com.exper_report.model;

import java.sql.Timestamp;

public class ExperReportVO implements java.io.Serializable {
	
	private Integer report_no;
	private Integer reporter_no;
	private Integer reported_exper_no;
	private String reason;
	private Timestamp report_time;
	private String reply_content;
	private Timestamp reply_time;
	private Integer is_checked;
	
	public Integer getReport_no() {
		return report_no;
	}
	public void setReport_no(Integer report_no) {
		this.report_no = report_no;
	}
	public Integer getReporter_no() {
		return reporter_no;
	}
	public void setReporter_no(Integer reporter_no) {
		this.reporter_no = reporter_no;
	}
	public Integer getReported_exper_no() {
		return reported_exper_no;
	}
	public void setReported_exper_no(Integer reported_exper_no) {
		this.reported_exper_no = reported_exper_no;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Timestamp getReport_time() {
		return report_time;
	}
	public void setReport_time(Timestamp report_time) {
		this.report_time = report_time;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Timestamp getReply_time() {
		return reply_time;
	}
	public void setReply_time(Timestamp reply_time) {
		this.reply_time = reply_time;
	}
	public Integer getIs_checked() {
		return is_checked;
	}
	public void setIs_checked(Integer is_checked) {
		this.is_checked = is_checked;
	}
	
	
		
	}
	