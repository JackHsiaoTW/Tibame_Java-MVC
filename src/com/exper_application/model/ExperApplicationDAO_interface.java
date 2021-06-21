package com.exper_application.model;

import java.util.List;


import com.exper_application.model.ExperApplicationVO;


public interface ExperApplicationDAO_interface {
	public void insert(ExperApplicationVO expappliVO);
    public void update(ExperApplicationVO expappliVO);
    public ExperApplicationVO findByPrimaryKey(Integer exper_appli_no);
    public List<ExperApplicationVO> getAll();
    public List<ExperApplicationVO> getExpAppliByMemberNo(Integer member_no);
    public List<ExperApplicationVO> getExperAppliByExperOrderrNo(Integer exper_order_no);
}
