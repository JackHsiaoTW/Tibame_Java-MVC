//package com.favorite_exper.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class FavoriteExperJDBCDAO implements FavoriteExperDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "123456";
//
//	private static final String INSERT_STMT = "INSERT INTO Favorite_Exper (Exper_No,Member_No) VALUES ( ?, ?)";
//	private static final String GET_ALL_STMT = "SELECT Exper_No,Member_No FROM Favorite_Exper order by Exper_No";
//	private static final String GET_ONE_STMT = "SELECT Exper_No,Member_No FROM Favorite_Exper where Exper_No = ?";
//	private static final String DELETE = "DELETE FROM Favorite_Exper where Exper_No = ?";
//	private static final String UPDATE = "UPDATE Favorite_Exper set Member_No=? where Exper_No = ?";
//
//	@Override
//	public void insert(FavoriteExperVO favorexpVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, favorexpVO.getExper_no());
//			pstmt.setInt(2, favorexpVO.getMember_no());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(FavoriteExperVO favorexpVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//			pstmt.setInt(1, favorexpVO.getMember_no());
//			pstmt.setInt(2, favorexpVO.getExper_no());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(Integer Exper_no) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, Exper_no);
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public List<FavoriteExperVO> findByPrimaryKey(Integer Exper_no) {
//		List<FavoriteExperVO> list = new ArrayList<FavoriteExperVO>();
//		FavoriteExperVO favorexpVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, Exper_no);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// favorexpVO 也稱為 Domain objects
//				favorexpVO = new FavoriteExperVO();
//				favorexpVO.setExper_no(rs.getInt("Exper_no"));
//				favorexpVO.setMember_no(rs.getInt("Member_no"));
//				list.add(favorexpVO);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	@Override
//	public List<FavoriteExperVO> getAll() {
//		List<FavoriteExperVO> list2 = new ArrayList<FavoriteExperVO>();
//		FavoriteExperVO favorexpVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// favorexpVO 也稱為 Domain objects
//				favorexpVO = new FavoriteExperVO();
//				favorexpVO.setExper_no(rs.getInt("Exper_no"));
//				favorexpVO.setMember_no(rs.getInt("Member_no"));
//				list2.add(favorexpVO);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list2;
//	}
//
//	public static void main(String[] args) {
//
//		FavoriteExperJDBCDAO dao = new FavoriteExperJDBCDAO();
//
////		//新增
////		FavoriteExperVO favorexpVO1 = new FavoriteExperVO();
////		favorexpVO1.setExper_no(1);
////		favorexpVO1.setMember_no(1);
////		dao.insert(favorexpVO1);
////		System.out.println("---------------------");
//
////		// 修改
////		FavoriteExperVO favorexpVO2 = new FavoriteExperVO();
////		favorexpVO2.setExper_no(2);
////		favorexpVO2.setMember_no(4);
////	
////		dao.update(favorexpVO2);
//
////		 //刪除
////		dao.delete(1,1);
//
////		// 查詢
//		List<FavoriteExperVO> list = dao.findByPrimaryKey(2);
//		for (FavoriteExperVO aFavorite : list) {
//			System.out.print(aFavorite.getExper_no() + ",");
//			System.out.println(aFavorite.getMember_no());
//		}
//		System.out.println("---------------------");
//
//		// 查詢
////		List<FavoriteExperVO> list2 = dao.getAll();
////		for (FavoriteExperVO aFavorite : list2) {
////			System.out.print(aFavorite.getExper_no() + ",");
////			System.out.print(aFavorite.getMember_no() + ",");
////			System.out.println();
////		}
//	}
//}