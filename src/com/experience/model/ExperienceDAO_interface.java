package com.experience.model;

import java.util.List;

import com.experience.model.ExperienceVO;


public interface ExperienceDAO_interface {
	public void insert(ExperienceVO experVO);
    public void update(ExperienceVO experVO);
    public void delete(Integer exper_no);
    public ExperienceVO findByPrimaryKey(Integer exper_no);
    public List<ExperienceVO> getAll();
  public List<ExperienceVO> getAllbyHostNo(Integer host_no);
}
