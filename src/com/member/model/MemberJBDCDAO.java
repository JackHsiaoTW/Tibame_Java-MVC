package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.friendList.model.FriendListVO;

public class MemberJBDCDAO implements MemberDAO_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GuideMe?useSSL=false&serverTimezone=Asia/Taipei&";
	public static final String USER = "root";
	private static final String PASSWORD = "123456";
//	private static final String INSERT_STMT = "INSERT INTO Member(Account, Password, Name, ID_Number, Birth_Date, Phone, email, Member_State, Member_Pic, Lisce_Pic1, Lisce_Pic2, Lisce_Pic3, Lisce_Name1, Lisce_Name2, Lisce_Name3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT = "INSERT INTO Member(Account, Password, Name, ID_Number, Birth_Date, Phone, email, Member_State, Member_Pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String UPDATE_STMT = "UPDATE Member SET Account = ?, Password = ?, Name = ?, ID_Number = ?, Birth_Date = ?, Phone = ?, email = ?, Member_State = ? , Member_Pic = ?, Lisce_Pic1 = ?, Lisce_Pic2 = ?, Lisce_Pic3 = ?, Lisce_Name1 = ?, Lisce_Name2 = ?, Lisce_Name3 = ? WHERE member_no = ?";
	private static final String UPDATE_STMT = "UPDATE Member SET Account = ?, Password = ?, Name = ?, ID_Number = ?, Birth_Date = ?, Phone = ?, email = ?, Member_State = ? , Member_Pic = ? WHERE member_no = ?";
	private static final String UPDATEMEMLISCE = "UPDATE Member SET Lisce_Pic1 = ?, Lisce_Pic2 = ?, Lisce_Pic3 = ?, Lisce_Name1 = ?, Lisce_Name2 = ?, Lisce_Name3 = ? WHERE member_no = ?";
	private static final String DELETE_STMT = "DELETE FROM Member WHERE member_no = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Member WHERE member_no = ?";
	private static final String FIND_BY_NAME = "SELECT * FROM Member WHERE NAME like ? ";
	private static final String FIND_BY_EMAIL = "SELECT * FROM Member WHERE EMAIL = ? ";
	private static final String FIND_BY_ACCOUNT = "SELECT * FROM Member where Account = ?";
	private static final String FIND_BY_NAME_FORFRIENDS = "SELECT * FROM Member WHERE NAME like ? ";
	private static final String GET_ALL = "SELECT * FROM Member";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member.getAccount());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getIdNumber());
			pstmt.setDate(5, member.getBirthDate());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setInt(8, member.getMemberState());
			pstmt.setBytes(9, member.getMemberPic());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void update(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, member.getAccount());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getIdNumber());
			pstmt.setDate(5, member.getBirthDate());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getEmail());
			pstmt.setInt(8, member.getMemberState());
			pstmt.setBytes(9, member.getMemberPic());
			pstmt.setInt(10, member.getMemberNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void delete(Integer memberNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, memberNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public MemberVO findByPrimaryKey(Integer memberNo) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getInt("Member_No"));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setPassword(rs.getString("Password"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setIdNumber(rs.getString("ID_Number"));
				memberVO.setBirthDate(rs.getDate("Birth_Date"));
				memberVO.setPhone(rs.getString("Phone"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setMemberState(rs.getInt("Member_State"));
				memberVO.setMemberPic(rs.getBytes("Member_Pic"));
				memberVO.setLiscePic1(rs.getBytes("Lisce_Pic1"));
				memberVO.setLiscePic2(rs.getBytes("Lisce_Pic2"));
				memberVO.setLiscePic3(rs.getBytes("Lisce_Pic3"));
				memberVO.setLisceName1(rs.getString("Lisce_Name1"));
				memberVO.setLisceName2(rs.getString("Lisce_Name2"));
				memberVO.setLisceName3(rs.getString("Lisce_Name3"));
			}

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
		return memberVO;
	}

	@Override
	public List<MemberVO> findByName(String name) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NAME);
//			System.out.println("FIND_BY_NAME:" + FIND_BY_NAME);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getInt("Member_No"));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setPassword(rs.getString("Password"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setIdNumber(rs.getString("ID_Number"));
				memberVO.setBirthDate(rs.getDate("Birth_Date"));
				memberVO.setPhone(rs.getString("Phone"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setMemberState(rs.getInt("Member_State"));
				memberVO.setMemberPic(rs.getBytes("Member_Pic"));
				memberVO.setLiscePic1(rs.getBytes("Lisce_Pic1"));
				memberVO.setLiscePic2(rs.getBytes("Lisce_Pic2"));
				memberVO.setLiscePic3(rs.getBytes("Lisce_Pic3"));
				memberVO.setLisceName1(rs.getString("Lisce_Name1"));
				memberVO.setLisceName2(rs.getString("Lisce_Name2"));
				memberVO.setLisceName3(rs.getString("Lisce_Name3"));
				list.add(memberVO);
			}
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
	public MemberVO findByEmail(String email) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_EMAIL);
//			System.out.println("FIND_BY_EMAIL:" + FIND_BY_EMAIL);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getInt("Member_No"));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setPassword(rs.getString("Password"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setIdNumber(rs.getString("ID_Number"));
				memberVO.setBirthDate(rs.getDate("Birth_Date"));
				memberVO.setPhone(rs.getString("Phone"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setMemberState(rs.getInt("Member_State"));
				memberVO.setMemberPic(rs.getBytes("Member_Pic"));
				memberVO.setLiscePic1(rs.getBytes("Lisce_Pic1"));
				memberVO.setLiscePic2(rs.getBytes("Lisce_Pic2"));
				memberVO.setLiscePic3(rs.getBytes("Lisce_Pic3"));
				memberVO.setLisceName1(rs.getString("Lisce_Name1"));
				memberVO.setLisceName2(rs.getString("Lisce_Name2"));
				memberVO.setLisceName3(rs.getString("Lisce_Name3"));
			}
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
		return memberVO;
	}
	
	@Override
	public MemberVO findByAccount(String account) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNT);
//			System.out.println("FIND_BY_ACCOUNT:" + FIND_BY_ACCOUNT);
			pstmt.setString(1, account);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getInt("Member_No"));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setPassword(rs.getString("Password"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setIdNumber(rs.getString("ID_Number"));
				memberVO.setBirthDate(rs.getDate("Birth_Date"));
				memberVO.setPhone(rs.getString("Phone"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setMemberState(rs.getInt("Member_State"));
				memberVO.setMemberPic(rs.getBytes("Member_Pic"));
				memberVO.setLiscePic1(rs.getBytes("Lisce_Pic1"));
				memberVO.setLiscePic2(rs.getBytes("Lisce_Pic2"));
				memberVO.setLiscePic3(rs.getBytes("Lisce_Pic3"));
				memberVO.setLisceName1(rs.getString("Lisce_Name1"));
				memberVO.setLisceName2(rs.getString("Lisce_Name2"));
				memberVO.setLisceName3(rs.getString("Lisce_Name3"));
			}
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
		return memberVO;
	}

	@Override
	public List<FriendListVO> findByNameForFreinds(Integer memNO, String name) {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NAME_FORFRIENDS);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemberNo(memNO);
				friendListVO.setFriendNo(rs.getInt("Member_No"));
				list.add(friendListVO);
			}

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
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(rs.getInt("Member_No"));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setPassword(rs.getString("Password"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setIdNumber(rs.getString("ID_Number"));
				memberVO.setBirthDate(rs.getDate("Birth_Date"));
				memberVO.setPhone(rs.getString("Phone"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setMemberState(rs.getInt("Member_State"));
				memberVO.setMemberPic(rs.getBytes("Member_Pic"));
				memberVO.setLiscePic1(rs.getBytes("Lisce_Pic1"));
				memberVO.setLiscePic2(rs.getBytes("Lisce_Pic2"));
				memberVO.setLiscePic3(rs.getBytes("Lisce_Pic3"));
				memberVO.setLisceName1(rs.getString("Lisce_Name1"));
				memberVO.setLisceName2(rs.getString("Lisce_Name2"));
				memberVO.setLisceName3(rs.getString("Lisce_Name3"));
				list.add(memberVO);
			}

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
	public void updateLise(MemberVO member) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATEMEMLISCE);

			pstmt.setBytes(1, member.getLiscePic1());
			pstmt.setBytes(2, member.getLiscePic2());
			pstmt.setBytes(3, member.getLiscePic3());
			pstmt.setString(4, member.getLisceName1());
			pstmt.setString(5, member.getLisceName2());
			pstmt.setString(6, member.getLisceName3());
			pstmt.setInt(7, member.getMemberNo());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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

}
