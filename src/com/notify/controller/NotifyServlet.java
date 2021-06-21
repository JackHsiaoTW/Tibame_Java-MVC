package com.notify.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.notify.model.NotifyService;
import com.notify.model.NotifyVO;

public class NotifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NotifyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("action---" + action);

		if ("addNotify".equals(action)) {
//			System.out.println("---in--action:" + action);

		} else if ("listOneNotify".equals(action)) {
//			System.out.println("---in--action:" + action);
			String notifyNoStr = req.getParameter("notifyNo");
			JSONArray array = new JSONArray();

			if (notifyNoStr != null && !notifyNoStr.isEmpty()) {
				Integer notifyNo = new Integer(notifyNoStr);
				NotifyService notifySvc = new NotifyService();
				NotifyVO notifyVO = notifySvc.getOneNotify(notifyNo);

				try {
					JSONObject obj = new JSONObject();
					obj.put("notifyNo", notifyVO.getNotifyNo());
					obj.put("notifyPerson", notifyVO.getNotifyPerson());
					obj.put("notifyContent", notifyVO.getNotifyContent());
					obj.put("notifyTime", notifyVO.getNotifyTime());
					array.put(obj);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// Bootstrap_modal
				boolean openListOneNotifyModal = true;
				req.setAttribute("openListOneNotifyModal", openListOneNotifyModal);
			}

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();

		} else if ("listOneMemberNotify".equals(action)) {
//			System.out.println("---in--action:" + action);
			String notifyPersonStr = req.getParameter("notifyPerson");
			JSONArray array = new JSONArray();

			if (notifyPersonStr != null && !notifyPersonStr.isEmpty()) {
				Integer notifyPerson = new Integer(notifyPersonStr);
				NotifyService notifySvc = new NotifyService();
				List<NotifyVO> notifyVOs = notifySvc.getOneNotifyByPerson(notifyPerson);

				try {
					for (NotifyVO notifyVO : notifyVOs) {
						JSONObject obj = new JSONObject();
						obj.put("notifyNo", notifyVO.getNotifyNo());
						obj.put("notifyPerson", notifyVO.getNotifyPerson());
						obj.put("notifyContent", notifyVO.getNotifyContent());
						obj.put("notifyTime", notifyVO.getNotifyTime());
						
//						System.out.println("notifyNo:"+notifyVO.getNotifyNo());
//						System.out.println("notifyPerson:"+notifyVO.getNotifyPerson());
//						System.out.println("notifyContent:"+notifyVO.getNotifyContent());
//						System.out.println("notifyTime:"+notifyVO.getNotifyTime());
//						System.out.println("---------===========----------");
						
						array.put(obj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
