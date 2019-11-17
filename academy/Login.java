package kosta.academy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
	Scanner sc = new Scanner(System.in);
	//private List<Member> memberList = new ArrayList<Member>();
	//private List<Login> loginList = new ArrayList<Login>();

	private String memberNo;
	private String pw;
//	private Student student = new Student();
//	private Worker worker = new Worker();
	private Member m = null;

	public Login() {

//		copyList(student.setMember());
//		copyList(worker.setMember());
//		for (int i = 0; i < memberList.size(); i++) {
//			System.out.println(memberList.get(i).getMemberNo());
//		}
		//read();

		//setLoginInfo();
	}
	


	public Login(String memberNo, String pw) {
		super();
		this.memberNo = memberNo;
		this.pw = pw;
	}

//	public void copyList(List<Member> list) {
//		for (int i = 0; i < list.size(); i++) {
//			memberList.add(list.get(i));
//		}
//	}

	public Member overlapMember(String memberNo, List<Member> list) {

		for (int i = 0; i < list.size(); i++) {
			if (memberNo.equals(list.get(i).getMemberNo())) {
				System.out.println(list.get(i).getMemberName() + "님이 로그인 하셨습니다.");
				return list.get(i);
			}
		}
		System.out.println(">> 로그인 실패");
		return null;
	}

	public Login overlapMemberNo(String memberNo, List<Login> list) {

		for (int i = 0; i < list.size(); i++) {
			if (memberNo.equals(list.get(i).getMemberNo())) {
				// System.out.println(list.get(i).getMemberNo()+"님이 로그인 하셨습니다.");
				return list.get(i);
			}
		}
//		System.out.println("아이디 불일치");
		return null;
	}

	public boolean overlapPw(String pw, Login login) {
		if (pw.equals(login.getPw())) {
			return true;
		}
		System.out.println("비밀번호 불일치");
		return false;
	}

	public Member login(List<Member> memberList, List<Login> loginList) {
		do {
			System.out.println("============================ 로그인 ===========================");
			System.out.print("\t\t\t학번:");
			String memberNo = sc.nextLine();
			System.out.print("\t\t\t비밀번호: ");
			String pw = sc.nextLine();
			if (overlapMemberNo(memberNo, loginList) != null) {
				if (overlapPw(pw, overlapMemberNo(memberNo, loginList))) {
					m = overlapMember(memberNo, memberList);
				}
			} else
				System.out.println("아이디 불일치");
		} while (m == null);

		return m;
	}

	public void logout() {
		// write();
		System.out.println("로그아웃");
		m = null;
	}


	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	/////////////////////// 수강바구니 저장 객체파일 입출력

//	public void write() {
//
//		ObjectOutputStream oos = null;
//		try {
//			// 멤버 객체를 스트림에 전달 파일에 넣을거니까 FileOutputStream 부르기
//			oos = new ObjectOutputStream(new FileOutputStream("MemberInfo.ser"));
//			oos.writeObject(memberList); // 파일에 멤버 객체 씀
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				oos.close();
//			} catch (Exception e2) {
//			}
//		}
//	}
//
//	// 외부의 객체를 읽어 들이니까 역직렬화
//	public void read() {
//		ObjectInputStream ois = null;
//
//		try {
//			ois = new ObjectInputStream(new FileInputStream("MemberInfo.ser"));
//			memberList = (List<Member>) ois.readObject(); // 형변환 필요
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				ois.close();
//			} catch (Exception e2) {
//			}
//		}
//	}

}
