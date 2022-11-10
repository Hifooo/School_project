package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.JDBCUtil;

public class MemberDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	final String FIND_MAX = "select max(custno) from member_tbl_02";
	final String INSERT_MEMBER = "insert into member_tbl_02 values(?, ?, ?, ?, ?, ?, ?)";
	final String SELECT_ALL = "select * from member_tbl_02";
	final String SELECT_ONE = "select * from member_tbl_02 where custno=?";
	final String UPDATE_MEMBER = "update member_tbl_02 set where custno  = ?";
	
	public int maxCustno() {
		
		int max = 0;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(FIND_MAX);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				max = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return max;
	}
	
	public int register(MemberVO vo) {
		int cnt = 0;
	try {
		conn = JDBCUtil.getConnection();
		pstmt = conn.prepareStatement(INSERT_MEMBER);
		pstmt.setInt(1, vo.getCustno());
		pstmt.setString(2, vo.getCustname());
		pstmt.setString(3, vo.getPhone());
		pstmt.setString(4, vo.getAddress());
		pstmt.setDate(5, vo.getJoindate());
		pstmt.setString(6, vo.getGrade());
		pstmt.setString(7, vo.getCity());
		
	
		cnt = pstmt.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return cnt;
	}
	public ArrayList<MemberVO> selectMembers() {
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				Date joindate = rs.getDate("joindate");
				String grade = rs.getString("grade");
				String city = rs.getString("city");
				
				MemberVO vo = new MemberVO(custno, custname, phone, address, joindate, grade, city);
				list.add(vo);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public MemberVO selectMember(int custno) {
		MemberVO vo = new MemberVO();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int custnum = rs.getInt("custno");  
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				Date joindate = rs.getDate("joindate");
				String grade = rs.getString("grade");
				String city = rs.getString("city");
				vo = new MemberVO(custnum, custname, phone, address, joindate, grade, city);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
