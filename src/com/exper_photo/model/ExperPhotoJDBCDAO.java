package com.exper_photo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.exper_type.model.ExperTypeJDBCDAO;
import com.exper_type.model.ExperTypeVO;
import com.experience.model.ExperienceJDBCDAO;



public class ExperPhotoJDBCDAO implements ExperPhotoDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO Exper_Photo (Exper_No, Photo) VALUES(?, ?)";
	private static final String UPDATE_STMT = "UPDATE Exper_Photo set Exper_No=?, Photo=? where Exper_Photo_No=?";
	private static final String DELETE_STMT = "DELETE FROM Exper_Photo where Exper_Photo_No = ?";
	private static final String GET_ONE_STMT = "SELECT Exper_Photo_No, Exper_No, Photo FROM Exper_Photo where Exper_Photo_No = ?";	
	private static final String GET_ALL_STMT = "SELECT Exper_Photo_no, Exper_No, Photo FROM Exper_Photo order by Exper_Photo_No";		
	private static final String GET_PHOTO_BY_EXPERNO="SELECT Exper_Photo_no, Exper_No, Photo FROM Exper_Photo where Exper_No=?";
	private static final String GET_EXPERPHOTO = "SELECT Photo FROM Exper_Photo where Exper_Photo_no = ?";
	private static final String UPDATE_PHOTO_BY_EXPER_PHOTO_NO = "UPDATE Exper_Photo set Photo=? where Exper_Photo_No=?";
	
	
	
	@Override
	public void insert(ExperPhotoVO expphoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setInt(1, expphoVO.getExper_no());
			pstmt.setBytes(2, expphoVO.getPhoto());
			
		
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public void update(ExperPhotoVO expphoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, expphoVO.getExper_no());
			pstmt.setBytes(2, expphoVO.getPhoto());
			pstmt.setInt(3, expphoVO.getExper_photo_no());
			
			pstmt.executeUpdate();


		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
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
	public void delete(Integer exper_photo_no) { 
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, exper_photo_no);
			pstmt.executeUpdate();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ExperPhotoVO findByPrimaryKey(Integer exper_photo_no) {
		ExperPhotoVO expphoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, exper_photo_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				expphoVO = new ExperPhotoVO();
				expphoVO.setExper_photo_no(rs.getInt("exper_photo_no"));
				expphoVO.setExper_no(rs.getInt("exper_no"));
				expphoVO.setPhoto(rs.getBytes("photo"));
			}


		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
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
		return expphoVO;
	}


	@Override
	public List<ExperPhotoVO> getAll() {
		List<ExperPhotoVO>list=new ArrayList<ExperPhotoVO>();
		ExperPhotoVO expphoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expphoVO = new ExperPhotoVO();
				expphoVO.setExper_photo_no(rs.getInt("exper_photo_no"));
				expphoVO.setExper_no(rs.getInt("exper_no"));
				expphoVO.setPhoto(rs.getBytes("photo"));
			
				list.add(expphoVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public List<ExperPhotoVO> getPhotoByExperNo(Integer exper_no) {
		List<ExperPhotoVO>list=new ArrayList<ExperPhotoVO>();
		ExperPhotoVO expphoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PHOTO_BY_EXPERNO);
			pstmt.setInt(1, exper_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				expphoVO = new ExperPhotoVO();
				expphoVO.setExper_photo_no(rs.getInt("exper_photo_no"));
				expphoVO.setExper_no(rs.getInt("exper_no"));
				expphoVO.setPhoto(rs.getBytes("photo"));
		
				list.add(expphoVO);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	
	
	public byte[] getExperPhoto(Integer exper_photo_no){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] photo = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EXPERPHOTO);
			
			pstmt.setInt(1, exper_photo_no);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				photo = rs.getBytes("photo");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();	
		}catch(SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return photo;
	}

	
	
public static void main(String[] args) {
	ExperPhotoJDBCDAO dao = new ExperPhotoJDBCDAO();
	// 新增 ====================================
//	ExperPhotoVO etVO = new ExperPhotoVO();
//	etVO.setExper_no(2);
//	dao.insert(etVO);
	// ========================================
	// 修改=====================================
//	ExperTypeVO etVO2 = new ExperTypeVO();
//	etVO2.setExper_type_name("一起跳海吧");
//	etVO2.setExper_type_no(5);
//	dao.update(etVO2);

	// 查詢by PK================================
//	ExperTypeVO etVO3 = dao.findByPrimaryKey(6);
//	System.out.println(etVO3.getExper_type_no());
//	System.out.println(etVO3.getExper_type_name());
//
//	System.out.println("---------------------");
	// 查詢 Getall==============================
//	List<ExperTypeVO> list = dao.getAll();
//	for (ExperTypeVO etVO : list) {
//		System.out.print(etVO.getExper_type_name());
//		System.out.println();
//	}

}

@Override
public void update_photo_by_exper_photo_no(ExperPhotoVO expphoVO) {
	// TODO Auto-generated method stub
	
}

}
