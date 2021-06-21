package com.itine.model;

import java.util.List;


public interface ItineDAO_interface {
	void add(ItineVO itine);
	void update(ItineVO itine);
	ItineVO findByPK(Integer itineNo);
	List<ItineVO> findByBuilder(Integer builder);
	
}
