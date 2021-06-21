package com.exper_type.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.experience.model.ExperienceVO;

public class ExperTypeDAO implements ExperTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO Exper_Type ( Exper_Type_Name) VALUES ( ?)";
	private static final String GET_ALL_STMT = "SELECT Exper_Type_No,Exper_Type_Name FROM Exper_Type order by Exper_Type_No";
	private static final String GET_ONE_STMT = "SELECT Exper_Type_No,Exper_Type_Name FROM Exper_Type where Exper_Type_No = ?";
	private static final String GET_Exper_ByExperType_STMT = "SELECT Exper_No, Host_No, Checker_No, Name, Price, Exper_Descr,"
			+ "Exper_Type_No FROM Experience where Exper_Type_No = ? order by Exper_No";
	private static final String UPDATE = "UPDATE Exper_Type set Exper_Type_Name=? where Exper_Type_No = ?";


	@Override
	public void insert(ExperTypeVO extypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, extypeVO.getExper_type_name());


			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Set<ExperienceVO> getExperByExperType(Integer exper_type_no) {
		Set<ExperienceVO> set = new LinkedHashSet<ExperienceVO>();
		ExperienceVO exVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_Exper_ByExperType_STMT);
			pstmt.setInt(1, exper_type_no);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				exVO = new ExperienceVO();
				exVO.setExper_no(rs.getInt("exper_no"));
				exVO.setHost_no(rs.getInt("host_no"));
				exVO.setExper_type_no(rs.getInt("exper_type_no"));
				exVO.setName(rs.getString("name"));
				exVO.setPrice(rs.getInt("price"));
				exVO.setExper_descr(rs.getString("ecper_descr"));
				exVO.setChecker_no(rs.getInt("checker_no"));
				exVO.setExper_status(rs.getInt("exper_status"));
				set.add(exVO); // Store the row in the vector
			}
	
			// Handle any driver errors
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
		return set;
	}

	@Override
	public void update(ExperTypeVO expertypeVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, expertypeVO.getExper_type_name());
			pstmt.setInt(2, expertypeVO.getExper_type_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ExperTypeVO findByPrimaryKey (Integer exper_type_no) {
		ExperTypeVO etVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, exper_type_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// etVO 也稱為 Domain objects
				etVO = new ExperTypeVO();
				etVO.setExper_type_no(rs.getInt("exper_type_no"));
				etVO.setExper_type_name(rs.getString("exper_type_name"));

			}

			// Handle any driver errors
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
		return etVO;
	}

	@Override
	public void delete(ExperTypeVO expertypeVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ExperTypeVO> getAll() {
		List<ExperTypeVO> list = new ArrayList<ExperTypeVO>();
		ExperTypeVO etVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				etVO = new ExperTypeVO();
				etVO.setExper_type_no(rs.getInt("exper_type_no"));
				etVO.setExper_type_name(rs.getString("exper_type_name"));
				list.add(etVO); // Store the row in the list
			}

			// Handle any driver errors
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
		ExperTypeJDBCDAO dao = new ExperTypeJDBCDAO();
		// 新增 ====================================
//		ExperTypeVO etVO = new ExperTypeVO();
//		etVO.setExper_type_name("一起跳海吧");
//		dao.insert(etVO);
		// ========================================
		// 修改=====================================
//		ExperTypeVO etVO2 = new ExperTypeVO();
//		etVO2.setExper_type_name("一起跳海吧");
//		etVO2.setExper_type_no(9);
//		dao.update(etVO2);
		// 刪除=====================================

		// 查詢by PK================================
//		ExperTypeVO etVO3 = dao.findByPrimaryKey(6);
//		System.out.println(etVO3.getExper_type_no());
//		System.out.println(etVO3.getExper_type_name());
//	
//		System.out.println("---------------------");
		// 查詢 Getall==============================
		List<ExperTypeVO> list = dao.getAll();
		for (ExperTypeVO etVO : list) {
			System.out.print(etVO.getExper_type_name());
			System.out.println();
		}

	}




}
