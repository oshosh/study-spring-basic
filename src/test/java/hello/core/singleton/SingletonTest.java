package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

  @Test
  @DisplayName("스프링 없는 순수한 DI 컨테이너")
  void pureContainer() {
    AppConfig appConfig = new AppConfig();
    // 1. 조회 : 호출할 때 마다 객체를 생성
    MemberService memberService1 = appConfig.memberService();
    // 2. 조회 : 호출할 때 마다 객체를 생성
    MemberService memberService2 = appConfig.memberService();

    // 참조 값이 다른 것을 확인
    System.out.println("memberService1 = " + memberService1); // memberService1 = hello.core.member.MemberServiceImpl@22f31dec
    System.out.println("memberService2 = " + memberService2); // memberService2 = hello.core.member.MemberServiceImpl@45312be2

    // memberService 1 과 memberService2는 서로 달라야 한다.
    assertThat(memberService1).isNotSameAs(memberService2);
  }

  @Test
  @DisplayName("싱글톤 패턴을 적용한 객체 사용")
  void singletonServiceTest() {
    SingletonService instance1 = SingletonService.getInstance();
    SingletonService instance2 = SingletonService.getInstance();

    System.out.println("instance1 = " + instance1);
    System.out.println("instance2 = " + instance2);

    assertThat(instance1).isSameAs(instance2);
  }

  @Test
  @DisplayName("스프링 컨테이너와 싱글톤")
  void springContainer(){
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService1 = ac.getBean("memberService", MemberService.class);
    MemberService memberService2 = ac.getBean("memberService", MemberService.class);

    // springContainer 활용한 경우 싱글톤 패턴이 적용 됨
    // 빈 스코프를 쓰면 요청 시 새로운 객체를 생성해서 반황 하기도 함
    System.out.println("memberService1 = " + memberService1); // memberService1 = hello.core.member.MemberServiceImpl@53f0a4cb
    System.out.println("memberService2 = " + memberService2); // memberService2 = hello.core.member.MemberServiceImpl@53f0a4cb

    assertThat(memberService1).isSameAs(memberService2);
  }
}
