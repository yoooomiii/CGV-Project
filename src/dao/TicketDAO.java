package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dto.MemberDTO;
import dto.TicketDTO;

public class TicketDAO {

	private Connection conn=null;
	private String url="jdbc:oracle:thin:@localhost:1522:xe";
	public TicketDAO() {  
		String driver ="oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			//			System.out.println("�ε� ����");
		} catch (Exception e) {
			//			System.out.println("�ε� ����");
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url,"system","1111");
			//			System.out.println("���Ἲ��");
			return conn;
		} catch (Exception e) {
			//			System.out.println("�������");
		}
		return null;
	}


	public void insertt(TicketDTO t ) {
		if(getConnection() != null) {
			try {
				String sql ="insert into TICKET values (?,?,?)";
				PreparedStatement psmt=null;
				psmt=conn.prepareCall(sql);
				psmt.setString(1,t.getId());
				psmt.setString(2, t.getCode());
				psmt.setString(3, t.getSeat());
				psmt.executeUpdate();
				System.out.println("��ȭ ���� ��� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ����");
			}
		}
	}

	public ArrayList<TicketDTO> selAll(MemberDTO m) {
		ArrayList<TicketDTO> tlist = new ArrayList<>();
		if(getConnection() != null) {
			try {
				String sql = "select * from TICKET where id =?";
				PreparedStatement psmt = null;
				psmt =conn.prepareCall(sql);
				psmt.setString(1,m.getId());
				ResultSet rs = null;	
				rs = psmt.executeQuery();
				while(rs.next()) {
					TicketDTO t = new TicketDTO();
					t.setCode(rs.getString("code"));
					t.setId(rs.getString("id"));
					t.setSeat(rs.getString("seat"));
					tlist.add(t);
				}
			} catch (Exception e) {

			}
		}
		return tlist;
	}

	public void delete(String code, MemberDTO mdto) {
		if(getConnection() != null) {
			try {
				String sql ="delete from TICKET where code=? and id=?";
				PreparedStatement psmt=null;
				psmt=conn.prepareStatement(sql);
				psmt.setString(1,code);
				psmt.setString(2,mdto.getId() );
				psmt.executeUpdate();
				System.out.println("���Ű� ��ҵǾ����ϴ�");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ����");
			}
		}
	}

	public void update(MemberDTO mdto, String seatnum, String code) {
		if(getConnection() != null) {
			try {
				String sql ="update TICKET set seat=? where id=? and code=?" ;
				PreparedStatement psmt=null;
				psmt=conn.prepareStatement(sql);
				psmt.setString(1,seatnum);
				psmt.setString(2,mdto.getId());
				psmt.setString(3,code);
				psmt.executeUpdate();
				System.out.println("��ȭ ���� ���� ����");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("��� ����");
			}
		}
	}
}
