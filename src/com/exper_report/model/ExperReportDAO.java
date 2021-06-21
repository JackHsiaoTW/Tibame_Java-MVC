package com.exper_report.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ExperReportDAO implements ExperReportDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
		e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Exper_Report (Reporter_No, Reported_Exper_No, Reason) VALUES (?,?,?)";
	private static final String UPDATE = "UPDATE Exper_Report SET Reporter_No =?, Reported_Exper_No=?, Reason=?, Reply_Content=?, Is_Checked=?, Reply_Time=CURRENT_TIMESTAMP WHERE Report_No=?";
	private static final String GET_ONE_STMT = "SELECT Report_No, Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_time, Is_Checked FROM Exper_Report WHERE Report_No= ?";
	private static final String GET_ALL_STMT = "SELECT Report_No, Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_time, Is_Checked FROM Exper_Report order by Report_No";
	private static final String GET_REPORT_BY_EXPERNO = "SELECT Report_No, Reporter_No, Reported_Exper_No, Reason, Report_Time, Reply_Content, Reply_time, Is_Checked FROM Exper_Report WHERE Reported_Exper_No= ? order by  Report_No";

	@Override
	public void insert(ExperReportVO erVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, erVO.getReporter_no());
			pstmt.setInt(2, erVO.getReported_exper_no());
			pstmt.setString(3, erVO.getReason());
		

			pstmt.executeUpdate();

		
		} catch (SQLException se) {
			se.printStackTrace();
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
	public void update(ExperReportVO erVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, erVO.getReporter_no());
			pstmt.setInt(2, erVO.getReported_exper_no());
			pstmt.setString(3, erVO.getReason());
			pstmt.setString(4, erVO.getReply_content());
			pstmt.setInt(5,erVO.getIs_checked());
			pstmt.setInt(6, erVO.getReport_no());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
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
	public List<ExperReportVO> getAll() {
		List<ExperReportVO> list = new ArrayList<ExperReportVO>();
		ExperReportVO erVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				erVO = new ExperReportVO();
				erVO.setReport_no(rs.getInt("report_no"));
				erVO.setReporter_no(rs.getInt("reporter_no"));
				erVO.setReported_exper_no(rs.getInt("reported_exper_no"));
				erVO.setReason(rs.getString("reason"));
				erVO.setReport_time(rs.getTimestamp("report_time"));
				erVO.setReply_content(rs.getString("reply_content"));
				erVO.setReply_time(rs.getTimestamp("reply_time"));
				erVO.setIs_checked(rs.getInt("is_checked"));
				list.add(erVO);
			}
		
		} catch (SQLException se) {
			se.printStackTrace();
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

	
	public ExperReportVO findByPrimaryKey(Integer report_no) {
		ExperReportVO erVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, report_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				erVO = new ExperReportVO();
				erVO.setReport_no(rs.getInt("report_no"));
				erVO.setReporter_no(rs.getInt("reporter_no"));
				erVO.setReported_exper_no(rs.getInt("reported_exper_no"));
				erVO.setReason(rs.getString("reason"));
				erVO.setReport_time(rs.getTimestamp("report_time"));
				erVO.setReply_content(rs.getString("reply_content"));
				erVO.setReply_time(rs.getTimestamp("reply_time"));
				erVO.setIs_checked(rs.getInt("is_checked"));
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
		return erVO;
	}
	
	@Override
	public Set<ExperReportVO> findByExperNo(Integer reported_exper_no) {
		Set<ExperReportVO> set = new LinkedHashSet<ExperReportVO>();
		ExperReportVO erVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_REPORT_BY_EXPERNO);
			pstmt.setInt(1, reported_exper_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				erVO = new ExperReportVO();
				erVO.setReport_no(rs.getInt("report_no"));
				erVO.setReporter_no(rs.getInt("reporter_no"));
				erVO.setReason(rs.getString("reason"));
				erVO.setReport_time(rs.getTimestamp("report_time"));
				erVO.setReply_content(rs.getString("reply_content"));
				erVO.setReply_time(rs.getTimestamp("reply_time"));
				erVO.setIs_checked(rs.getInt("is_checked"));
				set.add(erVO);
			}
		
		} catch (SQLException se) {
			se.printStackTrace();
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
		return set;
	}
	
	
	public static void main(String[] args) {
		ExperReportJDBCDAO dao = new ExperReportJDBCDAO();
		
		//insert
//		ExperReportVO erVO1 = new ExperReportVO();
//		
//		erVO1.setReporter_no(2);
//		erVO1.setReported_exper_no(3);
//		erVO1.setReason("體驗達人長這麼醜 好意思出來見人？？");
//		dao.insert(erVO1);
		
		
		//update
//		ExperReportVO erVO1 = new ExperReportVO();
//		erVO1.setReport_no(12);
//		erVO1.setReply_content("操你媽 你長更醜");
//    	erVO1.setIs_checked(2);
//		dao.update(erVO1);
		
		//getAll
		List<ExperReportVO> list = dao.getAll();
		for(ExperReportVO aExper : list) {
			System.out.print(aExper.getReport_no()+",");
			System.out.print(aExper.getReporter_no()+",");
			System.out.print(aExper.getReported_exper_no()+",");
			System.out.print(aExper.getReason()+",");
			System.out.print(aExper.getReport_time()+",");
			System.out.print(aExper.getReply_content()+",");
			System.out.print(aExper.getReply_time()+",");
			System.out.println(aExper.getIs_checked());
		}
		
		//getbyReportedExperNo
//		Set<ExperReportVO> set = dao.findByExperNo(2);
//		for(ExperReportVO aExper : set) {
//			System.out.print(aExper.getReport_no()+",");
//			System.out.print(aExper.getReporter_no()+",");
//			System.out.print(aExper.getReason()+",");
//			System.out.print(aExper.getReport_time()+",");
//			System.out.print(aExper.getReply_content()+",");
//			System.out.print(aExper.getReply_time()+",");
//			System.out.println(aExper.getIs_checked());
//			System.out.println();
//		}
		
	
		
		
//		findByPrimaryKey
//		ExperReportVO erVO = dao.findByPrimaryKey(1);
//		System.out.print(erVO.getReporter_no()+",");
//		System.out.print(erVO.getReported_exper_no()+",");
//		System.out.print(erVO.getReason()+",");
//		System.out.print(erVO.getReport_time()+",");
//		System.out.print(erVO.getReply_content()+",");
//		System.out.print(erVO.getReply_time()+",");
//		System.out.println(erVO.getIs_checked());
		}
	}