package com.itineItem.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ItineItemService {
	private ItineItemDAO_interface dao;
	
	public ItineItemService() {
		dao=new ItineItemDAO();
//		dao=new ItineItemJDBCDAO();

	}
	
	public ItineItemVO addItineItem(Integer itineNo, Integer attraNo, Timestamp startTime, Timestamp endTime,Integer manager) {
		ItineItemVO itineItemVO = new ItineItemVO();
		itineItemVO.setItineNo(itineNo);
		itineItemVO.setAttraNo(attraNo);
		itineItemVO.setStartTime(startTime);
		itineItemVO.setEndTime(endTime);
		itineItemVO.setManager(manager);
		
		dao.add(itineItemVO);
		
		return itineItemVO;
	}
	
	public ItineItemVO updateItineItem(Timestamp startTime, Timestamp endTime, String note, Integer manager, Boolean isDone, Date finishDate, String taskNote, Integer itineNo, Integer attraNo) {
		ItineItemVO itineItemVO = new ItineItemVO();
		itineItemVO.setStartTime(startTime);
		itineItemVO.setEndTime(endTime);
		itineItemVO.setNote(note);
		itineItemVO.setManager(manager);
		itineItemVO.setIsDone(isDone);
		itineItemVO.setFinishDate(finishDate);
		itineItemVO.setTaskNote(taskNote);
		itineItemVO.setItineNo(itineNo);
		itineItemVO.setAttraNo(attraNo);
		dao.update(itineItemVO);
		return itineItemVO;
		
	}
	
	public ItineItemVO findByItineNoAndAttraNo(Integer itineNo, Integer attraNo) {
		return dao.findByItineNoAndAttraNo(itineNo, attraNo);
	}
	
	public List<ItineItemVO> findByItineNo(Integer itineNo){
		return dao.findByItineNo(itineNo);
	}
	
	public List<ItineItemVO> findByItineNoAndManager(Integer itineNo, Integer manager){
		return dao.findByItineNoAndManager(itineNo, manager);
	}
	
	public void deleteItineItem(Integer itineNo, Integer attraNo) {
		dao.delete(itineNo, attraNo);
	}
	
	public List<ItineItemVO> getAll(){
		return dao.getAll();
	}
}
