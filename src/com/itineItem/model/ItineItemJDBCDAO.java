package com.itineItem.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.itine.model.ItineVO;

public class ItineItemJDBCDAO implements ItineItemDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx3edc";

	private static final String INSERT_STMT = "insert into itine_item (itine_no, attra_no, start_time, end_time) values (?,?,?,?);";
	private static final String UPDATE_STMT = "update itine_item set start_time=?, end_time=?, Note=?, Manager=?, isDone=?, finish_Date=?, task_note=? where itine_no=? and attra_no=?;";
	private static final String GET_BYITINENO_STMT = "select attra_no, start_time, end_time, note, manager, isdone, finish_date, task_note from itine_item where itine_no=?;";
	private static final String GET_BYMANAGER_STMT = "select attra_no, start_time, end_time, note, isdone, finish_date, task_note from itine_item where itine_no=? and manager=?;";
	private static final String DELETE_STMT = "delete from itine_item where itine_no=? and attra_no=?;";

	@Override
	public void add(ItineItemVO itineItem) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineItem.getItineNo());
			pstmt.setInt(2, itineItem.getAttraNo());
			pstmt.setTimestamp(3, itineItem.getStartTime());
			pstmt.setTimestamp(4, itineItem.getEndTime());

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
	public void update(ItineItemVO itineItem) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setTimestamp(1, itineItem.getStartTime());
			pstmt.setTimestamp(2, itineItem.getEndTime());
			pstmt.setString(3, itineItem.getNote());
			pstmt.setInt(4, itineItem.getManager());
			pstmt.setBoolean(5, itineItem.getIsDone());
			pstmt.setDate(6, itineItem.getFinishDate());
			pstmt.setString(7, itineItem.getTaskNote());
			pstmt.setInt(8, itineItem.getItineNo());
			pstmt.setInt(9, itineItem.getAttraNo());

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
	public List<ItineItemVO> findByItineNo(Integer itineNo) {
		// TODO Auto-generated method stub
		List<ItineItemVO> list = new ArrayList<ItineItemVO>();
		ItineItemVO itineItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYITINENO_STMT);
			pstmt.setInt(1, itineNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineItemVO = new ItineItemVO();
				itineItemVO.setAttraNo(rs.getInt("attra_no"));
				itineItemVO.setStartTime(rs.getTimestamp("start_time"));
				itineItemVO.setEndTime(rs.getTimestamp("end_time"));
				itineItemVO.setNote(rs.getString("note"));
				itineItemVO.setManager(rs.getInt("manager"));
				itineItemVO.setIsDone(rs.getBoolean("isDone"));
				itineItemVO.setFinishDate(rs.getDate("finish_date"));
				itineItemVO.setTaskNote(rs.getString("task_note"));
				list.add(itineItemVO);
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

	@Override
	public List<ItineItemVO> findByItineNoAndManager(Integer itineNo, Integer manager) {
		// TODO Auto-generated method stub
		List<ItineItemVO> list = new ArrayList<ItineItemVO>();
		ItineItemVO itineItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYMANAGER_STMT);
			pstmt.setInt(1, itineNo);
			pstmt.setInt(2, manager);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineItemVO = new ItineItemVO();
				itineItemVO.setAttraNo(rs.getInt("attra_no"));
				itineItemVO.setStartTime(rs.getTimestamp("start_time"));
				itineItemVO.setEndTime(rs.getTimestamp("end_time"));
				itineItemVO.setNote(rs.getString("note"));
				itineItemVO.setIsDone(rs.getBoolean("isDone"));
				itineItemVO.setFinishDate(rs.getDate("finish_date"));
				itineItemVO.setTaskNote(rs.getString("task_note"));
				list.add(itineItemVO);
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

	public static void main(String args[]) {

		ItineItemJDBCDAO dao = new ItineItemJDBCDAO();

		// 新增
//		ItineItemVO itineItemVO = new ItineItemVO();
//		itineItemVO.setItineNo(1);
//		itineItemVO.setAttraNo(6);
//		itineItemVO.setStartTime(new Timestamp(System.currentTimeMillis()));
//		itineItemVO.setEndTime(new Timestamp(System.currentTimeMillis()+60*60*1000));
//		
//		dao.add(itineItemVO);
//		System.out.println("新增成功!");

		// 修改
		ItineItemVO itineItemVO2 = new ItineItemVO();
		itineItemVO2.setItineNo(1);
		itineItemVO2.setAttraNo(5);
		itineItemVO2.setStartTime(new Timestamp(System.currentTimeMillis()));
		itineItemVO2.setEndTime(new Timestamp(System.currentTimeMillis() + 60 * 60 * 1000));
		itineItemVO2.setFinishDate(new Date(System.currentTimeMillis()));
		itineItemVO2.setIsDone(false);
		itineItemVO2.setManager(3);
		itineItemVO2.setNote("肉圓好像很好吃");
		itineItemVO2.setTaskNote("營業時間不確定");

		dao.update(itineItemVO2);
		System.out.println("修改成功!!");
		System.out.println("=========");

		// 查詢此行程有幾個行程明細
		List<ItineItemVO> list = dao.findByItineNo(1);
		for (ItineItemVO itineItemVO3 : list) {
			System.out.print(itineItemVO3.getAttraNo() + " ");
			System.out.print(itineItemVO3.getStartTime() + " ");
			System.out.print(itineItemVO3.getEndTime() + " ");
			System.out.print(itineItemVO3.getNote() + " ");
			System.out.print(itineItemVO3.getManager() + " ");
			System.out.print(itineItemVO3.getIsDone() + " ");
			System.out.print(itineItemVO3.getFinishDate() + " ");
			System.out.print(itineItemVO3.getTaskNote() + " ");
			System.out.println();
		}
		System.out.println("=======");

		// 查詢在此行程中幾個是此會員負責的
		List<ItineItemVO> list1 = dao.findByItineNoAndManager(1, 3);
		for (ItineItemVO itineItemVO4 : list1) {
			System.out.print(itineItemVO4.getAttraNo() + " ");
			System.out.print(itineItemVO4.getStartTime() + " ");
			System.out.print(itineItemVO4.getEndTime() + " ");
			System.out.print(itineItemVO4.getNote() + " ");
			System.out.print(itineItemVO4.getIsDone() + " ");
			System.out.print(itineItemVO4.getFinishDate() + " ");
			System.out.print(itineItemVO4.getTaskNote() + " ");
			System.out.println();
		}
		System.out.println("=======");
		
		// 刪除
		dao.delete(4, 2);
		System.out.println("刪除成功");
	}

	@Override
	public void delete(Integer itineNo, Integer attraNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, itineNo);
			pstmt.setInt(2, attraNo);

			pstmt.executeUpdate();
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
	public ItineItemVO findByItineNoAndAttraNo(Integer itineNo, Integer attraNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItineItemVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
