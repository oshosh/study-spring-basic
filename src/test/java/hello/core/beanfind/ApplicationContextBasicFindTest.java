package hello.core.beanfind;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  /*
  * 빈 이름으로 조회 vs 구체 타입으로 조회 시 빈 이름으로 조회와 같은 MemberService.class의 인터페이스로 스프링 컨테이너에 등록하는게 일반적으로 좋다
  * MemberServiceImpl.class 경우는 구현체이기 때문에 구현체가 변경 시 문제가 발생하고 인터페이스는 구현에 의존하지 않기 때문이다.
  * */
  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName(){
    MemberService memberService = ac.getBean("memberService", MemberService.class);
//    System.out.println("memberService = " + memberService);
//    System.out.println("memberService.getClass() " + memberService.getClass());
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByName2(){
    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }

  /*
  * "빈 이름으로 조회X" vs "이름 없이 타입으로만 조회"
  * 이름을 이상하게 조회 하는건 불가하지만 이름 없이 조회는 가능하다.
  * 근데 타입으로 조회 시에 스프링 빈에 둘 이상이면 혼란스러워 하기 때문에 이름을 지정해서 조회하는게 가장 나은 방법이다.
  * */
  @Test
  @DisplayName("빈 이름으로 조회X")
  void findBeanByNameX(){
    // ac.getBean("xxx", MemberService.class); // org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxx' available
    assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxx", MemberService.class));
  }

  @Test
  @DisplayName("이름 없이 타입으로만 조회")
  void findBeanByType(){
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
  }
}
