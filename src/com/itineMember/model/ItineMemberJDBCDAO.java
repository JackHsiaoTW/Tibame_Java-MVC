package com.itineMember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itine.model.ItineJDBCDAO;
import com.itine.model.ItineVO;

public class ItineMemberJDBCDAO implements ItineMemberDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx3edc";

	private static final String INSERT_STMT = "insert into itine_member (itine_no, member_no, iseditable) values (?,?,?);";
	private static final String UPDATE_STMT = "update itine_member set iseditable = ? where itine_no=? and member_no=?;";
	private static final String GET_BYITINENO_STMT = "select member_no, iseditable from itine_member where itine_no=?;";
	private static final String GET_BYMEMBERNO_STMT = "select itine_no, IsEditable from itine_member where member_no=?;";

	@Override
	public void add(ItineMemberVO itineMember) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, itineMember.getItineNo());
			pstmt.setInt(2, itineMember.getMemberNo());
			pstmt.setInt(3, itineMember.getIsEditable());

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
	public void update(ItineMemberVO itineMember) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, itineMember.getIsEditable());
			pstmt.setInt(2, itineMember.getItineNo());
			pstmt.setInt(3, itineMember.getMemberNo());
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
	public List<ItineMemberVO> findByItineNo(Integer itineNo) {
		// TODO Auto-generated method stub
		List<ItineMemberVO> list = new ArrayList<ItineMemberVO>();
		ItineMemberVO itineMemberVO = null;

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
				itineMemberVO = new ItineMemberVO();
				itineMemberVO.setMemberNo(rs.getInt("member_no"));
				itineMemberVO.setIsEditable(rs.getInt("iseditable"));
				list.add(itineMemberVO);
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
	public List<ItineMemberVO> findByMemberNo(Integer memberNo) {
		// TODO Auto-generated method stub
		List<ItineMemberVO> list = new ArrayList<ItineMemberVO>();
		ItineMemberVO itineMemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BYMEMBERNO_STMT);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				itineMemberVO = new ItineMemberVO();
				itineMemberVO.setItineNo(rs.getInt("itine_no"));
				itineMemberVO.setIsEditable(rs.getInt("iseditable"));
				list.add(itineMemberVO);
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

		ItineMemberJDBCDAO dao = new ItineMemberJDBCDAO();

		// 新增
//		ItineMemberVO itineMemberVO3 = new ItineMemberVO();
//		itineMemberVO3.setItineNo(3);
//		itineMemberVO3.setMemberNo(4);
//		itineMemberVO3.setIsEditable(0);
//		dao.add(itineMemberVO3);
//		System.out.println("新增成功");

//		// 更改
//		ItineMemberVO itineMemberVO2 = new ItineMemberVO();
//		itineMemberVO2.setItineNo(1);
//		itineMemberVO2.setMemberNo(4);
//		itineMemberVO2.setIsEditable(2);
//		dao.update(itineMemberVO2);
//		System.out.println("更改成功");

		// 用行程查團員有誰
		List<ItineMemberVO> list = dao.findByItineNo(1);
		for (ItineMemberVO itineMemberVO : list) {
			System.out.print(itineMemberVO.getMemberNo() + " ");
			System.out.print(itineMemberVO.getIsEditable() + " ");
			System.out.println();
		}
		System.out.println("=============");

		// 用會員查參加哪些行程
		List<ItineMemberVO> list1 = dao.findByMemberNo(1);
		for (ItineMemberVO itineMemberVO : list1) {
			System.out.print(itineMemberVO.getItineNo() + " ");
			System.out.print(itineMemberVO.getIsEditable() + " ");
			System.out.println();
		}
		System.out.println("=============");

	}
}
