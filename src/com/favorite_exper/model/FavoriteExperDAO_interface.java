package com.favorite_exper.model;

import java.util.List;

import com.experience.model.ExperienceVO;

public interface FavoriteExperDAO_interface {
    public void insert(Integer member_no, Integer exper_no);
	public void insert(FavoriteExperVO favorexpVO);
    public void delete(Integer member_no, Integer exper_no);
    
    public List<Integer> findByExperNo(Integer exper_no);
    public List<Integer> findByMemberNo(Integer member_no); 
    public List<FavoriteExperVO> getAll();
    
    public int countFavor(Integer exper_no);
}
