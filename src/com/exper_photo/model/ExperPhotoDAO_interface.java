package com.exper_photo.model;

import java.util.List;



public interface ExperPhotoDAO_interface {
	public void insert(ExperPhotoVO epVO);
    public void update(ExperPhotoVO epVO);
    public void delete(Integer exper_photo_no);
    public void update_photo_by_exper_photo_no(ExperPhotoVO expphoVO);
    public ExperPhotoVO findByPrimaryKey(Integer exper_photo_no);
    public List<ExperPhotoVO> getAll();
    public List<ExperPhotoVO> getPhotoByExperNo(Integer exper_no);
}
