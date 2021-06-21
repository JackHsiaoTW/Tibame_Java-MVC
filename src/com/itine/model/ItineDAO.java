package com.itine.model;

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

public class ItineDAO implements ItineDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_MEMBERITINE_STMT = "select * from itine where builder=? order by Update_Time desc;";
	private static final String UPDATE_STMT = "update itine set itine_name=?, itine_status=?, itine_board=? where itine_no=?";
	private static final String INSERT_STMT = "insert into itine (itine_name, builder, itine_status) values (?,?,?)";
	private static final String GET_ONE_STMT = "select * from itine where itine_no = ?;";
	
	@Override
	public void add(ItineVO itine) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, itine.getItineName());
			pstmt.setInt(2, itine.getBuilder());
			pstmt.setInt(3, 0);
			
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
	public void update(ItineVO itine) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, itine.getItineName());
			pstmt.setInt(2, itine.getItineStatus());
			pstmt.setString(3, itine.getItineBoard());
			pstmt.setInt(4, itine.getItineNo());
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
	public List<ItineVO> findByBuilder(Integer builder) {
		List<ItineVO> list = new ArrayList<ItineVO>();
		ItineVO itineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_MEMBERITINE_STMT);
			pstmt.setInt(1, builder);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineVO = new ItineVO();
				itineVO.setBuilder(builder);
				itineVO.setItineNo(rs.getInt("itine_no"));
				itineVO.setItineName(rs.getString("itine_name"));
				itineVO.setUpdateTime(rs.getTimestamp("update_time"));
				itineVO.setItineStatus(rs.getInt("itine_status"));
				itineVO.setItineBoard(rs.getString("itine_board"));
				list.add(itineVO);
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
	public ItineVO findByPK(Integer itineNo) {
		// TODO Auto-generated method stub
		ItineVO itineVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itineNo);
			rs = pstmt.executeQuery();
				
				rs.next();
				itineVO = new ItineVO();
				itineVO.setItineNo(itineNo);
				itineVO.setItineName(rs.getString("itine_name"));
				itineVO.setUpdateTime(rs.getTimestamp("update_time"));
				itineVO.setBuilder(rs.getInt("builder"));
				itineVO.setItineStatus(rs.getInt("itine_status"));
				itineVO.setItineBoard(rs.getString("itine_board"));
		
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
		
		
		
		
		
		return itineVO;
	}
	
}
