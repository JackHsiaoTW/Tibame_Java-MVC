package com.exper_type.model;

import java.util.List;
import java.util.Set;

import com.experience.model.ExperienceVO;



public class ExperTypeService {
	private ExperTypeDAO_interface dao;

	public ExperTypeService() {
		dao = new ExperTypeDAO();
	}

	public List<ExperTypeVO> getAll() {
		return dao.getAll();
	}

	public ExperTypeVO getOneExperType(Integer exper_type_no) {
		return dao.findByPrimaryKey(exper_type_no);
	}
	public Set<ExperienceVO> getExperByExperType(Integer exper_type_no){
		return dao.getExperByExperType(exper_type_no);
	}
	public void update(ExperTypeVO extypeVO) {
		dao.update(extypeVO);
	}
	public void insert(String exper_type_name) {
		ExperTypeVO extypeVO = new ExperTypeVO();
		extypeVO.setExper_type_name(exper_type_name);
		dao.insert(extypeVO);
	}

}
