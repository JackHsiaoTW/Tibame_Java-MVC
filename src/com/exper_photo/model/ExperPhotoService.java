package com.exper_photo.model;

import java.util.List;
import java.util.Set;

import com.exper_photo.model.ExperPhotoVO;

public class ExperPhotoService {

	private ExperPhotoDAO_interface dao;

	public ExperPhotoService() {
		dao = new ExperPhotoDAO();
	}

	public void insert(ExperPhotoVO expphoVO) {
		dao.insert(expphoVO);
	}

	public void update(ExperPhotoVO expphoVO) {
		dao.update(expphoVO);
	}

	public void deleteExpPho(Integer exper_photo_no) {
		dao.delete(exper_photo_no);
	}

	public List<ExperPhotoVO> getAll() {

		return dao.getAll();
	}

	public ExperPhotoVO getOneExperPho(Integer exper_photo_no) {
		return dao.findByPrimaryKey(exper_photo_no);
	}

	public List<ExperPhotoVO> getPhotoByExperNo(Integer exper_no) {

		return dao.getPhotoByExperNo(exper_no);
	}

	public void upDatePhotoByExperNo(ExperPhotoVO expphoVO) {
		dao.update_photo_by_exper_photo_no(expphoVO);
	}

	public ExperPhotoVO addExperPhoto(Integer exper_no, byte[] photo) {
		ExperPhotoVO expphoVO = new ExperPhotoVO();

		expphoVO.setExper_no(exper_no);
		expphoVO.setPhoto(photo);
		dao.insert(expphoVO);

		return expphoVO;
	}

	

}
