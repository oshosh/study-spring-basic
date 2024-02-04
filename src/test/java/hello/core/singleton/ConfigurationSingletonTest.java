package hello.core.singleton;

import static org.assertj.core.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
  @Test
  void configurationTest(){
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 테스트용
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

    // 진짜
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();

    System.out.println("memberService -> memberRepository = " + memberRepository1); // memberService -> memberRepository = hello.core.member.MemoryMemberRepository@2320fa6f
    System.out.println("orderService -> memberRepository = " + memberRepository2); // orderService -> memberRepository = hello.core.member.MemoryMemberRepository@2320fa6f
    System.out.println("memberRepository = " + memberRepository); // memberRepository = hello.core.member.MemoryMemberRepository@2320fa6f

    // 모두 같은 인스턴스를 참고하고 있음
    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  void configurationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AppConfig bean = ac.getBean(AppConfig.class);

    // AppConfig는 상속 받는 AppConfig@CGLIB 라는 바이트코드 조작 라이브러리로 클래스를 상속 시킴
    System.out.println("bean = " + bean.getClass()); //bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$85eff902
  }
}
