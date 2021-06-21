
package com.exper_order.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.exper_order.model.ExperOrderService;
import com.exper_order.model.ExperOrderVO;
import com.exper_order.model.ExperOrderVO;



public class ExperOrderService {
	private ExperOrderDAO_interface dao;

	public ExperOrderService() {
		dao = new ExperOrderDAO();
	}

//	public List<ExperOrderVO> getAll() {
//		return dao.getAll().stream()
//				.filter(e -> e.getExper_order_status()==1)
//				.collect(Collectors.toList());
//	}
	
	public List<ExperOrderVO> getAll() {
		return dao.getAll();
	}
	public List<ExperOrderVO> getAllExperPerByExperNo(Integer exper_no) {
		return dao.getAllExperPerByExperNo(exper_no);
	}

	public ExperOrderVO getOneExperOrder(Integer exper_order_no) {
		return dao.findByPrimaryKey(exper_order_no);
	}
	public List<ExperOrderVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	public void insert(Integer exper_no, Timestamp apply_start, Timestamp apply_end,
			Timestamp exper_order_start, Timestamp exper_order_end, Integer exper_max_limit, Integer exper_min_limit,
			Integer exper_now_price, Integer exper_order_status, Integer exper_apply_sum) {
		ExperOrderVO expordVO = new ExperOrderVO();
		expordVO.setExper_no(exper_no);
		expordVO.setApply_start(apply_start);
		expordVO.setApply_end(apply_end);
		expordVO.setExper_order_start(exper_order_start);
		expordVO.setExper_order_end(exper_order_end);
		expordVO.setExper_max_limit(exper_max_limit);
		expordVO.setExper_min_limit(exper_min_limit);
		expordVO.setExper_now_price(exper_now_price);
		expordVO.setExper_order_status(exper_order_status);
		expordVO.setExper_apply_sum(exper_apply_sum);
		dao.insert(expordVO);
	}

	
	public void upDateExperPerStatus(Integer exper_order_no, Integer exper_order_status) {
		dao.upDateExperPerStatus(exper_order_no, exper_order_status);
		
	}
	
	public void upDateExpOrdSignSum(Integer exper_order_no,Integer exper_apply_sum) {
		ExperOrderService expordSvc = new ExperOrderService();
		ExperOrderVO expordVO = expordSvc.getOneExperOrder(exper_order_no);
		expordVO.setExper_apply_sum(exper_apply_sum);
		dao.update(expordVO);
	}
	
	public List<ExperOrderVO> getListByExperOrderStart(String exper_order_status_on_date){
		return dao.getListByExperOrderStart(exper_order_status_on_date);
	}
	

}
