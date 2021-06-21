package util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

import com.google.gson.Gson;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.notify.model.NotifyVO;

@ServerEndpoint("/NotifyWS/{notifyUserID}")
public class NotifyWebSocket {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("notifyUserID") String notifyUserID, Session userSession) throws IOException {
		sessionsMap.put(notifyUserID, userSession);
		MemberVO memberVO = null;
		if (notifyUserID != null && !notifyUserID.isEmpty()) {
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.getOneMember(Integer.parseInt(notifyUserID));
		}

		String text = String.format("Session ID = %s, connected; notifyUserID = %s, notifyUserName = %s",
				userSession.getId(), notifyUserID, (memberVO == null ? "" : memberVO.getName()));
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("onMessage:");
		System.out.println("userSession:" + userSession);
		System.out.println("message:" + message);

		try {
			String msgType = (String) (new JSONObject(message)).getString("msgType");
			int sender = Integer.parseInt((new JSONObject(message)).getString("sender"));
			String notifyStr = (new JSONObject(message)).get("Msg").toString();
			NotifyVO notifyMessage = gson.fromJson(notifyStr, NotifyVO.class);
			String notifyPerson = notifyMessage.getNotifyPerson().toString();
			Session receiverSession = sessionsMap.get(notifyPerson);

			System.out.println("notifyPerson:" + notifyPerson + "    value:" + receiverSession);

			MemberService memberSvc = new MemberService();
			MemberVO memberVO = memberSvc.getOneMember(sender);

			if (msgType.equals("friendInvite")) {
				notifyMessage.setNotifyContent(memberVO.getName() + " 要求加入好友");
			} else if (msgType.equals("acceptFriendInvitation")) {
				notifyMessage.setNotifyContent("與" + memberVO.getName() + "已成為好友");
			}

			if (sessionsMap.containsKey(notifyPerson)) {
				System.out.println("---Gotcha!---");
				receiverSession.getAsyncRemote().sendText(gson.toJson(notifyMessage));
			}

//			for (Map.Entry<String, Session> session : sessionsMap.entrySet()) {
//				System.out.println("key:"+session.getKey()+"    value:"+session.getValue());
//				System.out.println("notifyMessage.getNotifyPerson():"+notifyMessage.getNotifyPerson()+"\n");
//				int userIdFromSession = new Integer(session.getKey());
//				
//				if (userIdFromSession == notifyPerson) {
//					System.out.println("---Gotcha!---");
//					((Session) session).getAsyncRemote().sendText(message);
//				}
//			}
			System.out.println("Message received: " + message);
			System.out.println("Message send: " + gson.toJson(notifyMessage));

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
//		connectedSessions.remove(userSession);
		sessionsMap.containsValue(userSession);

		String uSession = sessionsMap.keySet().stream().filter(k -> sessionsMap.get(k).equals(userSession))
				.peek(k -> System.out.println("session ID =" + userSession)).toString();
//		mapToInt(k -> Integer.parseInt(k));

		sessionsMap.remove(uSession);

		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
//		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
}
