package com.attraction.model;

import java.util.List;


public interface AttractionDAO_interface {
	String add(AttractionVO attractionVO);
	void update(AttractionVO attractionVO);
	AttractionVO findByPK(int attraNo);
	List<AttractionVO> findByName(String attraName);
	List<AttractionVO> getSearchFor(String searchfor, String sort);
	List<AttractionVO> getSearchForIsOnShelf(String searchfor, String sort);
	List<AttractionVO> getUnchecked();
	List<AttractionVO> getAll();
	int getNewestId();
}
