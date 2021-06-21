package com.exper_type.model;
import java.util.*;

import com.experience.model.ExperienceVO;

public interface ExperTypeDAO_interface {

	public void insert(ExperTypeVO extypeVO);
	public void update(ExperTypeVO extypeVO);
	public void delete(ExperTypeVO extypeVO);
	public ExperTypeVO findByPrimaryKey(Integer exper_type_no);
	public List<ExperTypeVO> getAll();
	public Set<ExperienceVO> getExperByExperType(Integer exper_type_no);
}
