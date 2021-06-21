package com.itineItem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItineItemDAO implements ItineItemDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into itine_item (itine_no, attra_no, start_time, end_time, manager) values (?,?,?,?,?);";
	private static final String UPDATE_STMT = "update itine_item set start_time=?, end_time=?, Note=?, Manager=?, isDone=?, finish_Date=?, task_note=? where itine_no=? and attra_no=?;";
	private static final String GET_BYITINENO_STMT = "select attra_no, start_time, end_time, note, manager, isdone, finish_date, task_note from itine_item where itine_no=?;";
	private static final String GET_BYMANAGER_STMT = "select * from itine_item where itine_no=? and manager=?;";
	private static final String GET_BYPK_STMT = "select * from itine_item where itine_no=? and attra_no=?;";
	private static final String DELETE_STMT = "delete from itine_item where itine_no=? and attra_no=?;";
	private static final String GETALL_STMT ="select * from itine_item;";
		
	@Override
	public void add(ItineItemVO itineItemVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineItemVO.getItineNo());
			pstmt.setInt(2, itineItemVO.getAttraNo());
			pstmt.setTimestamp(3, itineItemVO.getStartTime());
			pstmt.setTimestamp(4, itineItemVO.getEndTime());
			pstmt.setInt(5, itineItemVO.getManager());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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

	public void delete(Integer itineNo, Integer attraNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
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
	public List<ItineItemVO> findByItineNo(Integer itineNo) {
		// TODO Auto-generated method stub
		List<ItineItemVO> list = new ArrayList<ItineItemVO>();
		ItineItemVO itineItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYITINENO_STMT);
			pstmt.setInt(1, itineNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineItemVO = new ItineItemVO();
				itineItemVO.setItineNo(itineNo);
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMANAGER_STMT);
			pstmt.setInt(1, itineNo);
			pstmt.setInt(2, manager);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineItemVO = new ItineItemVO();
				itineItemVO.setItineNo(itineNo);
				itineItemVO.setManager(manager);
				itineItemVO.setAttraNo(rs.getInt("attra_no"));
				itineItemVO.setStartTime(rs.getTimestamp("start_time"));
				itineItemVO.setEndTime(rs.getTimestamp("end_time"));
				itineItemVO.setNote(rs.getString("note"));
				itineItemVO.setIsDone(rs.getBoolean("isDone"));
				itineItemVO.setFinishDate(rs.getDate("finish_date"));
				itineItemVO.setTaskNote(rs.getString("task_note"));
				list.add(itineItemVO);
			}
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
	public ItineItemVO findByItineNoAndAttraNo(Integer itineNo, Integer attraNo) {
		// TODO Auto-generated method stub
		ItineItemVO itineItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BYPK_STMT);
			pstmt.setInt(1, itineNo);
			pstmt.setInt(2, attraNo);
			rs = pstmt.executeQuery();

			rs.next();
			itineItemVO = new ItineItemVO();
			itineItemVO.setItineNo(itineNo);
			itineItemVO.setAttraNo(attraNo);
			itineItemVO.setStartTime(rs.getTimestamp("start_time"));
			itineItemVO.setEndTime(rs.getTimestamp("end_time"));
			itineItemVO.setNote(rs.getString("note"));
			itineItemVO.setManager(rs.getInt("manager"));
			itineItemVO.setIsDone(rs.getBoolean("isDone"));
			itineItemVO.setFinishDate(rs.getDate("finish_date"));
			itineItemVO.setTaskNote(rs.getString("task_note"));

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

		return itineItemVO;
	}

	@Override
	public List<ItineItemVO> getAll() {
		// TODO Auto-generated method stub
		List<ItineItemVO> list = new ArrayList<ItineItemVO>();
		ItineItemVO itineItemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineItemVO = new ItineItemVO();
				itineItemVO.setItineNo(rs.getInt("itine_no"));
				itineItemVO.setManager(rs.getInt("manager"));
				itineItemVO.setAttraNo(rs.getInt("attra_no"));
				itineItemVO.setStartTime(rs.getTimestamp("start_time"));
				itineItemVO.setEndTime(rs.getTimestamp("end_time"));
				itineItemVO.setNote(rs.getString("note"));
				itineItemVO.setIsDone(rs.getBoolean("isDone"));
				itineItemVO.setFinishDate(rs.getDate("finish_date"));
				itineItemVO.setTaskNote(rs.getString("task_note"));
				list.add(itineItemVO);
			}
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
}
