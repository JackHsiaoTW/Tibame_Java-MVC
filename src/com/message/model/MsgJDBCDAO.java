package com.message.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MsgJDBCDAO implements Msg_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GuideMe?useSSL=false&serverTimezone=Asia/Taipei&";
	public static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO Message(Speacker_No, Receiver_No, Content, Time, Direction, IsRead) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Message SET Speacker_No = ?, Receiver_No = ?, Content = ?, Time = ?, Direction = ?, IsRead = ? WHERE Message_No = ?";
//	private static final String DELETE_STMT = "DELETE FROM Message WHERE Message_No = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Message WHERE Message_No = ?";
	private static final String FIND_BY_SPEACKERNO = "SELECT * FROM Message WHERE Speacker_No = ? ";
	private static final String GET_ALL = "SELECT * FROM Message";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(MsgVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, msgVO.getSpeackerNo());
			pstmt.setInt(2, msgVO.getReceiverNo());
			pstmt.setString(3, msgVO.getContent());
			pstmt.setTimestamp(4, msgVO.getTime());
			pstmt.setBoolean(5, msgVO.getDirection());
			pstmt.setBoolean(6, msgVO.getIsRead());
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
	public void update(MsgVO msgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, msgVO.getSpeackerNo());
			pstmt.setInt(2, msgVO.getReceiverNo());
			pstmt.setString(3, msgVO.getContent());
			pstmt.setTimestamp(4, msgVO.getTime());
			pstmt.setBoolean(5, msgVO.getDirection());
			pstmt.setBoolean(6, msgVO.getIsRead());
			pstmt.setInt(7, msgVO.getMsgNo());
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
	public MsgVO findByPrimaryKey(Integer msgNo) {
		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, msgNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgVO = new MsgVO();
				msgVO.setMsgNo(rs.getInt("Message_No"));
				msgVO.setSpeackerNo(rs.getInt("Speacker_No"));
				msgVO.setReceiverNo(rs.getInt("Receiver_No"));
				msgVO.setContent(rs.getString("Content"));
				msgVO.setTime(rs.getTimestamp("Time"));
				msgVO.setDirection(rs.getBoolean("Direction"));
				msgVO.setIsRead(rs.getBoolean("IsRead"));
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

		return msgVO;
	}

	@Override
	public List<MsgVO> findBySpeackerNo(Integer speackerNo) {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_SPEACKERNO);
			pstmt.setInt(1, speackerNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgVO = new MsgVO();
				msgVO.setMsgNo(rs.getInt("Message_No"));
				msgVO.setSpeackerNo(rs.getInt("Speacker_No"));
				msgVO.setReceiverNo(rs.getInt("Receiver_No"));
				msgVO.setContent(rs.getString("Content"));
				msgVO.setTime(rs.getTimestamp("Time"));
				msgVO.setDirection(rs.getBoolean("Direction"));
				msgVO.setIsRead(rs.getBoolean("IsRead"));
				list.add(msgVO);
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
	public List<MsgVO> getAll() {
		List<MsgVO> list = new ArrayList<MsgVO>();
		MsgVO msgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				msgVO = new MsgVO();
				msgVO.setMsgNo(rs.getInt("Message_No"));
				msgVO.setSpeackerNo(rs.getInt("Speacker_No"));
				msgVO.setReceiverNo(rs.getInt("Receiver_No"));
				msgVO.setContent(rs.getString("Content"));
				msgVO.setTime(rs.getTimestamp("Time"));
				msgVO.setDirection(rs.getBoolean("Direction"));
				msgVO.setIsRead(rs.getBoolean("IsRead"));
				list.add(msgVO);
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
