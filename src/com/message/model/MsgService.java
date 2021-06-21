package com.message.model;

import java.sql.Timestamp;
import java.util.List;

public class MsgService {
	private Msg_interface dao;

	public MsgService() {
//		dao = new MsgJDBCDAO();
		dao = new MsgDAO();
	}

	public MsgVO addMsg(Integer speackerNo, Integer receiverNo, String content, Timestamp time, Boolean direction,
			Boolean isRead) {
		MsgVO msgVO = new MsgVO();
		msgVO.setSpeackerNo(speackerNo);
		msgVO.setReceiverNo(receiverNo);
		msgVO.setContent(content);
		msgVO.setTime(time);
		msgVO.setDirection(direction);
		msgVO.setIsRead(isRead);
		dao.insert(msgVO);

		return msgVO;
	}

	public MsgVO updateMsg(Integer speackerNo, Integer receiverNo, String content, Timestamp time, Boolean direction,
			Boolean isRead, Integer msgNo) {
		MsgVO msgVO = new MsgVO();
		msgVO.setSpeackerNo(speackerNo);
		msgVO.setReceiverNo(receiverNo);
		msgVO.setContent(content);
		msgVO.setTime(time);
		msgVO.setDirection(direction);
		msgVO.setIsRead(isRead);
		msgVO.setMsgNo(msgNo);
		dao.update(msgVO);

		return msgVO;
	}

	public MsgVO getOneMsg(Integer msgNo) {
		return dao.findByPrimaryKey(msgNo);
	}

	public List<MsgVO> getMsgBySpeackerNo(Integer speackerNo) {
		return dao.findBySpeackerNo(speackerNo);
	}

	public List<MsgVO> getAll() {
		return dao.getAll();
	}

}
