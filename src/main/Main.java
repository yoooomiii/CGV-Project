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
			System.out.println("1.�Ϲ� ����� �α���  2.������ �α���  3.ȸ������ ");
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
		System.out.println("������ ���̵� �Է����ּ���");
		String id = in.nextLine();
		System.out.println("������ ��й�ȣ�� �Է����ּ���");
		String pw = in.nextLine();
		System.out.println("������ �̸��� �Է����ּ���");
		String name = in.nextLine();
		MemberDTO m = new MemberDTO();
		m.setId(id);
		m.setPw(pw);
		m.setName(name);
		mdao.insertm(m);
	}



	private static void manager() {
		System.out.println("������ ��й�ȣ�� �Է����ּ���");
		String pw="1111";
		String getpw = in.nextLine();
		if(pw.equals(getpw)) {
			System.out.println("�α��� ����");
			cgv.menuM();
		} else {
			System.out.println("�α��� ����");
		}
	}

	private static void user() {
		System.out.println("���̵� �Է����ּ���");
		String id = in.nextLine();
		System.out.println("��й�ȣ�� �Է����ּ���");
		String pw = in.nextLine();
		MemberDTO m = new MemberDTO();
		m.setId(id);
		m.setPw(pw);
		nowuser = mdao.chkid(m);
		if(nowuser!=null) {
			System.out.println("�α��� ����");
			cgv.menu(nowuser);
		} else {
			System.out.println("�α��� ����");
		}
	}
}
