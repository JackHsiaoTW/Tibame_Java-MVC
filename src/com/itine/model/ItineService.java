package com.itine.model;

import java.util.List;

public class ItineService {
	
	private ItineDAO_interface dao;
	
	public ItineService () {
//		dao = new ItineJDBCDAO(); //線下測試用的
		dao= new ItineDAO(); //severlet要用這個 
	}
	
	public ItineVO addItine(String itineName, Integer builder) {
		ItineVO itineVO = new ItineVO();
		
		itineVO.setItineName(itineName);
		itineVO.setBuilder(builder);
		
		dao.add(itineVO);
		return itineVO;
	}
	
	public ItineVO updateItine(String itineName, Integer itineStatus, String itineBoard, Integer itineNo) {
		ItineVO itineVO = new ItineVO();
		itineVO.setItineName(itineName);
		itineVO.setItineStatus(itineStatus);
		itineVO.setItineBoard(itineBoard);
		itineVO.setItineNo(itineNo);
		dao.update(itineVO);
		return itineVO;
	}
	
	public List<ItineVO> findByBuilder(Integer builder){
		return dao.findByBuilder(builder);
	}
	
	public ItineVO findByItineNo(Integer itineNo) {
		return dao.findByPK(itineNo);
	}
	
}
