package com.attraction.model;

import java.util.*;
import java.sql.*;

public class AttractionJDBCDAO implements AttractionDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/guideme?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1qaz2wsx3edc";

	private static final String GET_ALL_STMT = "SELECT * FROM attraction;";
	private static final String GET_SOME_UNCHECK_STMT = "SELECT * FROM attraction where is_on_shelf=0;";
	private static final String ADD_STMT = "INSERT INTO attraction (sort, attra_name, descr, location, is_on_shelf,attra_pic1) values (?,?,?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM attraction where attra_no = ?";
	private static final String UPDATE_STMT = "UPDATE attraction SET sort=?, attra_name=?, descr=?, location=?, is_on_shelf=?, attra_pic1=? where attra_no=?";
	private static final String GET_SEARCH_FOR = "SELECT * from attraction WHERE sort=? and attra_name LIKE ? and is_on_shelf=1";
	
	@Override
	public String add(AttractionVO attractionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String[] cols= {"ATTRA_NO"};
			pstmt = con.prepareStatement(ADD_STMT,cols);

			pstmt.setString(1, attractionVO.getSort());
			pstmt.setString(2, attractionVO.getAttraName());
			pstmt.setString(3, attractionVO.getDescr());
			pstmt.setString(4, attractionVO.getLocation());
			pstmt.setInt(5, attractionVO.getIsOnShelf());
			pstmt.setString(6, attractionVO.getAttraPic1());
			pstmt.executeUpdate();
			
			String attraNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				attraNo =rs.getString(1);
			}
			return attraNo;
			

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
	public void update(AttractionVO attractionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, attractionVO.getSort());
			pstmt.setString(2, attractionVO.getAttraName());
			pstmt.setString(3, attractionVO.getDescr());
			pstmt.setString(4, attractionVO.getLocation());
			pstmt.setInt(5, attractionVO.getIsOnShelf());
			pstmt.setString(6, attractionVO.getAttraPic1());
			pstmt.setInt(7, attractionVO.getAttraNo());
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
	public AttractionVO findByPK(int attraNo) {

		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, attraNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setAttraNo(rs.getInt("attra_no"));
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				attraVO.setLocation(rs.getString("location"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return attraVO;
	}

	@Override
	public List<AttractionVO> findByName(String attraName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttractionVO> getAll() {
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setAttraNo(rs.getInt("attra_no"));
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setLocation(rs.getString("location"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				list.add(attraVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<AttractionVO> getUnchecked() {
		// TODO Auto-generated method stub
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SOME_UNCHECK_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setAttraNo(rs.getInt("attra_no"));
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setLocation(rs.getString("location"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				list.add(attraVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	
	@Override
	public List<AttractionVO> getSearchFor(String searchfor, String sort) {
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SEARCH_FOR);
			pstmt.setString(1, sort);
			pstmt.setString(2,"%"+searchfor+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setLocation(rs.getString("location"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				list.add(attraVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		AttractionJDBCDAO dao = new AttractionJDBCDAO();

//		// 查全部
//		List<AttractionVO> list = dao.getAll();
//		for (AttractionVO aAttraVO : list) {
//			System.out.print(aAttraVO.getAttraNo());
//			System.out.print(aAttraVO.getSort());
//			System.out.print(aAttraVO.getAttraName());
//			System.out.print(aAttraVO.getDescr());
//			System.out.print(aAttraVO.getLocation());
//			System.out.print(aAttraVO.getIsOnShelf());
//			System.out.print(aAttraVO.getAttraPic1());
//			System.out.println();
//		}
//		System.out.println("====================");
//
//		// 查未上架
//		List<AttractionVO> uncheckedList = dao.getUnchecked();
//		for (AttractionVO aAttraVO : uncheckedList) {
//			System.out.print(aAttraVO.getAttraNo());
//			System.out.print(aAttraVO.getSort());
//			System.out.print(aAttraVO.getAttraName());
//			System.out.print(aAttraVO.getDescr());
//			System.out.print(aAttraVO.getLocation());
//			System.out.print(aAttraVO.getIsOnShelf());
//			System.out.print(aAttraVO.getAttraPic1());
//			System.out.println();
//		}
//		System.out.println("====================");

		// 新增一筆資料
		AttractionVO attractionVO1 = new AttractionVO();
		attractionVO1.setSort("景點");
		attractionVO1.setAttraName("台灣穀堡");
		attractionVO1.setDescr("種米的地方");
		attractionVO1.setLocation("埤頭");
		attractionVO1.setIsOnShelf(0);
		attractionVO1.setAttraPic1("");
		String attraNo = dao.add(attractionVO1);
		System.out.println("新增成功: 主鍵為:" + attraNo);
		System.out.println("====================");

//		// 查詢一筆資料
//
//		AttractionVO attraVO2 = dao.findByPK(3);
//
//		System.out.print(attraVO2.getAttraNo());
//		System.out.print(attraVO2.getSort());
//		System.out.print(attraVO2.getAttraName());
//		System.out.print(attraVO2.getDescr());
//		System.out.print(attraVO2.getLocation());
//		System.out.print(attraVO2.getIsOnShelf());
//		System.out.print(attraVO2.getAttraPic1());
//		System.out.println();
//		System.out.println("====================");
//
//		// 修改一筆資料
//		AttractionVO attractionVO2 = new AttractionVO();
//		attractionVO2.setSort("景點");
//		attractionVO2.setAttraName("中興穀堡");
//		attractionVO2.setDescr("種稻米的地方");
//		attractionVO2.setLocation("埤頭鄉");
//		attractionVO2.setIsOnShelf(0);
//		attractionVO2.setAttraPic1("");
//		attractionVO2.setAttraNo(1);
//		dao.update(attractionVO2);
//		System.out.println("修改成功");
//		System.out.println("====================");
//		
//		//用景點名查詢多筆資料
//		List<AttractionVO> searchForList = dao.getSearchFor("公園","景點");
//		for(AttractionVO attractionVO3: searchForList) {
//			System.out.print(attractionVO3.getSort());
//			System.out.print(attractionVO3.getAttraName());
//			System.out.print(attractionVO3.getDescr());
//			System.out.print(attractionVO3.getLocation());
//			System.out.print(attractionVO3.getIsOnShelf());
//			System.out.print(attractionVO3.getAttraPic1());
//			System.out.println();
//		}
//		System.out.println("=====================");
		
		
	}

	@Override
	public int getNewestId() {
		// TODO Auto-generated method stub
		int a = 0;
		return a;
	}

	@Override
	public List<AttractionVO> getSearchForIsOnShelf(String searchfor, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
