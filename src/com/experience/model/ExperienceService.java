package com.experience.model;

import java.util.List;


import com.experience.model.ExperienceVO;


public class ExperienceService {
	private ExperienceDAO_interface dao;

	public ExperienceService() {
		dao = new ExperienceDAO();
	}
	
//	public ExperienceVO addExper(Integer host_no, Integer checker_no, String name, Integer price,
//			String exper_descr, Integer exper_status, Integer exper_type_no) {
//
//		ExperienceVO experVO = new ExperienceVO();
//		
//		experVO.setChecker_no(checker_no);
//		experVO.setName(name);
//		experVO.setPrice(price);
//		experVO.setExper_descr(exper_descr);
//		experVO.setExper_status(exper_status);
//		experVO.setExper_type_no(exper_type_no);
//
//		dao.insert(experVO);
//
//		return experVO;
//	}
	
//	public ExperienceVO updateExper(Integer exper_no, Integer host_no, Integer checker_no, String name, Integer price,
//			String exper_descr, Integer exper_status, Integer exper_type_no) {
//
//		ExperienceVO experVO = new ExperienceVO();
//		
//		experVO.setExper_no(exper_no);
//		experVO.setChecker_no(checker_no);
//		experVO.setName(name);
//		experVO.setPrice(price);
//		experVO.setExper_descr(exper_descr);
//		experVO.setExper_status(exper_status);
//		experVO.setExper_type_no(exper_type_no);
//		
//		dao.update(experVO);
//
//		return experVO;
//	}
	
	public void update(ExperienceVO experVO) {
		 dao.update(experVO);
	 }
	
	public void deleteExper(Integer exper_no) {
		dao.delete(exper_no);
	}
		
	public ExperienceVO getOneExperience(Integer exper_no) {
		return dao.findByPrimaryKey(exper_no);
	}
	
	public List<ExperienceVO> getAll() {
		return dao.getAll();
	}



	public List<ExperienceVO> getAllbyHostNo(Integer host_no) {
		return dao.getAllbyHostNo(host_no);
	}
	
	
	public void insert(ExperienceVO experVO) {
		 dao.insert(experVO);
	 }

}
