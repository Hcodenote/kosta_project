package kosta.academy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Member{
	private String company;
   
   public Worker() {}
   
   public Worker(String memberNo,  String memberName, String company, String pw) {
      super(memberNo,memberName, pw);
      this.company = company;
   }
   
   

   @Override
   public List<Member> setMember() {
     System.out.println("Worker setMember");
     List<Member> list = new ArrayList<Member>();
      list.add(new Worker("201421493", "송아라", "kosta", "3333"));
      list.add(new Worker("201564312", "안희민", "KT", "4444"));

      return list;
   }

}