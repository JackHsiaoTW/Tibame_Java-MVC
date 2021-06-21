package com.attraction.model;

import java.util.ArrayList;
import java.util.List;

public class AttractionService {

	private AttractionDAO_interface dao;
	
	public AttractionService() {
		dao = new AttractionDAO();
//		dao = new AttractionJDBCDAO();
	}
	
	public int getNewestId() {
		return dao.getNewestId();
	}
	public List<AttractionVO> getAll(){
		return dao.getAll();
	}
	public List<AttractionVO> getUnchecked(){
		return dao.getUnchecked();
	}
	public String addAttra(String sort, String attraName, String descr, String location, int isOnShelf,
			String attraPic1) {
		AttractionVO attractionVO = new AttractionVO();
		
		attractionVO.setSort(sort);
		attractionVO.setAttraName(attraName);
		attractionVO.setDescr(descr);
		attractionVO.setLocation(location);
		attractionVO.setIsOnShelf(isOnShelf);
		attractionVO.setAttraPic1(attraPic1);
		String attraNo = dao.add(attractionVO);
		
		return attraNo;
	}
	public AttractionVO getOne(int attraNo) {
		return dao.findByPK(attraNo);
	}
	
	public AttractionVO updateAttra(String sort, String attraName, String descr, String location, int isOnShelf,
			String attraPic1, int attraNo) {
		AttractionVO attractionVO = new AttractionVO();
		
		attractionVO.setSort(sort);
		attractionVO.setAttraName(attraName);
		attractionVO.setDescr(descr);
		attractionVO.setLocation(location);
		attractionVO.setIsOnShelf(isOnShelf);
		attractionVO.setAttraPic1(attraPic1);
		attractionVO.setAttraNo(attraNo);
		dao.update(attractionVO);
		
		return attractionVO;
	}
	
	public List<AttractionVO> getSearchFor(String searchfor, String sort) {
		
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		list = dao.getSearchFor(searchfor, sort);
		
		return list;
	}
	
	public List<AttractionVO> getSearchForIsOnShelf(String searchfor, String sort) {
		
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		list = dao.getSearchForIsOnShelf(searchfor, sort);
		
		return list;
	}
	
	public boolean isTrafficTool(int attraNo) {
		boolean answer = false;
		AttractionVO attraVO = getOne(attraNo);
		if("交通".equals(attraVO.getSort())) {
			answer =true;
		}
		
		return answer;
	} 
	
	
}
