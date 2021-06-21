package com.exper_report.model;

import java.sql.Timestamp;
import java.util.*;

import com.exper_report.model.ExperReportVO;

public class ExperReportService {
    
	private ExperReportDAO_interface dao;
 
    public ExperReportService() {
    	dao = new ExperReportDAO();
    }
    
    public ExperReportVO addER (Integer reporter_no, Integer reported_exper_no, String reason) {
    	ExperReportVO erVO = new ExperReportVO();
    	erVO.setReporter_no(reporter_no);
    	erVO.setReported_exper_no(reported_exper_no);
    	erVO.setReason(reason);
    	dao.insert(erVO);
    	
    	return erVO;
    }
    
//    public ExperReportVO updateER (Integer report_no, String reply_content, Integer is_checked) {
//    	ExperReportVO erVO = new ExperReportVO();
//    	erVO.setReport_no(report_no);
//    	erVO.setReply_content(reply_content);
//    	erVO.setIs_checked(is_checked);
//    	dao.update(erVO);   	
//    	
//    	return erVO;
//    }
    
    public ExperReportVO getOneReportNo(Integer report_no) {
    	return dao.findByPrimaryKey(report_no);
    	
    }
    public List<ExperReportVO> getAll(){
    	return dao.getAll();
    }
    
    public void update(ExperReportVO erVO) {
		dao.update(erVO);
	}
    
}
