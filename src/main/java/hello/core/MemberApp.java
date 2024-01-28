package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

  public static void main(String[] args) {
    MemberService memberService = new MemberServiceImpl();
    Member member = new Member(1L, "memberA", Grade.VIP); // cmd + option + v 변수 생성
    memberService.join(member);
    System.out.println("new member = " + member.getName());

    Member findMember = memberService.findMember(1L);
    System.out.println("findMember = " + findMember.getName() );
  }
}
