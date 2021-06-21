package com.serviceMsg.model;

import java.sql.Timestamp;
import java.util.List;

public class SvcMsgService {
	private SvcMsgDAO_inteface dao;

	public SvcMsgService() {
//		dao = new SvcMsgJDBCDAO();
		dao = new SvcMsgDAO();
	}

	public SvcMsgVO addSvcMsg(Integer memberNo, Integer empNo, String content, Timestamp time, Boolean direction) {
		SvcMsgVO svcMsgVO = new SvcMsgVO();
		svcMsgVO.setMemberNo(memberNo);
		svcMsgVO.setEmpNo(empNo);
		svcMsgVO.setContent(content);
		svcMsgVO.setTime(time);
		svcMsgVO.setDirection(direction);

		dao.insert(svcMsgVO);

		return svcMsgVO;
	}

	public SvcMsgVO getOneSvcMsg(Integer svcMsgNo) {
		return dao.findByPrimaryKey(svcMsgNo);
	}

	public List<SvcMsgVO> getSvcMsgByMemberNo(Integer memberNo) {
		return dao.findByMemberNo(memberNo);
	}

	public List<SvcMsgVO> getAll() {
		return dao.getAll();
	}

}
