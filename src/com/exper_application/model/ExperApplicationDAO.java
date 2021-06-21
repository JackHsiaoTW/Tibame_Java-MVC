package com.exper_application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ExperApplicationDAO implements ExperApplicationDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO Exper_Application (Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status, Exper_Appli_Memo)VALUES(?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT Exper_Appli_No, Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status, Exper_Appli_Memo FROM Exper_Application";
	private static final String GET_BY_MEMBER_NO_STMT = "SELECT Exper_Appli_No, Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status, Exper_Appli_Memo FROM Exper_Application where Member_No=?";
	private static final String GET_BY_EXPER_ORDER_NO_STMT = "SELECT Exper_Appli_No, Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status, Exper_Appli_Memo FROM Exper_Application where Exper_Order_No=?";
	private static final String GET_ONE_STMT = "SELECT Exper_Appli_No, Member_No, Exper_Order_No, Number, Sum, Exper_Appli_Status, Exper_Payment_Status, Exper_Appli_Memo FROM Exper_Application where Exper_Appli_No = ?";
	private static final String UPDATE = "UPDATE Exper_Application set Member_No=?, Exper_Order_No=?, Number=?, Sum=?, Exper_Appli_Status=?, Exper_Payment_Status=?, Exper_Appli_Memo=? where Exper_Appli_No=?";
	
	
	
	@Override
	public void insert(ExperApplicationVO expapVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, expapVO.getMember_no());
			pstmt.setInt(2, expapVO.getExper_order_no());
			pstmt.setInt(3, expapVO.getNumber());
			pstmt.setInt(4, expapVO.getSum());
			pstmt.setInt(5, expapVO.getExper_appli_status());
			pstmt.setInt(6, expapVO.getExper_payment_status());
			pstmt.setString(7, expapVO.getExper_appli_memo());


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
	public void update(ExperApplicationVO expapVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, expapVO.getMember_no());
			pstmt.setInt(2, expapVO.getExper_order_no());
			pstmt.setInt(3, expapVO.getNumber());
			pstmt.setInt(4, expapVO.getSum());
			pstmt.setInt(5, expapVO.getExper_appli_status());
			pstmt.setInt(6, expapVO.getExper_payment_status());
			pstmt.setString(7, expapVO.getExper_appli_memo());
			pstmt.setInt(8, expapVO.getExper_appli_no());
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
	public ExperApplicationVO findByPrimaryKey(Integer exper_appli_no) {
		ExperApplicationVO expapVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, exper_appli_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				expapVO = new ExperApplicationVO();
				expapVO.setExper_appli_no(rs.getInt("exper_appli_no"));
				expapVO.setMember_no(rs.getInt("member_no"));
				expapVO.setExper_order_no(rs.getInt("exper_order_no"));
				expapVO.setNumber(rs.getInt("number"));
				expapVO.setSum(rs.getInt("sum"));
				expapVO.setExper_appli_status(rs.getInt("exper_appli_status"));
				expapVO.setExper_payment_status(rs.getInt("exper_payment_status"));
				expapVO.setExper_appli_memo(rs.getString("exper_appli_memo"));
			}


			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return expapVO;
	}

	@Override
	public List<ExperApplicationVO> getAll() {
		List<ExperApplicationVO> list = new ArrayList<ExperApplicationVO>();
		ExperApplicationVO expapVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expapVO = new ExperApplicationVO();
				expapVO.setExper_appli_no(rs.getInt("exper_appli_no"));
				expapVO.setMember_no(rs.getInt("member_no"));
				expapVO.setExper_order_no(rs.getInt("exper_order_no"));
				expapVO.setNumber(rs.getInt("number"));
				expapVO.setSum(rs.getInt("sum"));
				expapVO.setExper_appli_status(rs.getInt("exper_appli_status"));
				expapVO.setExper_payment_status(rs.getInt("exper_payment_status"));
				expapVO.setExper_appli_memo(rs.getString("exper_appli_memo"));
				list.add(expapVO);
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
	public List<ExperApplicationVO> getExperAppliByExperOrderrNo(Integer exper_order_no) {
		List<ExperApplicationVO> list = new ArrayList<ExperApplicationVO>();
		ExperApplicationVO expapVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_EXPER_ORDER_NO_STMT);
			pstmt.setInt(1, exper_order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expapVO = new ExperApplicationVO();
				expapVO.setExper_appli_no(rs.getInt("exper_appli_no"));
				expapVO.setMember_no(rs.getInt("member_no"));
				expapVO.setExper_order_no(rs.getInt("exper_order_no"));
				expapVO.setNumber(rs.getInt("number"));
				expapVO.setSum(rs.getInt("sum"));
				expapVO.setExper_appli_status(rs.getInt("exper_appli_status"));
				expapVO.setExper_payment_status(rs.getInt("exper_payment_status"));
				expapVO.setExper_appli_memo(rs.getString("exper_appli_memo"));
				list.add(expapVO); // Store the row in the list
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
	public List<ExperApplicationVO> getExpAppliByMemberNo(Integer member_no) {
		List<ExperApplicationVO> list = new ArrayList<ExperApplicationVO>();
		ExperApplicationVO expapVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_MEMBER_NO_STMT);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expapVO = new ExperApplicationVO();
				expapVO.setExper_appli_no(rs.getInt("exper_appli_no"));
				expapVO.setMember_no(rs.getInt("member_no"));
				expapVO.setExper_order_no(rs.getInt("exper_order_no"));
				expapVO.setNumber(rs.getInt("number"));
				expapVO.setSum(rs.getInt("sum"));
				expapVO.setExper_appli_status(rs.getInt("exper_appli_status"));
				expapVO.setExper_payment_status(rs.getInt("exper_payment_status"));
				expapVO.setExper_appli_memo(rs.getString("exper_appli_memo"));
				list.add(expapVO); // Store the row in the list
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
	
	
//	@Override
//	public List<ExperApplicationVO> getExpAppliByHostNoAndDate(Integer host_no, String exper_start_date) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public static void main(String[] args) {
//		ExperApplicationJDBCDAO dao = new ExperApplicationJDBCDAO();
		// 新增 insert====================================
//		ExperApplicationVO expapVO = new ExperApplicationVO();
//		expapVO.setMember_no(1);
//		expapVO.setExper_order_no(1);
//		expapVO.setNumber(1);
//		expapVO.setSum(2000);
//		expapVO.setExper_appli_status(0);
//		expapVO.setExper_payment_status(0);
//		expapVO.setExper_appli_memo("你他媽到底會不會Java");	
//		dao.insert(expapVO);
		// 修改 update=====================================
//		ExperApplicationVO expapVO=new ExperApplicationVO();
//		expapVO.setMember_no(1);
//		expapVO.setExper_order_no(1);
//		expapVO.setNumber(1);
//		expapVO.setSum(2000);
//		expapVO.setExper_appli_status(0);
//		expapVO.setExper_payment_status(1);
//		expapVO.setExper_appli_memo("你他媽到底會不會Java");
//		expapVO.setExper_appli_no(2);
//		dao.update(expapVO);
		// 查詢 getAll====================================
//		List<ExperApplicationVO> list = dao.getAll();
//		for (ExperApplicationVO aExpap : list) {
//			System.out.print(aExpap.getExper_appli_no() + ",");
//			System.out.print(aExpap.getMember_no() + ",");
//			System.out.print(aExpap.getExper_order_no() + ",");
//			System.out.print(aExpap.getNumber() + ",");
//			System.out.print(aExpap.getSum() + ",");
//			System.out.print(aExpap.getExper_appli_status() + ",");
//			System.out.print(aExpap.getExper_payment_status()+ ",");
//			System.out.print(aExpap.getExper_appli_memo());
//			System.out.println();
//		}
		// 查詢 getByPK===================================
//		ExperApplicationVO expapVO = dao.findByPrimaryKey(2);
//			System.out.print(expapVO.getMember_no() + ",");
//			System.out.print(expapVO.getExper_order_no() + ",");
//			System.out.print(expapVO.getNumber() + ",");
//			System.out.print(expapVO.getSum() + ",");
//			System.out.print(expapVO.getExper_appli_status() + ",");
//			System.out.print(expapVO.getExper_payment_status()+ ",");
//			System.out.print(expapVO.getExper_appli_memo());
//			System.out.println("");
//
//
//
//
//	}


	
}
