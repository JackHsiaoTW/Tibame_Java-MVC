package com.favorite_exper.model;

import java.util.List;

import com.experience.model.ExperienceVO;
import com.favorite_exper.model.FavoriteExperVO;

public class FavoriteExperService {

	private FavoriteExperDAO_interface dao;

	public FavoriteExperService() {
		dao = new FavoriteExperDAO();
	}

//	public FavoriteExperVO addFavorite(Integer exper_no, Integer member_no) {
//
//		FavoriteExperVO favorexpVO = new FavoriteExperVO();
//		favorexpVO.setExper_no(exper_no);
//		favorexpVO.setMember_no(member_no);
//		dao.insert(favorexpVO);
//		return favorexpVO;
//	}

	public void insert(FavoriteExperVO favorexpVO) {
		 dao.insert(favorexpVO);
	 }
//	public FavoriteExperVO updateFavorite(Integer exper_no, Integer member_no) {
//
//		FavoriteExperVO favorexpVO = new FavoriteExperVO();
//		favorexpVO.setExper_no(exper_no);
//		favorexpVO.setMember_no(member_no);
//		dao.update(favorexpVO);
//		return favorexpVO;
//	}

//	public void deleteFavorite(Integer exper_no) {
//		dao.delete(exper_no);
//	}

	public List<Integer> getOneByMemberNo(Integer member_no){
		return dao.findByMemberNo(member_no);
	}
	public List<Integer> getOneByExperNo(Integer exper_no){
		return dao.findByExperNo(exper_no);
	}
	public List<FavoriteExperVO> getAll() {
		return dao.getAll();
	}
	public int getCountFavor(Integer exper_no) {
		return dao.countFavor(exper_no);
	}
	public boolean getFavor(Integer member_no, Integer exper_no){
		return dao.findByMemberNo(member_no).contains(exper_no);
	}
	
	public int addFavor(Integer member_no, Integer exper_no) {
		dao.insert(member_no,exper_no);
		return dao.countFavor(exper_no);
	}
	
	public int deleteFavor(Integer member_no, Integer exper_no) {
		dao.delete(member_no,exper_no);
		return dao.countFavor(exper_no);
	}
	
}
