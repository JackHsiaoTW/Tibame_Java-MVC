package com.itine.controller;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.JedisPoolUtil;

public class JedisHandleBoardMessage {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static List<List> getHistoryMsg(String itineID){
		Jedis jedis = null;
		jedis = pool.getResource();
		jedis.auth("123456");
		List<String> historyData = jedis.lrange(itineID,0,-1);
		jedis.close();
		List<List> msgList = new ArrayList<List>();
		for(String amsg : historyData){
			List<String> msgListWrap = new ArrayList<String>();
			String name = amsg.substring(0,amsg.indexOf(":"));
			msgListWrap.add(name);
			msgListWrap.add(amsg);
			msgList.add(msgListWrap);
		}
		return msgList;
	}
	
	public static void saveBoardMessage(String itineID,String sender,String message) {
		Jedis jedis = pool.getResource();
		jedis.auth("123456");
		String full = new StringBuilder(sender).append(":").append(message).toString();
		String key = "itineID:"+itineID;
		jedis.rpush(key, full);
		jedis.close();
	}
	
}
