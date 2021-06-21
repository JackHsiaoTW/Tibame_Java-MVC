package com.exper_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.exper_order.model.ExperOrderVO;

import util.*;


public class ExperOrderDAO implements ExperOrderDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}
		
	private static final String INSERT_STMT = "INSERT INTO Exper_Order(Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT Exper_Order_No, Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum FROM Exper_Order order by Exper_Order_No";
	private static final String GET_ONE_STMT = "SELECT Exper_Order_No, Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum FROM Exper_Order where Exper_Order_No = ?";
	private static final String GET_ALL_BY_EXPER_NO = "SELECT Exper_Order_No, Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum FROM Exper_Order where Exper_No = ?";
	private static final String GET_LIST_BY_EXPER_ORDER_START = "SELECT Exper_Order_No, Exper_No, Apply_Start, Apply_End, Exper_Order_Start, Exper_Order_End, Exper_Max_Limit, Exper_Min_Limit, Exper_Now_Price, Exper_Order_Status, Exper_Apply_Sum FROM Exper_Order WHERE Exper_Order_Start> str_to_date(?, '%Y-%m-%d %H:%i:%s') and Exper_Order_Start<str_to_date(?, '%Y-%m-%d %H:%i:%s')";
	private static final String UPDATE = "UPDATE  Exper_Order set Exper_No=?, Apply_Start=?, Apply_End=?, Exper_Order_Start=?, Exper_Order_End=?, Exper_Max_Limit=?, Exper_Min_Limit=?, Exper_Now_Price=?, Exper_Order_Status=?, Exper_Apply_Sum=? where Exper_Order_No=?";
	private static final String UPDATE_EXPERORDER_STATUS = "UPDATE  Exper_Order set Exper_Order_Status=? where Exper_Order_No =?";

@Override
public void insert(ExperOrderVO expordVO) {
	Connection con = null;
	PreparedStatement pstmt = null;
	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		pstmt.setInt(1, expordVO.getExper_no());
		pstmt.setTimestamp(2, expordVO.getApply_start());
		pstmt.setTimestamp(3, expordVO.getApply_end());
		pstmt.setTimestamp(4, expordVO.getExper_order_start());
		pstmt.setTimestamp(5, expordVO.getExper_order_end());
		pstmt.setInt(6, expordVO.getExper_max_limit());
		pstmt.setInt(7, expordVO.getExper_min_limit());
		pstmt.setInt(8, expordVO.getExper_now_price());
		pstmt.setInt(9, expordVO.getExper_order_status());
		pstmt.setInt(10, expordVO.getExper_apply_sum());
		
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

public List<ExperOrderVO> getListByExperOrderStart(String exper_order_status_on_date) {
	List<ExperOrderVO>list=new ArrayList<ExperOrderVO>();
	ExperOrderVO expordVO=null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;                                                        
	long fromToLong = java.sql.Timestamp.valueOf(exper_order_status_on_date+" 00:00:00.0").getTime();
	long fromToLong2 = java.sql.Timestamp.valueOf(exper_order_status_on_date+" 23:59:59.0").getTime();
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_LIST_BY_EXPER_ORDER_START);
		String dayStart=new Timestamp(fromToLong).toString().replace(".0", "");
		String dayEnd=new Timestamp(fromToLong2).toString().replace(".0", "");
		pstmt.setString(1, dayStart);
		pstmt.setString(2, dayEnd);
		
		
		rs=pstmt.executeQuery();
		while(rs.next()) {
			expordVO=new ExperOrderVO();
			expordVO.setExper_order_no(rs.getInt("exper_order_no"));
			expordVO.setExper_no(rs.getInt("exper_no"));
			expordVO.setApply_start(rs.getTimestamp("apply_start"));
			expordVO.setApply_end(rs.getTimestamp("apply_end"));
			expordVO.setExper_order_start(rs.getTimestamp("exper_order_start"));
			expordVO.setExper_order_end(rs.getTimestamp("exper_order_end"));
			expordVO.setExper_max_limit(rs.getInt("exper_max_limit"));
			expordVO.setExper_min_limit(rs.getInt("exper_min_limit"));
			expordVO.setExper_now_price(rs.getInt("exper_now_price"));
			expordVO.setExper_order_status(rs.getInt("exper_order_status"));
			expordVO.setExper_apply_sum(rs.getInt("exper_apply_sum"));
			list.add(expordVO);
		}
		
		

		
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
	return list;
}

