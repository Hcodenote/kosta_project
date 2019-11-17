package kosta.academy;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Manager {
	//private Student student = new Student();
	//private Worker worker = new Worker();
	private Subject subject = new Subject();
	private Member m = null; // user
	//private Login login = new Login();
	Scanner sc = new Scanner(System.in);

	public Manager() {
		
	}
	
	// 수정 부분
	public Manager(Member m) {
		this.m = m;
		subject.setSubject();
	}

	// 들을 수 있는 과정인지 체크
	public boolean overlapCourse(Member m, Subject subject) {
		if (m instanceof Worker) {
			if (subject.getCourse().equals("재직자")) {
				return true;
			}
		} else if (m instanceof Student) {
			if (subject.getCourse().equals("취업")) {
				return true;
			}
		}

		System.out.println("들을 수 없는 과정입니다");
		return false;

	}

	// 학생의 수강바구니에 입력된 과목이 있는지 확인
	public boolean overlapCheck(String code, List<Subject> list) {
		Iterator<Subject> iter = list.iterator();

		while (iter.hasNext()) {
			// 이미 신청한 과목인지 확인하기
			if (code.equals(iter.next().getCode())) {
				return true;
			}

		}
		return false;
	}

	// 수강을 변경할 과목의 위치(인덱스) 반환
	public int idxCheck(String code, List<Subject> list) {
		for (int i = 0; i < list.size(); i++) {
			if (code.equals(list.get(i).getCode())) {
				return i;
			}
		}
		return 0;
	}

	// 수강바구니가 가득 찼는지 체크
	public boolean fullCheck(List<Subject> list) {
		if (list.size() < 2)
			return true;
		else
			return false;
	}

	public void addSubject(String code, Member m) {
		int idx = -1;
		// 로그인 한 사람의 바구니 상태 확인

		if (!overlapCheck(code, m.getSubjectList())) {
			// 과목 리스트를 순차적으로 돌면서 일치하는 강의가 있는지 확인
			for (int i = 0; i < subject.getList().size(); i++) {
				if (code.equals(subject.getList().get(i).getCode())) {
					if (overlapCourse(m, subject.getList().get(i))) {
						System.out.println(subject.getList().get(i).getSubjectName() + " 수강신청 완료");
						m.addSubjectList(subject.getList().get(i));
						idx = 1; // 추가
					} else
						idx = 2; // 과정이 달라서 들을 수 없음
				}
			}
		} else {
			System.out.println("이미 신청한 과목입니다.");
			idx = 0;
		}
		if (idx == -1) {
			System.out.println("과목코드를 잘못 입력하셨습니다.");
		}
	}

	// 수강신청
	public void put() {

		if (fullCheck(m.getSubjectList())) {
			System.out.print("[수강신청] 과목 코드 입력: ");
			String code = sc.nextLine();
			addSubject(code, m);
		} else
			System.out.println("수강바구니가 가득 찼습니다.");
	}

	public void setSubject(String code, int codeIdx, Member m) {
		int idx = -1;
		// 로그인 한 사람의 바구니 상태 확인
		if (!overlapCheck(code, m.getSubjectList())) {
			// 과목 리스트를 순차적으로 돌면서 일치하는 강의가 있는지 확인
			for (int i = 0; i < subject.getList().size(); i++) {
				if (code.equals(subject.getList().get(i).getCode())) {
					System.out.println(m.getSubjectList().get(codeIdx).getSubjectName() + "을 "
							+ subject.getList().get(i).getSubjectName() + "(으)로 수강변경 완료");
					m.setSubjectList(codeIdx, subject.getList().get(i));
					idx = 1;
				}
			}
		} else {
			System.out.println("이미 신청한 과목입니다.");
			idx = 0;
		}
		if (idx == -1) {
			System.out.println("과목코드를 잘못 입력하셨습니다.");
		}
	}

	// 수강정정
	public void edit() {

		System.out.print("[수강변경] 변경을 원하는 과목 코드 입력: ");
		String code = sc.nextLine();
		int codeIdx = idxCheck(code, m.getSubjectList());
		int idx = -1;

		if (overlapCheck(code, m.getSubjectList())) {
			// 입력 과목이 학생의 수강바구니에 있다면
			System.out.print("[수강변경] 수강하고 싶은 과목 코드 입력: ");
			String code2 = sc.nextLine();
			setSubject(code2, codeIdx, m);
			idx = 1;

		} else {
			for (int i = 0; i < subject.getList().size(); i++) {
				if (code.equals(subject.getList().get(i).getCode())) {
					idx = 0;
					System.out.println("신청하지 않은 과목입니다.");
				}
			}

		}
		if (idx == -1) {
			System.out.println("과목코드를 잘못 입력하셨습니다.");
		}

	}

	// 과목삭제
	public void del() {
		System.out.print("[과목삭제] 과목 코드 입력: ");
		String code = sc.nextLine();
		int idx = -1;
		// 로그인 한 사람의 바구니 상태 확인

		if (overlapCheck(code, m.getSubjectList())) {
			for (int i = 0; i < m.getSubjectList().size(); i++) {
				if (code.equals(m.getSubjectList().get(i).getCode())) {
					System.out.println(m.getSubjectList().get(i).getSubjectName() + " 과목삭제 완료");
					m.delSubjectList(m.getSubjectList().get(i));
					idx = 1;
				}
			}
		} else {
			for (int i = 0; i < subject.getList().size(); i++) {
				if (!code.equals(subject.getList().get(i).getCode())) {
					idx = 0;
					System.out.println("신청하지 않은 과목입니다.");
				}
			}
		}
		if (idx == -1) {
			System.out.println("과목코드를 잘못 입력하셨습니다.");
		}

	}

	// 수강확인
	public void check() {

		if (m.getSubjectList().size() == 0) {
			System.out.println("수강신청한 과목이 없습니다.");
		} else {
			System.out.println("========================= 수강신청 목록 =========================");
			for (int i = 0; i < m.getSubjectList().size(); i++) {
				System.out.println("\t과목코드| " + m.getSubjectList().get(i).getCode() + "\t강사| "
						+ m.getSubjectList().get(i).getTeacher() + "\t과목| " + m.getSubjectList().get(i).getSubjectName()
						+ "\t과정| " + m.getSubjectList().get(i).getCourse());
			}
			System.out.println("============================================================");
		}

	}

	// 시간표
	public void show() {
		try {
			if (m.getSubjectList().size() == 0) {
				System.out.println("신청한 과목이 없습니다.");
			} else {
				write(m.getSubjectList());
				System.out.println("시간표 출력 완료");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그아웃
	
//	public void logout() {
//		System.out.println("로그아웃합니다.");
//		login.logout();
//
//	}
	
	

	public void read() {

	}

	public void write(List<Subject> list) {
		PrintWriter writer = null;
		char arr[] = new char[1024];

		try {
			System.out.print("파일 이름 입력: ");
			String fileName = sc.nextLine();
			writer = new PrintWriter(fileName + ".txt");

			for (int i = 0; i < m.getSubjectList().size(); i++) {
				writer.println("\t=============== 성적표 ===============");
				writer.printf("\t과목코드| %s\t강사| %s\t과목명| %s \t과정| %s", list.get(i).getCode(), list.get(i).getTeacher(),
						list.get(i).getSubjectName(), list.get(i).getCourse());
				writer.println("\t====================================");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e2) {
			}
		}
	}

	public void subjectPrint() {
		subject.print();
	}

}
