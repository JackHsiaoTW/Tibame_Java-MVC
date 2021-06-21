package com.attraction.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadMyAttractionJson {

	public static void sendtoDB(String path, String sort)
			throws JSONException, SQLException, ClassNotFoundException, IOException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
		String userid = "root";
		String passwd = "1qaz2wsx3edc";

		final String ADD_STMT = "INSERT INTO attraction (sort, attra_name, descr, location, is_on_shelf,attra_pic1) values (?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);

//		讀取json檔案
		StringBuilder sb = new StringBuilder();
		String str;
		FileReader fr = new FileReader(path);

		BufferedReader br = new BufferedReader(fr);
		while ((str = br.readLine()) != null) { // 條件檢查不為空值
//            System.out.println(str);
			sb.append(str);
		}

//        System.out.println(sb);

		br.close();
		fr.close();

//        分析檔案

		JSONObject all = new JSONObject(sb.toString());
		JSONArray Info = all.getJSONObject("XML_Head").getJSONObject("Infos").getJSONArray("Info");

		try {
			for (int i = 0; i < Info.length(); i++) {
				JSONObject attra = Info.getJSONObject(i);
				System.out.println(attra.getString("Name"));
				System.out.println(attra.getString("Description"));
				System.out.println(attra.getString("Add"));
				System.out.println(attra.getString("Picture1"));
//		送入資料庫

				pstmt = con.prepareStatement(ADD_STMT);

				pstmt.setString(1, sort);
				pstmt.setString(2, attra.getString("Name"));
				pstmt.setString(3, attra.getString("Description"));
				pstmt.setString(4, attra.getString("Add"));
				pstmt.setInt(5, 1);
				pstmt.setString(6, attra.getString("Picture1"));
				pstmt.executeUpdate();
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		sendtoDB("C:\\Users\\CJ0201019\\Desktop\\專題一大包\\觀光局公開景點資料庫json\\scenic_spot.json","景點");
		sendtoDB("C:\\Users\\CJ0201019\\Desktop\\專題一大包\\觀光局公開景點資料庫json\\restaurant.json", "餐廳");
		sendtoDB("C:\\Users\\CJ0201019\\Desktop\\專題一大包\\觀光局公開景點資料庫json\\hotel.json","住宿");

	}

}
