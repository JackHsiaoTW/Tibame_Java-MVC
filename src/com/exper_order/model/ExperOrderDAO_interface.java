package com.exper_order.model;

import java.util.List;
import java.util.Map;
import java.util.Set;



public interface ExperOrderDAO_interface {
	public void insert(ExperOrderVO expordVO);
    public void update(ExperOrderVO expordVO);
    public void upDateExperPerStatus(Integer exper_order_no, Integer exper_order_status);
    public ExperOrderVO findByPrimaryKey(Integer exper_order_no);
    public void delete(Integer exper_order_no);
    public List<ExperOrderVO> getAll();
    public List<ExperOrderVO> getAll(Map<String, String[]> map);
    public List<ExperOrderVO> getAllExperPerByExperNo(Integer exper_no);
    public List<ExperOrderVO> getListByExperOrderStart(String exper_order_status_on_date);

}