@Override
public List<ExperOrderVO> getAllExperPerByExperNo(Integer exper_no) {
	List<ExperOrderVO>list=new ArrayList<ExperOrderVO>();
	ExperOrderVO expordVO=null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_BY_EXPER_NO);
		pstmt.setInt(1, exper_no);
		
		rs=pstmt.executeQuery();
		while(rs.next()) {
			expordVO=new ExperOrderVO();
			expordVO.setExper_order_no(rs.getInt("exper_order_no"));
			expordVO.setExper_no(rs.getInt("exper_no"));
			expordVO.setApply_start(rs.getTimestamp("apply_start"));
			expordVO.setApply_end(rs.getTimestamp("apply_end"));
			expordVO.setExper_order_start(rs.getTimestamp("exper_order_start"));
			expordVO.setExper_order_end(rs.getTimestamp("exper_order_end"));
			expordVO.setExper_max_limit(rs.getInt("exper_max_limit"));
			expordVO.setExper_min_limit(rs.getInt("exper_min_limit"));
			expordVO.setExper_now_price(rs.getInt("exper_now_price"));
			expordVO.setExper_order_status(rs.getInt("exper_order_status"));
			expordVO.setExper_apply_sum(rs.getInt("exper_apply_sum"));
			list.add(expordVO);
		}
		
		

		
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
	return list;
}

	

@Override
public void update(ExperOrderVO expordVO) {
	Connection con = null;
	PreparedStatement pstmt = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);
		
		pstmt.setInt(1, expordVO.getExper_no());
		pstmt.setTimestamp(2, expordVO.getApply_start());
		pstmt.setTimestamp(3, expordVO.getApply_end());
		pstmt.setTimestamp(4, expordVO.getExper_order_start());
		pstmt.setTimestamp(5, expordVO.getExper_order_end());
		pstmt.setInt(6, expordVO.getExper_max_limit());
		pstmt.setInt(7, expordVO.getExper_min_limit());
		pstmt.setInt(8, expordVO.getExper_now_price());
		pstmt.setInt(9, expordVO.getExper_order_status());
		pstmt.setInt(10, expordVO.getExper_apply_sum());
		pstmt.setInt(11, expordVO.getExper_order_no());


		pstmt.executeUpdate();

		
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
public ExperOrderVO findByPrimaryKey(Integer exper_order_no) {
	ExperOrderVO expordVO=null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);
		pstmt.setInt(1, exper_order_no);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			expordVO=new ExperOrderVO();
			expordVO.setExper_order_no(rs.getInt("exper_order_no"));
			expordVO.setExper_no(rs.getInt("exper_no"));
			expordVO.setApply_start(rs.getTimestamp("apply_start"));
			expordVO.setApply_end(rs.getTimestamp("apply_end"));
			expordVO.setExper_order_start(rs.getTimestamp("exper_order_start"));
			expordVO.setExper_order_end(rs.getTimestamp("exper_order_end"));
			expordVO.setExper_max_limit(rs.getInt("exper_max_limit"));
			expordVO.setExper_min_limit(rs.getInt("exper_min_limit"));
			expordVO.setExper_now_price(rs.getInt("exper_now_price"));
			expordVO.setExper_order_status(rs.getInt("exper_order_status"));
			expordVO.setExper_apply_sum(rs.getInt("exper_apply_sum"));
			
		}
		
		

	
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
	return expordVO;
}
@Override
public void upDateExperPerStatus(Integer exper_order_no, Integer exper_order_status)  {
	ExperOrderVO expordVO =null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs =null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_EXPERORDER_STATUS);
		pstmt.setInt(1, exper_order_status);
		pstmt.setInt(2, exper_order_no);
		pstmt.executeUpdate();
		
			
	
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
public List<ExperOrderVO> getAll() {
	List<ExperOrderVO>list=new ArrayList<ExperOrderVO>();
	ExperOrderVO expordVO=null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		
		rs=pstmt.executeQuery();
		while(rs.next()) {
			expordVO=new ExperOrderVO();
			expordVO.setExper_order_no(rs.getInt("exper_order_no"));
			expordVO.setExper_no(rs.getInt("exper_no"));
			expordVO.setApply_start(rs.getTimestamp("apply_start"));
			expordVO.setApply_end(rs.getTimestamp("apply_end"));
			expordVO.setExper_order_start(rs.getTimestamp("exper_order_start"));
			expordVO.setExper_order_end(rs.getTimestamp("exper_order_end"));
			expordVO.setExper_max_limit(rs.getInt("exper_max_limit"));
			expordVO.setExper_min_limit(rs.getInt("exper_min_limit"));
			expordVO.setExper_now_price(rs.getInt("exper_now_price"));
			expordVO.setExper_order_status(rs.getInt("exper_order_status"));
			expordVO.setExper_apply_sum(rs.getInt("exper_apply_sum"));
			list.add(expordVO);
		}
		
		

	
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
	return list;
}



