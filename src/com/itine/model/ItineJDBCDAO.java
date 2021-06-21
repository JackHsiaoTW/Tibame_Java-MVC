package com.itine.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItineJDBCDAO implements ItineDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx3edc";

	private static final String GET_MEMBERITINE_STMT = "select itine_name, update_time, itine_status, itine_board from itine where builder=? order by Update_Time desc;";
	private static final String UPDATE_STMT = "update itine set itine_name=?, itine_status=?, itine_board=? where itine_no=?";
	private static final String INSERT_STMT = "insert into itine (itine_name, builder, itine_status) values (?,?,?)";

	@Override
	public void add(ItineVO itine) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itine.getItineName());
			pstmt.setInt(2, itine.getBuilder());
			pstmt.setInt(3, 0);
			
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public void update(ItineVO itine) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, itine.getItineName());
			pstmt.setInt(2, itine.getItineStatus());
			pstmt.setString(3, itine.getItineBoard());
			pstmt.setInt(4, itine.getItineNo());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	@Override
	public List<ItineVO> findByBuilder(Integer builder) {
		List<ItineVO> list = new ArrayList<ItineVO>();
		ItineVO itineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBERITINE_STMT);
			pstmt.setInt(1, builder);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineVO = new ItineVO();
				itineVO.setItineName(rs.getString("itine_name"));
				itineVO.setUpdateTime(rs.getTimestamp("update_time"));
				itineVO.setItineStatus(rs.getInt("itine_status"));
				itineVO.setItineBoard(rs.getString("itine_board"));
				list.add(itineVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

	public static void main(String[] args) {

		ItineJDBCDAO dao = new ItineJDBCDAO();

		// 查詢
		List<ItineVO> list = dao.findByBuilder(1);
		for (ItineVO itineVO : list) {
			System.out.print(itineVO.getItineName() + " ");
			System.out.print(itineVO.getUpdateTime() + " ");
			System.out.print(itineVO.getItineStatus() + " ");
			System.out.print(itineVO.getItineBoard() + " ");
			System.out.println();
		}

		// 更改
		ItineVO itineVO2 = new ItineVO();
		itineVO2.setItineNo(1);
		itineVO2.setItineName("台中好玩五日遊");
		itineVO2.setItineStatus(2);
		itineVO2.setItineBoard("jason: 要去哪玩呢?");
		dao.update(itineVO2);
		System.out.println("更改成功");

		// 新增
		ItineVO itineVO3 = new ItineVO();
		itineVO3.setBuilder(3);
		itineVO3.setItineName("澎湖五日遊");
		dao.add(itineVO3);
		System.out.println("新增成功");

	}

	@Override
	public ItineVO findByPK(Integer itineNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
