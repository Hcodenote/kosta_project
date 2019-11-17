package kosta.academy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student extends Member {
	private String universe;
   
   
   public Student() {}
   public Student(String memberNo, String memberName, String universe, String pw) {
      super(memberNo,memberName, pw);
      this.universe = universe;
   }

   @Override
   public List<Member> setMember() {
      System.out.println("Student setMember");
      List<Member> stList = new ArrayList<Member>();
      stList.add(new Student("201349246","이지은", "코스타대학교", "1111"));
      stList.add(new Student("201462266", "라일현", "안녕대학교", "2222"));
      return stList;

   }
   
}