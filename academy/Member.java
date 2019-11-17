package kosta.academy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract public class Member implements Serializable {
	private String memberNo;
	private String memberName;
	private String pw;
	protected List<Subject> subjectList = new ArrayList<Subject>(); // 개인 수강바구니

	public Member() {
	}

	public Member(String memberNo, String memberName, String pw) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.pw = pw;
	}

	public void delSubjectList(Subject subject) {
		subjectList.remove(subject);
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public void addSubjectList(Subject subject) {
		subjectList.add(subject);
	}

	public void setSubjectList(int codeIdx, Subject subject) {
		subjectList.set(codeIdx, subject);
	}

	public String getMemberNo() {
		return memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	abstract public List<Member> setMember();
}