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
			System.out.println("1.영화 예매  2.예매 취소  3.예매 수정  4.예매 조회");
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
		System.out.println("수정할 영화의 코드를 입력해주세요");
		String code = in.nextLine();
		System.out.println("수정할 좌석의 행을 입력해주세요  A~H");
		String seat1 = in.nextLine();
		System.out.println("수정할 좌석의 열을 입력해주세요 1~20");
		int seat2 = in.nextInt();
		if(chkseatnum(seat1, seat2)) {
			return;
		}
		String seatnum= seat1 + seat2;
		tdao.update(mdto, seatnum,code);

	}
	private void del() {
		System.out.println("예매 최소를 하려는 영화의 코드를 입력해주세요");
		String code = in.nextLine();
		tdao.delete(code, mdto);
	}


	private void add() {
		mvdao.selAll();
		TicketDTO t =new TicketDTO();
		System.out.println("예매할 영화 코드를 입력해주세요");
		String code = in.nextLine();
		System.out.println("좌석의 행을 입력해주세요   A~H");
		String seat1 = in.nextLine();
		System.out.println("좌석의 열을 입력해주세요 1~20");
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
			System.out.println("1.상영표 등록  2.수정  3.삭제  4.고객 통합 정보 조회");
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
		System.out.println("검색할 고객의 아이디를 입력해주세요");
		String id = in.nextLine();
		MemberDTO m = new MemberDTO();
		m= mdao.sel(id);
		System.out.println("고객 아이디" + m.getId());
		System.out.println("고객 이름:" + m.getName());
		ArrayList<TicketDTO> tlist = new ArrayList<>();
		tlist=tdao.selAll(m);
		for(TicketDTO t : tlist) {
			System.out.println("영화 코드:" + t.getCode());
			System.out.println("영화 이름:" + mvdao.sel(t).getMname());
			System.out.println("좌석 번호:" + t.getSeat());
		}
	}	


	private void modM() {
		mvList();
		System.out.println("수정할 영화의 코드를 입력하세요");
		String code = in.nextLine();
		System.out.println("영화의 이름을 입력하세요");
		String mname = in.nextLine();
		System.out.println("영화 시작 시간을 입력하세요");
		String stime= in.nextLine();
		System.out.println("영화 종료 시간을 입력하세요");
		String etime = in.nextLine();
		mvdao.update(code,mname,stime,etime);
		
	}
	private void delM() {
		mvList();
		System.out.println("삭제할 영화의 코드를 입력하세요");
		String code = in.nextLine();
		mvdao.delete(code);
		
		
	}
	private void addM() {
		System.out.println("상영 코드 입력");
		String code = in.nextLine();
		System.out.println("영화 이름 입력");
		String mname = in.nextLine();
		System.out.println("시작 시간 입력");
		String stime = in.nextLine();
		System.out.println("종료 시간 입력");
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
			System.out.println("좌석의 행을 다시 입력해주세요");
			return true;
		} 
		if(seat2 <1 || seat2>20) {
			System.out.println("좌석의 열을 다시 입력해주세요");
			return true;
		}
		return false;
	}
	
	
	private void mvList() {
		ArrayList<MovieDTO> mvlist = new ArrayList<>();
		mvlist=mvdao.selAll();
		for(MovieDTO m: mvlist) {
			System.out.println("코드:" + m.getCode());
			System.out.println("이름:" + m.getMname());
			System.out.println("시작 시간:" + m.getStime());
			System.out.println("종료 시간:" + m.getEtime());
			System.out.println("--------------------");
		}
	}
	
	private void tList() {
		ArrayList<TicketDTO> tlist = new ArrayList<>();
		tlist=tdao.selAll(mdto);
		for(TicketDTO t: tlist) {
			System.out.println("코드:" + t.getCode());
			System.out.println("아이디:" + t.getId());
			System.out.println("좌석:" + t.getSeat());
			System.out.println("--------------------");
		}
	}
	
}
