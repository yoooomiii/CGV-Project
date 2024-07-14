package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Statement;

import dto.MemberDTO;
import dto.MovieDTO;

public class MemberDAO {

	private Connection conn=null;
	private String url="jdbc:oracle:thin:@localhost:1522:xe";
	public MemberDAO() {  
		String driver ="oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			//			System.out.println("로드 성공");
		} catch (Exception e) {
			//			System.out.println("로드 실패");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url,"system","1111");
			//			System.out.println("연결성공");
			return conn;
		} catch (Exception e) {
			//			System.out.println("연결실패");
		}
		return null;
	}

	public MemberDTO chkid(MemberDTO m) {
		MemberDTO mm = null;
		if(getConnection() != null) {
			try {
				String sql = "select * from MEMBER where id= ? and pw= ?";
				PreparedStatement psmt=null;
				psmt=conn.prepareCall(sql);
				psmt.setString(1,m.getId() );
				psmt.setString(2, m.getPw());
				ResultSet rs = null;
				rs = psmt.executeQuery();
				if(rs.next()) {
					mm = new MemberDTO();
					mm.setId(rs.getString("id"));
					mm.setPw(rs.getString("pw"));
					mm.setName(rs.getString("name"));
				}
			} catch (Exception e) {
				System.out.println("로그인 중 실패");
			}
		}
		return mm;
	}

	public void insertm(MemberDTO m) {
		if(getConnection() != null) {
			try {
				String sql = "insert into MEMBER values (?,?,?)";
				PreparedStatement psmt=null;
				psmt=conn.prepareCall(sql);
				psmt.setString(1,m.getId() );
				psmt.setString(2, m.getPw());
				psmt.setString(3, m.getName());
				psmt.executeUpdate();
			} catch (Exception e) {
			}
		}
	}

	public MemberDTO sel(String id) {
		MemberDTO m = null;
		if(getConnection() != null) {
			try {
				String sql = "select * from MEMBER where id= ?";
				PreparedStatement psmt=null;
				psmt=conn.prepareCall(sql);
				psmt.setString(1, id );
				ResultSet rs = null;
				rs = psmt.executeQuery();
				if(rs.next()) {
					m= new MemberDTO();
					m.setId(rs.getString("id"));
					m.setPw(rs.getString("pw"));
					m.setName(rs.getString("name"));
					System.out.println("---------------");
				}
			} catch (Exception e) {
				System.out.println(" 실패");
			}
		}
		return m;
	}
}
