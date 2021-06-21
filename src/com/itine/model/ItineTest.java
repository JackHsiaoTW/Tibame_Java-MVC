package com.itine.model;

import java.util.List;

public class ItineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ItineService itineSvc = new ItineService();
		
		//新增
		itineSvc.addItine("金門三日遊",3);
		System.out.println("新增成功");
		
		//修改
		itineSvc.updateItine("台中好玩五日遊", 2, "jason: 要去哪玩呢?", 3);
		System.out.println("修改成功");

		
		//查詢
		List<ItineVO> list = itineSvc.findByBuilder(3);
		for (ItineVO itineVO : list) {
			System.out.print(itineVO.getItineName() + " ");
			System.out.print(itineVO.getUpdateTime() + " ");
			System.out.print(itineVO.getItineStatus() + " ");
			System.out.print(itineVO.getItineBoard() + " ");
			System.out.println();
		}
	}

}
