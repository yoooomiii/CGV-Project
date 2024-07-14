package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.MovieDTO;
import dto.TicketDTO;

public class MovieDAO {

	private Connection conn=null;
	private String url="jdbc:oracle:thin:@localhost:1522:xe";

	public MovieDAO() {  
		String driver ="oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			//			System.out.println("로드 성공");
		} catch (Exception e) {
			//			System.out.println("로드 실패");
		}
	}

	public void delete(String code) {
		if(getConnection() != null) {
			try {
				PreparedStatement psmt = null;
				String sql = "delete from MOVIE where code=?";
				psmt =conn.prepareStatement(sql);
				psmt.setString(1, code);
				psmt.executeUpdate();
				System.out.println("삭제되었습니다");
			} catch (Exception e) {
				System.out.println("예약한 고객이 있습니다");
			}
		}
	}

	public ArrayList<MovieDTO> search(String code) {
		ArrayList<MovieDTO> mvdto = new ArrayList <>();
		if(getConnection() != null) {
			try {
				String sql= "select * from MOVIE where code = ?";
				ResultSet rs =null;
				PreparedStatement psmt =conn.prepareStatement(sql);
				psmt.setString(1, code);
				rs = psmt.executeQuery();
				while(rs.next()) {
					MovieDTO m = new MovieDTO();
					m.setCode(rs.getString("code"));
					m.setMname(rs.getString("mname"));
					m.setStime(rs.getString("stime"));
					m.setEtime(rs.getString("etime"));
				}
			} catch (Exception e) {
			}
		}
		return mvdto;
	}



	public void update(String code, String mname, String stime, String etime) {
		if(getConnection() != null) {
			try {
				PreparedStatement psmt = null;
				String sql = "update MOVIE set mname=? , stime=? , etime=? where code=?";
				psmt =conn.prepareStatement(sql);
				psmt.setString(1, mname);
				psmt.setString(2, stime);
				psmt.setString(3, etime);
				psmt.setString(4, code);
				psmt.executeUpdate();
				System.out.println("수정이되었습니다");
			} catch (Exception e) {

			}
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

	public void insertmv(MovieDTO m) {
		if(getConnection() != null) {
			try {
				String sql ="insert into MOVIE values (?,?,?,?)";
				PreparedStatement psmt=null;
				psmt=conn.prepareCall(sql);
				psmt.setString(1, m.getCode());
				psmt.setString(2, m.getMname());
				psmt.setString(3, m.getStime());
				psmt.setString(4, m.getEtime());
				psmt.executeUpdate();
				System.out.println("영화 정보 등록 성공");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("등록 실패");
			}
		}
	}

	public ArrayList<MovieDTO> selAll() {
		ArrayList<MovieDTO> mvlist = new ArrayList<>();
		if(getConnection() != null) {
			try {
				String sql = "select * from MOVIE";
				Statement stmt = null;
				stmt =conn.createStatement();
				ResultSet rs = null;	
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					MovieDTO m = new MovieDTO();
					m.setCode(rs.getString("code"));
					m.setMname(rs.getString("mname"));
					m.setStime(rs.getString("stime"));
					m.setEtime(rs.getString("etime"));
					mvlist.add(m);
				}
			} catch (Exception e) {

			}
		}
		return mvlist;
	}

	public MovieDTO sel(TicketDTO t) {
		MovieDTO mv= null;
		if(getConnection() != null) {
			try {
				String sql = "select * from MOVIE where code=?";
				PreparedStatement psmt = null;
				psmt =conn.prepareStatement(sql);
				psmt.setString(1, t.getCode());
				ResultSet rs = null;	
				rs = psmt.executeQuery();
				if(rs.next()) { 
					mv= new MovieDTO();
					mv.setCode(rs.getString("code"));
					mv.setMname(rs.getString("mname"));
					mv.setStime(rs.getString("stime"));
					mv.setEtime(rs.getString("etime"));
				}
			} catch (Exception e) {
			}
		}
		return mv;
	}
}

