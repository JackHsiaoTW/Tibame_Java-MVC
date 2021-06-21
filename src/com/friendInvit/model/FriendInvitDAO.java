package com.friendInvit.model;

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

import com.notify.model.NotifyDAO;
import com.notify.model.NotifyVO;

public class FriendInvitDAO implements FriendInvit_interface {
	private static final String INSERT_STMT = "INSERT INTO Friend_Invitation(Adder, Confirmer) VALUES (?, ?)";
//	private static final String UPDATE_STMT = "UPDATE Friend_Invitation SET Adder = ?, Confirmer = ? WHERE Friend_Invit_No = ?";
	private static final String DELETE_STMT = "DELETE FROM Friend_Invitation WHERE Friend_Invit_No = ?";
	private static final String FIND_BY_PK = "SELECT * FROM Friend_Invitation WHERE Friend_Invit_No = ?";
	private static final String FIND_BY_2MEMNO = "call findFriendInvitListBy2MemNo(?,?)";
	private static final String FIND_BY_MEMNO = "SELECT * FROM Friend_Invitation where Confirmer = ?";
	private static final String GET_ALL = "SELECT * FROM Friend_Invitation";
	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/guidemeDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(FriendInvitVO friendInvitVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, friendInvitVO.getAdder());
			pstmt.setInt(2, friendInvitVO.getConfirmer());
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
	public void delete(Integer friendInvitNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, friendInvitNo);
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
	public FriendInvitVO findByPrimaryKey(Integer friendInvitNo) {
		FriendInvitVO friendInvitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, friendInvitNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendInvitVO = new FriendInvitVO();
				friendInvitVO.setFriendInvitNo(rs.getInt("Friend_Invit_No"));
				friendInvitVO.setAdder(rs.getInt("Adder"));
				friendInvitVO.setConfirmer(rs.getInt("Confirmer"));
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

		return friendInvitVO;
	}

	@Override
	public FriendInvitVO findBy2MemNo(Integer memberNo1, Integer memberNo2) {
		FriendInvitVO friendInvitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_2MEMNO);
			pstmt.setInt(1, memberNo1);
			pstmt.setInt(2, memberNo2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendInvitVO = new FriendInvitVO();
				friendInvitVO.setFriendInvitNo(rs.getInt("Friend_Invit_No"));
				friendInvitVO.setAdder(rs.getInt("Adder"));
				friendInvitVO.setConfirmer(rs.getInt("Confirmer"));
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

		return friendInvitVO;
	}

	@Override
	public List<FriendInvitVO> findByMemNo(Integer memberNo) {
		List<FriendInvitVO> list = new ArrayList<FriendInvitVO>();
		FriendInvitVO friendInvitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_MEMNO);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendInvitVO = new FriendInvitVO();
				friendInvitVO.setFriendInvitNo(rs.getInt("Friend_Invit_No"));
				friendInvitVO.setAdder(rs.getInt("Adder"));
				friendInvitVO.setConfirmer(rs.getInt("Confirmer"));
				list.add(friendInvitVO);
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
	public List<FriendInvitVO> getAll() {
		List<FriendInvitVO> list = new ArrayList<FriendInvitVO>();
		FriendInvitVO friendInvitVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendInvitVO = new FriendInvitVO();
				friendInvitVO.setFriendInvitNo(rs.getInt("Friend_Invit_No"));
				friendInvitVO.setAdder(rs.getInt("Adder"));
				friendInvitVO.setConfirmer(rs.getInt("Confirmer"));
				list.add(friendInvitVO);
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
	public Integer insertWithNotify(FriendInvitVO friendInvitVO, NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer friendInvitNo = null;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String cols[] = { "Friend_Invit_No" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, friendInvitVO.getAdder());
			pstmt.setInt(2, friendInvitVO.getConfirmer());
			pstmt.executeUpdate();

			// 掘取對應的自增主鍵值
//			String friendInvitNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				friendInvitNo = rs.getInt(1);
				System.out.println("自增主鍵值= " + friendInvitNo + "(剛新增成功的朋友邀請編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}

			NotifyDAO notifyDAO = new NotifyDAO();
			notifyDAO.insert(notifyVO);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-friendInvit");
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
		return friendInvitNo;
	}

	@Override
	public void delete2(Integer friendInvitNo, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, friendInvitNo);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-friendInvit");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete3(Integer friendInvitNo, NotifyVO notifyVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setInt(1, friendInvitNo);
			pstmt.executeUpdate();

			NotifyDAO dao = new NotifyDAO();
			dao.insert2(notifyVO, con);

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-friendInvit");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

}
