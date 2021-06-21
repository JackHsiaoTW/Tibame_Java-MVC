package com.exper_application.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.exper_application.model.ExperApplicationVO;
import com.exper_order.model.ExperOrderService;




public class ExperApplicationService {
	private ExperApplicationDAO_interface dao;

	public ExperApplicationService() {
		dao = new ExperApplicationDAO();
	}

    	
	public List<ExperApplicationVO> getAll() {
		return dao.getAll();
	}
	
	public List<ExperApplicationVO> getExpAppliByMemberNo(Integer member_no) {
		return dao.getExpAppliByMemberNo(member_no);
	}
	
	public List<ExperApplicationVO> getExperAppliByExperOrderrNo(Integer exper_order_no) {
		return dao.getExperAppliByExperOrderrNo(exper_order_no);
	}
	
	public ExperApplicationVO getOneApplication(Integer exper_appli_no) {
		return dao.findByPrimaryKey(exper_appli_no);
	}
	public void changeExperApplitionStatus(ExperApplicationVO expapVO) {
		dao.update(expapVO);
	}
	
	public void insertExperApplication(ExperApplicationVO expapVO) {
		dao.insert(expapVO);
	}
	
	
	public ExperApplicationVO addExperAppli(Integer member_no, Integer exper_order_no, Integer number,
			Integer sum, Integer exper_appli_status, Integer exper_payment_status, String exper_appli_memo){
		
		ExperApplicationVO expapVO = new ExperApplicationVO();
		
		expapVO.setMember_no(member_no);
		expapVO.setExper_order_no(exper_order_no);
		expapVO.setNumber(number);
		expapVO.setSum(sum);
		expapVO.setExper_appli_status(exper_appli_status);
		expapVO.setExper_payment_status(exper_payment_status);
		expapVO.setExper_appli_memo(exper_appli_memo);

		dao.insert(expapVO);
		
		
		return expapVO;
	}
	


}


