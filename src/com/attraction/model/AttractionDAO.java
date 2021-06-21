package com.attraction.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class AttractionDAO implements AttractionDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String GET_ALL_STMT="SELECT * FROM attraction";
	private static final String GET_SOME_UNCHECK_STMT = "SELECT * FROM attraction where is_on_shelf=0;";
	private static final String ADD_STMT = "INSERT INTO attraction (sort, attra_name, descr, location, is_on_shelf,attra_pic1) values (?,?,?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM attraction where attra_no = ?";
	private static final String UPDATE_STMT = "UPDATE attraction SET sort=?, attra_name=?, descr=?, location=?, is_on_shelf=?, attra_pic1=? where attra_no=?";
	private static final String GET_SEARCH_FOR_IS_ON_SHELF = "SELECT * FROM attraction WHERE sort=? and attra_name LIKE ? and is_on_shelf=1";
	private static final String GET_SEARCH_FOR = "SELECT * FROM attraction WHERE sort=? and attra_name LIKE ?";
	private static final String GET_NEWESTID_STMT ="select attra_no from attraction order by attra_no desc limit 1 ;";
	
	@Override
	public String add(AttractionVO attractionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			String cols[] = {"ATTRA_NO"};
			pstmt = con.prepareStatement(ADD_STMT,cols);
			
			pstmt.setString(1,attractionVO.getSort());
			pstmt.setString(2,attractionVO.getAttraName());
			pstmt.setString(3,attractionVO.getDescr());
			pstmt.setString(4, attractionVO.getLocation());
			pstmt.setInt(5, attractionVO.getIsOnShelf());
			pstmt.setString(6, attractionVO.getAttraPic1());
			pstmt.executeUpdate();
			
			//擷取自增主鍵
			String attraNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				attraNo = rs.getString(1);
			}
			return attraNo;
			
			// Handle any SQL errors
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
	public void update(AttractionVO attractionVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con= ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, attractionVO.getSort());
			pstmt.setString(2, attractionVO.getAttraName());
			pstmt.setString(3, attractionVO.getDescr());
			pstmt.setString(4, attractionVO.getLocation());
			pstmt.setInt(5, attractionVO.getIsOnShelf());
			pstmt.setString(6, attractionVO.getAttraPic1());
			pstmt.setInt(7, attractionVO.getAttraNo());
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
	public AttractionVO findByPK(int attraNo) {

		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
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
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCH_FOR);
			pstmt.setString(1, sort);
			pstmt.setString(2,"%"+searchfor+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setAttraNo(rs.getInt("attra_No"));
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setLocation(rs.getString("location"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				list.add(attraVO);
			}

			
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
	public List<AttractionVO> getSearchForIsOnShelf(String searchfor, String sort) {
		List<AttractionVO> list = new ArrayList<AttractionVO>();
		AttractionVO attraVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_SEARCH_FOR_IS_ON_SHELF);
			pstmt.setString(1, sort);
			pstmt.setString(2,"%"+searchfor+"%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				attraVO = new AttractionVO();
				attraVO.setAttraNo(rs.getInt("attra_No"));
				attraVO.setSort(rs.getString("sort"));
				attraVO.setAttraName(rs.getString("attra_name"));
				attraVO.setDescr(rs.getString("descr"));
				attraVO.setLocation(rs.getString("location"));
				attraVO.setIsOnShelf(rs.getInt("is_on_shelf"));
				attraVO.setAttraPic1(rs.getString("attra_pic1"));
				list.add(attraVO);
			}

			
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
	public int getNewestId() {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int attraNo;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEWESTID_STMT);
			rs = pstmt.executeQuery();

			rs.next();
			attraNo = rs.getInt("attra_no");
				
			

			
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
		
		
		return attraNo;
	}
	
	


	
	
}
