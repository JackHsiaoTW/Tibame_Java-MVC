package com.notify.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TestNotify {
	public static void main(String[] args) {
		NotifyService notifySvc = new NotifyService();
		
		//insert
		notifySvc.addNotify(2, "notifyContent", new Timestamp(System.currentTimeMillis()));
		
		//getOneNotifyByPerson
//		List<NotifyVO> list = new ArrayList<NotifyVO>();
//		list = notifySvc.getOneNotifyByPerson(1);
//		for (NotifyVO notifyVO : list) {
//			System.out.println("NotifNo:"+notifyVO.getNotifNo());
//			System.out.println("NotifPerson:"+notifyVO.getNotifPerson());
//			System.out.println("NotifContent:"+notifyVO.getNotifContent());
//			System.out.println("NotifTime:"+notifyVO.getNotifTime());
//			System.out.println("---------");
//		}
		
		//getAll
//		List<NotifyVO> list = new ArrayList<NotifyVO>();
//		list = notifySvc.getAll();
//		
//		for (NotifyVO notifyVO : list) {
//			System.out.println("NotifNo:"+notifyVO.getNotifNo());
//			System.out.println("NotifPerson:"+notifyVO.getNotifPerson());
//			System.out.println("NotifContent:"+notifyVO.getNotifContent());
//			System.out.println("NotifTime:"+notifyVO.getNotifTime());
//			System.out.println("---------");
//		}
	}
}
