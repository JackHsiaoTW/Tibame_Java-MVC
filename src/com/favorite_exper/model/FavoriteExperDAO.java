package com.favorite_exper.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class FavoriteExperDAO implements FavoriteExperDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO Favorite_Exper (Member_No,Exper_No) VALUES ( ?, ?)";
	private static final String DELETE = 
			"DELETE FROM Favorite_Exper where Exper_No = ? ";
	private static final String GET_ALL_STMT = 
		"SELECT Exper_No,Member_No FROM Favorite_Exper order by Exper_No";
	private static final String GET_ONE_BY_EXPERNO = 
		"SELECT Exper_No,Member_No FROM Favorite_Exper where Exper_No = ?";
	private static final String GET_ONE_BY_MEMBERNO = 
			"SELECT Exper_No,Member_No FROM Favorite_Exper where Member_No = ?";
	private static final String GET_COUNT_FAVOR =
			"SELECT COUNT(Exper_No) AS collect FROM Favorite_Exper where Exper_No =?";
	

	@Override
	public void insert(FavoriteExperVO favorexpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, favorexpVO.getMember_no());
			pstmt.setInt(2, favorexpVO.getExper_no());
			

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
	public void insert(Integer member_no, Integer exper_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, member_no);
			pstmt.setInt(2, exper_no);
			

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
	public List<Integer> findByMemberNo(Integer member_no) {
		List<Integer> list = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_MEMBERNO);

			pstmt.setInt(1, member_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				list.add(rs.getInt("exper_no"));
							
			}

			// Handle any driver errors
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
				

		return list;
	}
	@Override
	public void delete(Integer member_no, Integer exper_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, member_no);
			pstmt.setInt(2, exper_no);
			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public List<Integer> findByExperNo(Integer exper_no) {
		List<Integer> list = new ArrayList<Integer>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EXPERNO);

			pstmt.setInt(1, exper_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
				list.add(rs.getInt("member_no"));
							
			}

			// Handle any driver errors
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
				

		return list;
	}
	
	
	@Override
	public List<FavoriteExperVO> getAll() {
		List<FavoriteExperVO> list = new ArrayList<FavoriteExperVO>();
		FavoriteExperVO favorexpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				favorexpVO = new FavoriteExperVO();
				favorexpVO.setExper_no(rs.getInt("Exper_no"));
				favorexpVO.setMember_no(rs.getInt("Member_no"));
				list.add(favorexpVO);
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
		return list;
	}
	@Override
	public int countFavor(Integer exper_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int collect =0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNT_FAVOR);
			pstmt.setInt(1, exper_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				collect = rs.getInt("collect");
			}

			// Handle any driver errors
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
		return collect;
	}


	
}