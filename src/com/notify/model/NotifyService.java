package com.notify.model;

import java.sql.Timestamp;
import java.util.List;

public class NotifyService {
	private Notify_interface dao = null;

	public NotifyService() {
//		dao = new NotifyJDBCDAO();
		dao = new NotifyDAO();
	}

	public NotifyVO addNotify(Integer notifyPerson, String notifyContent, Timestamp notifyTime) {
		NotifyVO notifyVO = new NotifyVO();
		notifyVO.setNotifyPerson(notifyPerson);
		notifyVO.setNotifyContent(notifyContent);
		notifyVO.setNotifyTime(notifyTime);

		dao.insert(notifyVO);
		return notifyVO;
	}

	public NotifyVO getOneNotify(Integer notifNo) {
		return dao.findByPrimaryKey(notifNo);
	}

	public List<NotifyVO> getOneNotifyByPerson(Integer notifyPerson) {
		return dao.findByNotifyPerson(notifyPerson);
	}

	public List<NotifyVO> getAll() {
		return dao.getAll();
	}

}
