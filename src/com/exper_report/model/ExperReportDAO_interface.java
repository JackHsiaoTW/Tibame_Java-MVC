package com.exper_report.model;

import java.util.List;
import java.util.Set;

public interface ExperReportDAO_interface {
	public void insert(ExperReportVO erVO);
	public void update(ExperReportVO erVO);
	public ExperReportVO findByPrimaryKey(Integer report_no);
	public List<ExperReportVO> getAll();	
	public Set<ExperReportVO> findByExperNo(Integer reported_exper_no);
	
}
