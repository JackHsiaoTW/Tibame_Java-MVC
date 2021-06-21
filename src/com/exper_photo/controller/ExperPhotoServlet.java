package com.exper_photo.controller;

import java.io.IOException;

import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.env.IModule.IService;

import com.exper_photo.model.ExperPhotoService;
import com.exper_photo.model.ExperPhotoVO;
import com.exper_photo.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ExperPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExperPhotoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		// 顯示圖片
		if ("getOneExperPho".equals(action)) {
			res.setContentType("img/jpg");
			Integer exper_photo_no = new Integer(req.getParameter("exper_photo_no"));
			ExperPhotoService expphoSvc = new ExperPhotoService();
			ExperPhotoVO expphoVO = expphoSvc.getOneExperPho(exper_photo_no);
			byte[] exppho = expphoVO.getPhoto();
			res.getOutputStream().write(exppho);
			res.getOutputStream().flush();
			res.getOutputStream().close();
			return;
		}

		if ("getListExperPhoByExperNo".equals(action)) {
			res.setContentType("img/jpg");
			Integer exper_no = new Integer(req.getParameter("exper_no"));
			ExperPhotoService expphoSvc = new ExperPhotoService();
			List<ExperPhotoVO> list = expphoSvc.getPhotoByExperNo(exper_no);
			if (list.size() != 0) {
				byte[] exppho = list.get(0).getPhoto();
				res.getOutputStream().write(exppho);

			} else {
				InputStream in = getServletContext().getResourceAsStream("/resources/img/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				res.getOutputStream().write(b);
				in.close();
			}
			res.getOutputStream().flush();
			res.getOutputStream().close();
			return;
		}

		if ("get_Photo_By_Exper_photo_no".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("exper_photo_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入體驗照片編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_photo_no = null;
				try {
					exper_photo_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				ExperPhotoVO expphoVO = expphoSvc.getOneExperPho(exper_photo_no);
				if (expphoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expphoVO", expphoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_photo/listOneExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("get_Photo_By_Exper_no".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("exper_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入體驗編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_no = null;
				try {
					exper_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				List<ExperPhotoVO> list = expphoSvc.getPhotoByExperNo(exper_no);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/listOneExperience.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				req.setAttribute("exper_no", exper_no);
				String url = "/back-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/listOneExperience.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer exper_photo_no = new Integer(req.getParameter("exper_photo_no").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				ExperPhotoVO expphoVO = expphoSvc.getOneExperPho(exper_photo_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("expphoVO", expphoVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/exper_photo/update_ExperPhoto_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/listAllExperPhoto.jsp");
				failureView.forward(req, res);
			}
		}

		if ("upDate_By_Exper_Photo_No".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer exper_photo_no = new Integer(req.getParameter("exper_photo_no").trim());

				Part part = req.getPart("upfile1");

				InputStream in = part.getInputStream();
				byte[] photo = new byte[in.available()];
				in.read(photo);
				in.close();

				ExperPhotoVO expphoVO = new ExperPhotoVO();
				expphoVO.setExper_photo_no(exper_photo_no);
				expphoVO.setPhoto(photo);
				/*************************** 2.開始修改資料 *****************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				expphoSvc.upDatePhotoByExperNo(expphoVO);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expphoVO", expphoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/Update_Photo_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("upDate_By_Exper_no".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			ExperPhotoVO expphoVO = null;
			ExperPhotoService expphoSvc = new ExperPhotoService();

			try {
				Integer exper_no = new Integer(req.getParameter("exper_no").trim());
				Collection<Part> col = req.getParts();
				for (Part part : col) {

					if (part.getContentType() != null && part.getContentType().indexOf("image") >= 0) {

						InputStream in = part.getInputStream();
						byte[] photo = new byte[in.available()];
						in.read(photo);
						in.close();

						expphoVO = new ExperPhotoVO();
						expphoVO.setExper_no(exper_no);
						expphoVO.setPhoto(photo);
						/*************************** 2.開始修改資料 ******************************/
						expphoSvc.insert(expphoVO);
					}

				}
				List<ExperPhotoVO> list = expphoSvc.getPhotoByExperNo(exper_no);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/back-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				req.setAttribute("exper_no", exper_no);
				req.setAttribute("list", list);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/listAllExperPhoto.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				ExperPhotoVO expphoVO = null;
				Integer exper_photo_no = new Integer(req.getParameter("exper_photo_no").trim());
				Integer exper_no = new Integer(req.getParameter("exper_no").trim());
				byte[] photo = null;
				ExperPhotoService expphoSvc = new ExperPhotoService();
				Part part = req.getPart("upfile1");
				InputStream in = part.getInputStream();
				if (in.available() == 0) {

					expphoVO = expphoSvc.getOneExperPho(exper_photo_no);
					photo = expphoVO.getPhoto();

				} else {
					expphoVO = expphoSvc.getOneExperPho(exper_photo_no);
					photo = new byte[in.available()];
					in.read(photo);
					in.close();
				}

				expphoVO.setExper_no(exper_no);
				expphoVO.setExper_photo_no(exper_photo_no);
				expphoVO.setPhoto(photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("expphoVO", expphoVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/update_ExperPhoto_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				expphoSvc = new ExperPhotoService();
				expphoSvc.update(expphoVO);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("expphoVO", expphoVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/update_ExperPhoto_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer exper_photo_no = new Integer(req.getParameter("exper_photo_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				expphoSvc.deleteExpPho(exper_photo_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/listAllExperPhoto.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addLec.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String str = req.getParameter("exper_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入體驗編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/addExperPhoto.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer exper_no = null;
				try {
					exper_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("體驗編號格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/addExperPhoto.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				byte[] photo = null;
				try {
					Part part = req.getPart("upfile1");

					InputStream is = part.getInputStream();
					photo = new byte[is.available()];
					is.read(photo);
					is.close();
				} catch (Exception e) {
					errorMsgs.add("有錯誤問題");
				}
				if (photo.length == 0)
					photo = null;
				ExperPhotoVO epVO = new ExperPhotoVO();
				epVO.setExper_no(exper_no);
				epVO.setPhoto(photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("epVO", epVO); // 含有輸入格式錯誤的LecVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/exper_photo/addExperPhoto.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ExperPhotoService expphoSvc = new ExperPhotoService();
				epVO = expphoSvc.addExperPhoto(exper_no, photo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/exper_photo/listAllExperPhoto.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll_exp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/exper_photo/addExperPhoto.jsp");
				failureView.forward(req, res);

			}

		}

	}
}
