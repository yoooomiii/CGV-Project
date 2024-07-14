package main;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import manager.CGVAdmin;

public class Main {
	static private Scanner in = new Scanner(System.in);
	static private MemberDAO mdao = new MemberDAO();
	static private MemberDTO nowuser = null;
	static private CGVAdmin cgv = new CGVAdmin();
	static public void main(String[] args) {
		new CGVAdmin();
		menu();
	}

	public static void menu() {
		while(true) {
			System.out.println("1.일반 사용자 로그인  2.관리자 로그인  3.회원가입 ");
			int selnum = in.nextInt();
			in.nextLine();
			if(selnum==1) {
				user();
			} else if(selnum==2) {
				manager();
			} else if(selnum==3) {
				join();
			} else {
				break;
			}
		}
	}

	private static void join() {
		System.out.println("가입할 아이디를 입력해주세요");
		String id = in.nextLine();
		System.out.println("가입할 비밀번호를 입력해주세요");
		String pw = in.nextLine();
		System.out.println("가입할 이름을 입력해주세요");
		String name = in.nextLine();
		MemberDTO m = new MemberDTO();
		m.setId(id);
		m.setPw(pw);
		m.setName(name);
		mdao.insertm(m);
	}



	private static void manager() {
		System.out.println("관리자 비밀번호를 입력해주세요");
		String pw="1111";
		String getpw = in.nextLine();
		if(pw.equals(getpw)) {
			System.out.println("로그인 성공");
			cgv.menuM();
		} else {
			System.out.println("로그인 실패");
		}
	}

	private static void user() {
		System.out.println("아이디를 입력해주세요");
		String id = in.nextLine();
		System.out.println("비밀번호를 입력해주세요");
		String pw = in.nextLine();
		MemberDTO m = new MemberDTO();
		m.setId(id);
		m.setPw(pw);
		nowuser = mdao.chkid(m);
		if(nowuser!=null) {
			System.out.println("로그인 성공");
			cgv.menu(nowuser);
		} else {
			System.out.println("로그인 실패");
		}
	}
}
