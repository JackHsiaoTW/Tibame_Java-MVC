package com.notify.model;

import java.util.List;

public interface Notify_interface {
	public void insert(NotifyVO notifyVO);

//    public void update(NotifyVO notifyVO);
//    public void delete(Integer notifNo);
	public NotifyVO findByPrimaryKey(Integer notifNo);

	public List<NotifyVO> findByNotifyPerson(Integer notifyPerson);

	public List<NotifyVO> getAll();

	public void insert2(NotifyVO notifyVO, java.sql.Connection con);
}
