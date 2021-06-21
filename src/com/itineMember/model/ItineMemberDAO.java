package com.itineMember.model;

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

public class ItineMemberDAO implements ItineMemberDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx3edc";

	private static final String INSERT_STMT = "insert into itine_member (itine_no, member_no, iseditable) values (?,?,?);";
	private static final String UPDATE_STMT = "update itine_member set iseditable = ? where itine_no=? and member_no=?;";
	private static final String GET_BYITINENO_STMT = "select * from itine_member where itine_no=?;";
	private static final String GET_BYMEMBERNO_STMT = "select * from itine_member where member_no=?;";

	@Override
	public void add(ItineMemberVO itineMember) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineMember.getItineNo());
			pstmt.setInt(2, itineMember.getMemberNo());
			pstmt.setInt(3, itineMember.getIsEditable());

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
	public void update(ItineMemberVO itineMember) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, itineMember.getIsEditable());
			pstmt.setInt(2, itineMember.getItineNo());
			pstmt.setInt(3, itineMember.getMemberNo());
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
	public List<ItineMemberVO> findByItineNo(Integer itineNo) {
		// TODO Auto-generated method stub
		List<ItineMemberVO> list = new ArrayList<ItineMemberVO>();
		ItineMemberVO itineMemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_BYITINENO_STMT);
			pstmt.setInt(1, itineNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineMemberVO = new ItineMemberVO();
				itineMemberVO.setItineNo(itineNo);
				itineMemberVO.setMemberNo(rs.getInt("member_no"));
				itineMemberVO.setIsEditable(rs.getInt("iseditable"));
				list.add(itineMemberVO);
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
	public List<ItineMemberVO> findByMemberNo(Integer memberNo) {
		// TODO Auto-generated method stub
		List<ItineMemberVO> list = new ArrayList<ItineMemberVO>();
		ItineMemberVO itineMemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_BYMEMBERNO_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineMemberVO = new ItineMemberVO();
				itineMemberVO.setMemberNo(memberNo);
				itineMemberVO.setItineNo(rs.getInt("itine_no"));
				itineMemberVO.setIsEditable(rs.getInt("iseditable"));
				list.add(itineMemberVO);
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
