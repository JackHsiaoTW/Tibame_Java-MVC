package com.serviceMsg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SvcMsgJDBCDAO implements SvcMsgDAO_inteface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GuideMe?useSSL=false&serverTimezone=Asia/Taipei&";
	public static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO service_message(Member_No, Emplo_No, Content, Time, Direction) VALUES (?, ?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE service_message SET Member_No = ?, Emplo_No = ?, Content = ?, Time = ?, Direction = ? WHERE Service_Message_No = ?";
//	private static final String DELETE_STMT = "DELETE FROM service_message WHERE Service_Message_No = ?";
	private static final String FIND_BY_PK = "SELECT * FROM service_message WHERE Service_Message_No = ?";
	private static final String FIND_BY_MEMNO = "SELECT * FROM service_message WHERE Member_No = ? ";
	private static final String GET_ALL = "SELECT * FROM service_message";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(SvcMsgVO svcMsgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, svcMsgVO.getMemberNo());
			pstmt.setInt(2, svcMsgVO.getEmpNo());
			pstmt.setString(3, svcMsgVO.getContent());
			pstmt.setTimestamp(4, svcMsgVO.getTime());
			pstmt.setBoolean(5, svcMsgVO.getDirection());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public SvcMsgVO findByPrimaryKey(Integer svcMsgNo) {
		SvcMsgVO svcMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, svcMsgNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				svcMsgVO = new SvcMsgVO();
				svcMsgVO.setSvcMsgNo(rs.getInt("Service_Message_No"));
				svcMsgVO.setMemberNo(rs.getInt("Member_No"));
				svcMsgVO.setEmpNo(rs.getInt("Emplo_No"));
				svcMsgVO.setContent(rs.getString("Content"));
				svcMsgVO.setTime(rs.getTimestamp("Time"));
				svcMsgVO.setDirection(rs.getBoolean("Direction"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

		return svcMsgVO;
	}

	@Override
	public List<SvcMsgVO> findByMemberNo(Integer memberNo) {
		List<SvcMsgVO> list = new ArrayList<SvcMsgVO>();
		SvcMsgVO svcMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMNO);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				svcMsgVO = new SvcMsgVO();
				svcMsgVO.setSvcMsgNo(rs.getInt("Service_Message_No"));
				svcMsgVO.setMemberNo(rs.getInt("Member_No"));
				svcMsgVO.setEmpNo(rs.getInt("Emplo_No"));
				svcMsgVO.setContent(rs.getString("Content"));
				svcMsgVO.setTime(rs.getTimestamp("Time"));
				svcMsgVO.setDirection(rs.getBoolean("Direction"));
				list.add(svcMsgVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<SvcMsgVO> getAll() {
		List<SvcMsgVO> list = new ArrayList<SvcMsgVO>();
		SvcMsgVO svcMsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				svcMsgVO = new SvcMsgVO();
				svcMsgVO.setSvcMsgNo(rs.getInt("Service_Message_No"));
				svcMsgVO.setMemberNo(rs.getInt("Member_No"));
				svcMsgVO.setEmpNo(rs.getInt("Emplo_No"));
				svcMsgVO.setContent(rs.getString("Content"));
				svcMsgVO.setTime(rs.getTimestamp("Time"));
				svcMsgVO.setDirection(rs.getBoolean("Direction"));
				list.add(svcMsgVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

}
