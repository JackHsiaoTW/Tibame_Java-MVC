package com.serviceMsg.model;

import java.util.List;

public interface SvcMsgDAO_inteface {
	public void insert(SvcMsgVO svcMsgVO);

//	public void update(SvcMsgVO svcMsgVO);
//	public void delete(Integer svcMsgNo);
	public SvcMsgVO findByPrimaryKey(Integer svcMsgNo);

	public List<SvcMsgVO> findByMemberNo(Integer memberNo);

	public List<SvcMsgVO> getAll();
}
