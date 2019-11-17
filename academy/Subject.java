package kosta.academy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Subject  implements Serializable{

	private List<Subject> list = new ArrayList<Subject>();

	private String teacher;
	private String subjectName;
	private String code;
	private String course;

	public Subject() {
	}

	public Subject(String code, String teacher, String subjectName, String course) {
		super();
		this.code = code;
		this.teacher = teacher;
		this.subjectName = subjectName;
		this.course = course;
	}

	public void setSubject() {

		list.add(new Subject("31857", "서창훈", "Java", "취업"));
		list.add(new Subject("32751", "김모씨", "C++", "취업"));
		list.add(new Subject("31243", "박모씨", "Python", "취업"));
		list.add(new Subject("34283", "최모씨", "DB", "재직자"));
		list.add(new Subject("38663", "이모씨", "SQL", "재직자"));
		list.add(new Subject("39971", "하모씨", "AI", "재직자"));
	}

	public void print() {
		// setSubject();
		System.out.println("\t\t\t\t<수강신청 과목리스트>\t\t\t");
		for (int i = 0; i < list.size(); i++) {
			System.out.print("\t과목코드| " + list.get(i).code + "\t강사| " + list.get(i).teacher + "\t과목| "
					+ list.get(i).subjectName + "\t");
			if(list.get(i).subjectName.length() < 5) {
				System.out.print("\t");
			}
			System.out.println("\t과정|" + list.get(i).course);
			
		}
		System.out.println("============================================================");
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Subject> getList() {
		return list;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setList(List<Subject> list) {
		this.list = list;
	}

}
