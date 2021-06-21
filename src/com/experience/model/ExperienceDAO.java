package com.experience.model;

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

public class ExperienceDAO implements ExperienceDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Experience (Host_No, Name, Price, Exper_Descr, Exper_Type_No)"
			+ "VALUES( ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT Exper_No, Host_No, Checker_No, Name, Price, Exper_Descr, Exper_Status, Exper_Type_No FROM Experience order by Exper_No";
	private static final String GET_ONE_STMT = "SELECT Exper_No, Host_No, Checker_No, Name, Price, Exper_Descr, Exper_Status, Exper_Type_No FROM Experience where Exper_No = ?";
    private static final String GET_ALL_BY_HOST_NO = "SELECT Exper_No, Host_No, Checker_No, Name, Price, Exper_Descr, Exper_Status, Exper_Type_No FROM Experience where Host_No = ?";
	private static final String DELETE = "DELETE FROM Experience where Exper_No = ?";
	private static final String UPDATE = "UPDATE Experience set Host_No =?, Checker_No=?, Name=?, Price=?, Exper_Descr=?, Exper_Status=?, Exper_Type_No=? where Exper_No = ?";
	@Override
	public void insert(ExperienceVO experVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, experVO.getHost_no());;
			pstmt.setString(2, experVO.getName());
			pstmt.setInt(3, experVO.getPrice());
			pstmt.setString(4, experVO.getExper_descr());
			pstmt.setInt(5, experVO.getExper_type_no());
			
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
	public void update(ExperienceVO experVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, experVO.getHost_no());
			pstmt.setInt(2, experVO.getChecker_no());
			pstmt.setString(3, experVO.getName());
			pstmt.setInt(4, experVO.getPrice());
			pstmt.setString(5,experVO.getExper_descr());
			pstmt.setInt(6, experVO.getExper_status());
			pstmt.setInt(7, experVO.getExper_type_no());
			pstmt.setInt(8, experVO.getExper_no());
			
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
	public ExperienceVO findByPrimaryKey(Integer exper_no) {
		ExperienceVO experVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, exper_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				experVO = new ExperienceVO();
				experVO.setExper_no(rs.getInt("exper_no"));
				experVO.setHost_no(rs.getInt("host_no"));
				experVO.setChecker_no(rs.getInt("checker_no"));				
				experVO.setName(rs.getString("name"));
				experVO.setPrice(rs.getInt("price"));
				experVO.setExper_descr(rs.getString("exper_descr"));
				experVO.setExper_status(rs.getInt("exper_status"));
				experVO.setExper_type_no(rs.getInt("exper_type_no"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return experVO;
	}

	@Override
	public void delete(Integer exper_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, exper_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ExperienceVO> getAllbyHostNo(Integer host_no) {
		List<ExperienceVO> list = new ArrayList<ExperienceVO>();
		ExperienceVO experVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_HOST_NO);
			pstmt.setInt(1, host_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				experVO = new ExperienceVO();
				experVO.setExper_no(rs.getInt("exper_no"));
				experVO.setHost_no(rs.getInt("host_no"));
				experVO.setChecker_no(rs.getInt("checker_no"));				
				experVO.setName(rs.getString("name"));
				experVO.setPrice(rs.getInt("price"));
				experVO.setExper_descr(rs.getString("exper_descr"));
				experVO.setExper_status(rs.getInt("exper_status"));
				experVO.setExper_type_no(rs.getInt("exper_type_no"));
				list.add(experVO); // Store the row in the list
			}


			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ExperienceVO> getAll() {
		List<ExperienceVO> list = new ArrayList<ExperienceVO>();
		ExperienceVO experVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				experVO = new ExperienceVO();
				experVO.setExper_no(rs.getInt("exper_no"));
				experVO.setHost_no(rs.getInt("host_no"));
				experVO.setChecker_no(rs.getInt("checker_no"));				
				experVO.setName(rs.getString("name"));
				experVO.setPrice(rs.getInt("price"));
				experVO.setExper_descr(rs.getString("exper_descr"));
				experVO.setExper_status(rs.getInt("exper_status"));
				experVO.setExper_type_no(rs.getInt("exper_type_no"));
				list.add(experVO); // Store the row in the list
			}


			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		ExperienceJDBCDAO dao=new ExperienceJDBCDAO();
		//新增=======================================================
//			ExperienceVO experVO = new ExperienceVO();
//			experVO.setHost_no(13);
//			experVO.setChecker_no(1);
//			experVO.setName("一起揍吉娃娃");
//			experVO.setPrice(2880);
//			experVO.setExper_descr("記得帶狼牙棒");
//			experVO.setExper_status(1);
//			experVO.setExper_type_no(4);
//			
//			dao.insert(experVO);
		//修改 update================================================
//		ExperienceVO experVO = new ExperienceVO();
//		experVO.setHost_no(13);
//		experVO.setChecker_no(1);
//		experVO.setName("一起揍吉娃娃");
//		experVO.setPrice(2880);
//		experVO.setExper_descr("記得帶狼牙棒");
//		experVO.setExper_status(1);
//		experVO.setExper_type_no(4);
//		
//		dao.insert(experVO);
		//查詢 getAll================================================
//		List<ExperienceVO> list = dao.getAll();
//		for (ExperienceVO aExp : list) {
//			System.out.print(aExp.getExper_no() + ",");
//			System.out.print(aExp.getHost_no() + ",");
//			System.out.print(aExp.getChecker_no() + ",");
//			System.out.print(aExp.getName() + ",");
//			System.out.print(aExp.getPrice() + ",");
//			System.out.print(aExp.getExper_descr() + ",");
//			System.out.print(aExp.getExper_status() + ",");
//			System.out.print(aExp.getExper_type_no());
//			System.out.println();
//		}
		//查詢getByPK=============================================
//		ExperienceVO experVO3 = dao.findByPrimaryKey(3);
//		System.out.print(experVO3.getExper_no() + ",");
//		System.out.print(experVO3.getHost_no() + ",");
//		System.out.print(experVO3.getChecker_no() + ",");
//		System.out.print(experVO3.getName() + ",");
//		System.out.print(experVO3.getPrice() + ",");
//		System.out.print(experVO3.getExper_descr() + ",");
//		System.out.print(experVO3.getExper_status() + ",");
//		System.out.print(experVO3.getExper_type_no());
//		System.out.println();
		//查詢getBySell
//		List<ExperienceVO> list = dao.getAllbyHostNo(1);
//		for (ExperienceVO aExp : list) {
//			System.out.print(aExp.getExper_no() + ",");
//			System.out.print(aExp.getHost_no() + ",");
//			System.out.print(aExp.getChecker_no() + ",");
//			System.out.print(aExp.getName() + ",");
//			System.out.print(aExp.getPrice() + ",");
//			System.out.print(aExp.getExper_descr() + ",");
//			System.out.print(aExp.getExper_status() + ",");
//			System.out.print(aExp.getExper_type_no());
//			System.out.println();
//		}
		
	}
	
	
}
