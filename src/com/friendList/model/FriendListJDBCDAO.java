package com.friendList.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.friendInvit.model.FriendInvitJDBCDAO;
import com.member.model.MemberVO;
import com.notify.model.NotifyVO;

public class FriendListJDBCDAO implements FriendList_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GuideMe?useSSL=false&serverTimezone=Asia/Taipei&";
	public static final String USER = "root";
	private static final String PASSWORD = "123456";
	private static final String INSERT_STMT = "INSERT INTO Friend_List(Member_No, Friend_No) VALUES (?, ?)";
	private static final String DELETE_STMT = "DELETE FROM Friend_List WHERE (Member_No = ? AND Friend_No = ?) or (Member_No = ? AND Friend_No = ?)";
	private static final String FIND_BY_PK = "call findFriendListByPK(?,?)";
	private static final String FIND_BY_NAME = "call findFriendByMemberName(?,?)";
	private static final String FIND_BY_MEMNO = "SELECT Friend_No FriendList FROM Friend_List WHERE Member_No = ? union SELECT Member_No FriendList FROM Friend_List WHERE Friend_No = ?";
	private static final String GET_ALL = "SELECT * FROM Friend_List";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(FriendListVO friendListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, friendListVO.getMemberNo());
			pstmt.setInt(2, friendListVO.getFriendNo());
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
	public void delete(Integer memberNo, Integer friendNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, friendNo);
			pstmt.setInt(3, friendNo);
			pstmt.setInt(4, memberNo);
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
	public FriendListVO findByPrimaryKey(Integer memberNo, Integer friendNo) {
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, friendNo);

//			System.out.println("findByPrimaryKey_SQL:" + FIND_BY_PK + "   " + memberNo + "  " + friendNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemberNo(memberNo);
				friendListVO.setFriendNo(new Integer(rs.getString("FriendList")));
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
		return friendListVO;
	}

	@Override
	public List<MemberVO> findByMemberName(Integer memberNo, String name) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_NAME);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, name);
//			System.out.println("findByMemberName:" + FIND_BY_NAME + "   " + memberNo + "  " + name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemberNo(new Integer(rs.getString("Member_No")));
				memberVO.setAccount(rs.getString("Account"));
				memberVO.setName(rs.getString("Name"));
				memberVO.setEmail(rs.getString("Email"));
				list.add(memberVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<FriendListVO> findByMemberNo(Integer memberNo) {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MEMNO);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemberNo(memberNo);
				friendListVO.setFriendNo(rs.getInt("FriendList"));
				list.add(friendListVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public List<FriendListVO> getAll() {
		List<FriendListVO> list = new ArrayList<FriendListVO>();
		FriendListVO friendListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendListVO = new FriendListVO();
				friendListVO.setMemberNo(rs.getInt("Member_No"));
				friendListVO.setFriendNo(rs.getInt("Friend_No"));
				list.add(friendListVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
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
	public void insertFriListWithDelFriInvit(FriendListVO friendListVO, Integer friendInvitNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, friendListVO.getMemberNo());
			pstmt.setInt(2, friendListVO.getFriendNo());
			pstmt.executeUpdate();

			FriendInvitJDBCDAO dao = new FriendInvitJDBCDAO();
			dao.delete2(friendInvitNo, con);

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			if (con != null) {
				try {
					// 3??????????????????exception????????????catch?????????
					System.err.print("Transaction is being ");
					System.err.println("rolled back-???-friendList");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public void insertFriListWithDelFriInvitAndNotify(FriendListVO friendListVO, Integer friendInvitNo,
			NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, friendListVO.getMemberNo());
			pstmt.setInt(2, friendListVO.getFriendNo());
			pstmt.executeUpdate();

			FriendInvitJDBCDAO dao = new FriendInvitJDBCDAO();
//			dao.delete2(friendInvitNo, con);
			dao.delete3(friendInvitNo, notifyVO, con);

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			if (con != null) {
				try {
					// 3??????????????????exception????????????catch?????????
					System.err.print("Transaction is being ");
					System.err.println("rolled back-???-friendList");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
