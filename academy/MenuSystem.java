package kosta.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class MenuSystem {
	Scanner sc = new Scanner(System.in);
	private Login login = new Login();
	private Member m; // 멤버 변수 공용으로 사용
	private List<Member> memberList = new ArrayList<Member>();
	private List<Login> loginList = new ArrayList<Login>();
	private Student student = new Student();
	private Worker worker = new Worker();
	private Subject subject = new Subject();
	
	// 생성자
	public MenuSystem() {
//		copyList(student.setMember());
//		copyList(worker.setMember());

		//write();
		read();
		//subject.setSubject();
		setLoginInfo();
		System.out.println("<저장된 정보>");
		
		for(int i = 0; i <loginList.size(); i++) {
			System.out.println("ID: "+ loginList.get(i).getMemberNo());
			System.out.println("PW: "+ loginList.get(i).getPw());
		}
	}
	
	public void copyList(List<Member> list) {
	for (int i = 0; i < list.size(); i++) {
		memberList.add(list.get(i));
	}
}
	
	
	public void setLoginInfo() {
		for (int i = 0; i < memberList.size(); i++) {
			loginList.add(new Login(memberList.get(i).getMemberNo(), memberList.get(i).getPw()));
		}
	}

	// 회원 가입
	public void join() {
		System.out.println("=========================== 회원가입 ==========================");
		String memberNo = "";
		String memberName = "";
		String course = "";
		String pw ="";

		// 학번
		while (true) {
			System.out.print("학번 입력: (숫자 9자리)");
			memberNo = sc.nextLine();
			if (login.overlapMemberNo(memberNo, loginList) == null) {
				if (memberNo.matches("[0-9]{9}")) {
					break;
				} else
					System.out.println("학번은 숫자 9자리로 구성되어 있습니다.");
			} else
				System.out.println("학번이 존재합니다.");
		}

		// 비밀번호
		while (true) {
			System.out.print("비밀번호 입력: (알파벳/숫자 4-10자리)");
			pw = sc.nextLine();
			if (pw.matches("[\\w]{4,10}")) {
				break;
			} else
				System.out.println("비빌번호는 알파벳/숫자 4자리부터 10자리로 구성되어 있습니다. ");
		}

		// 이름
		while (true) {
			System.out.print("이름 입력: ");
			memberName = sc.nextLine();
			if (memberName.matches("[가-힣]{2,5}")) {
				break;
			} else
				System.out.println("이름은 한글로 2~5글자만 가능합니다.");
		}

		// 재직자/학생 구분
		System.out.println("과정을 선택해주세요. (취업/재직자)");

		while (true) {
			System.out.println("1. 재직자 2. 학생");
			System.out.print("입력: ");
			String menu = sc.nextLine();

			switch (menu) {
			case "1":
				System.out.print("회사명 입력: ");
				String company = sc.nextLine();
				memberList.add(new Worker(memberNo, memberName, company, pw));
				loginList.add(new Login(memberNo, pw));
				return;
			case "2":
				System.out.print("대학교명 입력: ");
				String universe = sc.nextLine();
				memberList.add(new Student(memberNo, memberName, universe, pw));
				loginList.add(new Login(memberNo, pw));
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}
		}
	}
	
	
	// 직렬화
	public void write() {

		ObjectOutputStream oos = null;
		try {
			// 멤버 객체를 스트림에 전달 파일에 넣을거니까 FileOutputStream 부르기
			oos = new ObjectOutputStream(new FileOutputStream("MemberInfo.ser"));
			oos.writeObject(memberList); // 파일에 멤버 객체 씀
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (Exception e2) {
			}
		}
	}

	// 외부의 객체를 읽어 들이니까 역직렬화
	public void read() {
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("MemberInfo.ser"));
			memberList = (List<Member>) ois.readObject(); // 형변환 필요

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (Exception e2) {
			}
		}
	}
	
	public  void Applymenu() {
		Scanner sc = new Scanner(System.in);
		Manager manager = new Manager(m);
		System.out.println("============================================================");
		manager.subjectPrint();
		while(true) {
			
			System.out.println("\t1. 수강신청 2. 수강정정 3. 과목삭제 4. 수강확인 5. 시간표출력 6. 종료");
			System.out.print("\t메뉴 입력: ");
			String menu = sc.nextLine();
			
			switch (menu) {
			case "1": // 수강신청
				manager.put();
				break;
			case "2": // 수강정정
				manager.edit();
				break;
			case "3": // 과목삭제
				manager.del();
				break;
			case "4": // 수강확인
				manager.check();
				break;
			case "5": // 시간표출력
				manager.show();
				break;
			case "6": // 종료
				System.out.println("수강신청을 종료합니다.");
				login.logout();
				return;
				
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
		}
	}
	public void LoginMenu() {
		while(true) {			
			System.out.println("=========================== 회원관리 ==========================");
			System.out.println("\t\t1. 로그인, 2.회원가입, 3.프로그램 종료");
			System.out.print("\t\t메뉴입력: ");
			String menu = sc.nextLine();

			switch (menu) {
			case "1" :
				m = login.login(memberList, loginList);
				Applymenu();
				break;
			case "2" :
				join();
				write();
				break;
			case "3" :
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
				break;
			}
		}
		
	}
	

}
