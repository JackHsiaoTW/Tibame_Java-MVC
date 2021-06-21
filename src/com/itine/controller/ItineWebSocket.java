package com.itine.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/ItineWebSocket/{itineID}")
public class ItineWebSocket {

	private static final Map<String,Set<Session>> connectedSessionsMap = Collections.synchronizedMap(new HashMap<String,Set<Session>>());
	
	@OnOpen
	public void onOpen(@PathParam("itineID")String itineID,Session userSession) {
		
//		boolean isThere = false;
//		for(String key : connectedSessionsMap.keySet()) {
//			if(key.equals(itineID)) {
//				Set<Session> sessionSet = connectedSessionsMap.get(key);
//				sessionSet.add(userSession);
//				connectedSessionsMap.put(itineID, sessionSet);
//				isThere = true;
//				break;
//			}
//		}
//		if(!isThere) {
//			Set<Session> sessionSet = new HashSet<Session>();
//			sessionSet.add(userSession);
//			connectedSessionsMap.put(itineID,sessionSet);
//		}
		
		if(connectedSessionsMap.containsKey(itineID)) {
			Set<Session> sessionSet = connectedSessionsMap.get(itineID);
			sessionSet.add(userSession);
			connectedSessionsMap.put(itineID, sessionSet);
		}else {
			Set<Session> sessionSet = new HashSet<Session>();
			sessionSet.add(userSession);
			connectedSessionsMap.put(itineID, sessionSet);
		}
		
		
		String text = "SessionID = "+userSession.getId()+"; connected; itineID = "+itineID;
		System.out.println(text);
		
		
	}
	
	@OnMessage
	public void onMessage(@PathParam("itineID")String itineID, Session userSession, String messageData) {
//		for(String key : connectedSessionsMap.keySet()) {
//			Set<Session> sessionSet = connectedSessionsMap.get(key);
//			for(Session session : sessionSet) {
//				if(session.isOpen()) {
//					session.getAsyncRemote().sendText(message);
//				}
//			}
//		}
		
		for(Session session : connectedSessionsMap.get(itineID)) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(messageData);
			}
		}
		
		System.out.println("somemessage received : "+ messageData);
		
		try {
			JSONObject messageDataObj = new JSONObject(messageData);
			if("message".equals(messageDataObj.getString("action"))) {
				String speaker = messageDataObj.getString("speaker");
				String message = messageDataObj.getString("message");
				JedisHandleBoardMessage.saveBoardMessage(itineID, speaker, message);
				System.out.println("存入資料庫成功!");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onClose(@PathParam("itineID")String itineID,Session userSession, CloseReason closeReason) {
		Set<Session> sessionSet = connectedSessionsMap.get(itineID);
		sessionSet.remove(userSession);
		connectedSessionsMap.put(itineID,sessionSet);
		String text = "SessionID = "+userSession+"; disconnected; close code = "+closeReason.getCloseCode()+"; close reason = "+closeReason.getReasonPhrase()+";";
		System.out.println(text);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
	
}
