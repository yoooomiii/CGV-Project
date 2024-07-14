package manager;

import java.util.ArrayList;
import java.util.Scanner;

import dao.MemberDAO;
import dao.MovieDAO;
import dao.TicketDAO;
import dto.MemberDTO;
import dto.MovieDTO;
import dto.TicketDTO;

public class CGVAdmin {
	private Scanner in = new Scanner(System.in);
	MemberDTO mdto = null;
	MemberDAO mdao = new MemberDAO();
	MovieDAO mvdao = new MovieDAO();
	TicketDAO tdao = new TicketDAO();
	public CGVAdmin() {
	}
	public void menu(MemberDTO m) {
		mdto = m;
		while(true) {
			System.out.println("1.��ȭ ����  2.���� ���  3.���� ����  4.���� ��ȸ");
			int selnum = in.nextInt();
			in.nextLine();
			if(selnum==1) {
				mvList();
				add();
			} else if(selnum==2) {
				tList();
				del();
			} else if(selnum==3) {
				tList();
				mod();
			} else if(selnum==4) {
				tList();
			} else {
				break;
			}
		}
	}
	private void mod() {
		System.out.println("������ ��ȭ�� �ڵ带 �Է����ּ���");
		String code = in.nextLine();
		System.out.println("������ �¼��� ���� �Է����ּ���  A~H");
		String seat1 = in.nextLine();
		System.out.println("������ �¼��� ���� �Է����ּ��� 1~20");
		int seat2 = in.nextInt();
		if(chkseatnum(seat1, seat2)) {
			return;
		}
		String seatnum= seat1 + seat2;
		tdao.update(mdto, seatnum,code);

	}
	private void del() {
		System.out.println("���� �ּҸ� �Ϸ��� ��ȭ�� �ڵ带 �Է����ּ���");
		String code = in.nextLine();
		tdao.delete(code, mdto);
	}


	private void add() {
		mvdao.selAll();
		TicketDTO t =new TicketDTO();
		System.out.println("������ ��ȭ �ڵ带 �Է����ּ���");
		String code = in.nextLine();
		System.out.println("�¼��� ���� �Է����ּ���   A~H");
		String seat1 = in.nextLine();
		System.out.println("�¼��� ���� �Է����ּ��� 1~20");
		int seat2 = in.nextInt();
		in.nextLine();
		if(chkseatnum(seat1, seat2)) {
			return;
		}
		String seatnum = seat1 + seat2;

		t.setCode(code);
		t.setId(mdto.getId());
		t.setSeat(seatnum);
		tdao.insertt(t);
	}


	public void menuM () {
		while(true) {
			System.out.println("1.��ǥ ���  2.����  3.����  4.�� ���� ���� ��ȸ");
			int selnum = in.nextInt();
			in.nextLine();
			if(selnum==1) {
				addM();
			} else if(selnum==2) {
				modM();
				mvList();
			} else if(selnum==3) {
				delM();
			} else if(selnum==4) {
				searchM();
			} else {
				break;
			}
		}
	}

	private void searchM() {
		System.out.println("�˻��� ���� ���̵� �Է����ּ���");
		String id = in.nextLine();
		MemberDTO m = new MemberDTO();
		m= mdao.sel(id);
		System.out.println("�� ���̵�" + m.getId());
		System.out.println("�� �̸�:" + m.getName());
		ArrayList<TicketDTO> tlist = new ArrayList<>();
		tlist=tdao.selAll(m);
		for(TicketDTO t : tlist) {
			System.out.println("��ȭ �ڵ�:" + t.getCode());
			System.out.println("��ȭ �̸�:" + mvdao.sel(t).getMname());
			System.out.println("�¼� ��ȣ:" + t.getSeat());
		}
	}	


	private void modM() {
		mvList();
		System.out.println("������ ��ȭ�� �ڵ带 �Է��ϼ���");
		String code = in.nextLine();
		System.out.println("��ȭ�� �̸��� �Է��ϼ���");
		String mname = in.nextLine();
		System.out.println("��ȭ ���� �ð��� �Է��ϼ���");
		String stime= in.nextLine();
		System.out.println("��ȭ ���� �ð��� �Է��ϼ���");
		String etime = in.nextLine();
		mvdao.update(code,mname,stime,etime);
		
	}
	private void delM() {
		mvList();
		System.out.println("������ ��ȭ�� �ڵ带 �Է��ϼ���");
		String code = in.nextLine();
		mvdao.delete(code);
		
		
	}
	private void addM() {
		System.out.println("�� �ڵ� �Է�");
		String code = in.nextLine();
		System.out.println("��ȭ �̸� �Է�");
		String mname = in.nextLine();
		System.out.println("���� �ð� �Է�");
		String stime = in.nextLine();
		System.out.println("���� �ð� �Է�");
		String etime = in.nextLine();
		
		MovieDTO m = new MovieDTO();
		m.setCode(code);
		m.setMname(mname);
		m.setStime(stime);
		m.setEtime(etime);
		mvdao.insertmv(m);
		
	}

	private boolean chkseatnum(String seat1, int seat2) {
		if(seat1.charAt(0)< 'A' || seat1.charAt(0)>'H') {
			System.out.println("�¼��� ���� �ٽ� �Է����ּ���");
			return true;
		} 
		if(seat2 <1 || seat2>20) {
			System.out.println("�¼��� ���� �ٽ� �Է����ּ���");
			return true;
		}
		return false;
	}
	
	
	private void mvList() {
		ArrayList<MovieDTO> mvlist = new ArrayList<>();
		mvlist=mvdao.selAll();
		for(MovieDTO m: mvlist) {
			System.out.println("�ڵ�:" + m.getCode());
			System.out.println("�̸�:" + m.getMname());
			System.out.println("���� �ð�:" + m.getStime());
			System.out.println("���� �ð�:" + m.getEtime());
			System.out.println("--------------------");
		}
	}
	
	private void tList() {
		ArrayList<TicketDTO> tlist = new ArrayList<>();
		tlist=tdao.selAll(mdto);
		for(TicketDTO t: tlist) {
			System.out.println("�ڵ�:" + t.getCode());
			System.out.println("���̵�:" + t.getId());
			System.out.println("�¼�:" + t.getSeat());
			System.out.println("--------------------");
		}
	}
	
}