@Override
public void delete(Integer exper_order_no) {
	// TODO Auto-generated method stub
	
}

@Override
public List<ExperOrderVO> getAll(Map<String, String[]> map) {
	List<ExperOrderVO> list = new ArrayList<ExperOrderVO>();
	ExperOrderVO expordVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		
		con = ds.getConnection();
		String finalSQL = "SELECT Exper_Order_No,EO.Exper_No,Apply_Start,Apply_End,Exper_Order_Start,"
				+ "Exper_Order_End,Exper_Max_Limit,Exper_Min_Limit,Exper_Now_Price,Exper_Order_Status,"
				+ "Exper_Apply_Sum,Name,Exper_Descr,ETY.Exper_Type_No,Exper_Type_Name" + 
				" FROM Exper_Order EO" + 
				" JOIN Experience EX ON EO.Exper_No=EO.Exper_No" + 
				" JOIN Exper_Type ETY ON ETY.Exper_Type_No = EX.Exper_Type_No" + 
	           jdbcUtil_CompositeQuery_Exper_Order.get_WhereCondition(map)
	          + " ORDER BY Exper_Order_No";
		pstmt = con.prepareStatement(finalSQL);
		System.out.println("●●finalSQL(by DAO) = "+finalSQL);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			expordVO=new ExperOrderVO();
			expordVO.setExper_order_no(rs.getInt("exper_order_no"));
			expordVO.setExper_no(rs.getInt("exper_no"));
			expordVO.setApply_start(rs.getTimestamp("apply_start"));
			expordVO.setApply_end(rs.getTimestamp("apply_end"));
			expordVO.setExper_order_start(rs.getTimestamp("exper_order_start"));
			expordVO.setExper_order_end(rs.getTimestamp("exper_order_end"));
			expordVO.setExper_max_limit(rs.getInt("exper_max_limit"));
			expordVO.setExper_min_limit(rs.getInt("exper_min_limit"));
			expordVO.setExper_now_price(rs.getInt("exper_now_price"));
			expordVO.setExper_order_status(rs.getInt("exper_order_status"));
			expordVO.setExper_apply_sum(rs.getInt("exper_apply_sum"));
			list.add(expordVO);
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
	ExperOrderJDBCDAO dao=new ExperOrderJDBCDAO();
	// 新增 ====================================
//	ExperOrderVO expordVO = new ExperOrderVO();
//	expordVO.setExper_no(1);
//	expordVO.setApply_start(java.sql.Timestamp.valueOf("2021-03-11 12:00:00"));
//	expordVO.setApply_end(java.sql.Timestamp.valueOf("2021-03-13 12:00:00"));
//	expordVO.setExper_order_start(java.sql.Timestamp.valueOf("2021-03-14 12:00:00"));
//	expordVO.setExper_order_end(java.sql.Timestamp.valueOf("2021-03-14 13:00:00"));
//	expordVO.setExper_max_limit(20);
//	expordVO.setExper_min_limit(3);
//	expordVO.setExper_now_price(1500);
//	expordVO.setExper_order_status(2);
//	expordVO.setExper_apply_sum(15);
//	dao.insert(expordVO);
//	//修改活動期別狀態=============================
//	dao.upDateExperPerStatus(1, 2);
	//修改=====================================
//	ExperOrderVO expordVO2 = new ExperOrderVO();
//	expordVO2.setExper_no(1);
//	expordVO2.setApply_start(java.sql.Timestamp.valueOf("2021-03-11 12:00:00"));
//	expordVO2.setApply_end(java.sql.Timestamp.valueOf("2021-03-13 12:00:00"));
//	expordVO2.setExper_order_start(java.sql.Timestamp.valueOf("2021-03-14 12:00:00"));
//	expordVO2.setExper_order_end(java.sql.Timestamp.valueOf("2021-03-14 13:00:00"));
//	expordVO2.setExper_max_limit(20);
//	expordVO2.setExper_min_limit(3);
//	expordVO2.setExper_now_price(1500);
//	expordVO2.setExper_order_status(2);
//	expordVO2.setExper_apply_sum(15);
//	expordVO2.setExper_order_no(11);
//	dao.update(expordVO2);
	//查詢 getByPK=============================
//	ExperOrderVO expordVO3 = dao.findByPrimaryKey(2);
//	System.out.println(expordVO3.getExper_no());
//	System.out.println(expordVO3.getApply_start());
//	System.out.println(expordVO3.getApply_end());
//	System.out.println(expordVO3.getExper_order_start());
//	System.out.println(expordVO3.getExper_order_end());
//	System.out.println(expordVO3.getExper_max_limit());
//	System.out.println(expordVO3.getExper_min_limit());
//	System.out.println(expordVO3.getExper_now_price());
//	System.out.println(expordVO3.getExper_order_status());
//	System.out.println(expordVO3.getExper_apply_sum());
//	//查詢 getAll================================
//	List<ExperOrderVO>list=dao.getAll();
//	for(ExperOrderVO aExpOrd:list) {
//		
//		System.out.println(aExpOrd.getExper_no());
//		System.out.println(aExpOrd.getApply_start());
//		System.out.println(aExpOrd.getApply_end());
//		System.out.println(aExpOrd.getExper_order_start());
//		System.out.println(aExpOrd.getExper_order_end());
//		System.out.println(aExpOrd.getExper_max_limit());
//		System.out.println(aExpOrd.getExper_min_limit());
//		System.out.println(aExpOrd.getExper_now_price());
//		System.out.println(aExpOrd.getExper_order_status());
//		System.out.println(aExpOrd.getExper_apply_sum());
//		System.out.println("=============================");
//	}
	
//	List<ExperOrderVO>list=dao.getListByExperOrderStart("2021-02-18");
//	for(ExperOrderVO aExpOrd:list) {
//		
//		System.out.println(aExpOrd.getExper_no());
//		System.out.println(aExpOrd.getApply_start());
//		System.out.println(aExpOrd.getApply_end());
//		System.out.println(aExpOrd.getExper_order_start());
//		System.out.println(aExpOrd.getExper_order_end());
//		System.out.println(aExpOrd.getExper_max_limit());
//		System.out.println(aExpOrd.getExper_min_limit());
//		System.out.println(aExpOrd.getExper_now_price());
//		System.out.println(aExpOrd.getExper_order_status());
//		System.out.println(aExpOrd.getExper_apply_sum());
//		System.out.println("=============================");
//	}
	
	
	
	
	
}







}
