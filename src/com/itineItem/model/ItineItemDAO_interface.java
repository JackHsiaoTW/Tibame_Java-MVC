package com.itineItem.model;

import java.util.List;


public interface ItineItemDAO_interface {
	void add(ItineItemVO itineItemVO);
	void update(ItineItemVO itineItem);
	void delete(Integer itineNo, Integer attraNo);
	ItineItemVO findByItineNoAndAttraNo(Integer itineNo, Integer attraNo);
	List<ItineItemVO> findByItineNo(Integer itineNo);
	List<ItineItemVO> findByItineNoAndManager(Integer itineNo, Integer manager);
	List<ItineItemVO> getAll();
}
