package com.message.model;

import java.util.List;

public interface Msg_interface {
	public void insert(MsgVO msgVO);

	public void update(MsgVO msgVO);

//	public void delete(Integer msgNo);
	public MsgVO findByPrimaryKey(Integer msgNo);

	public List<MsgVO> findBySpeackerNo(Integer speackerNo);

	public List<MsgVO> getAll();
}
