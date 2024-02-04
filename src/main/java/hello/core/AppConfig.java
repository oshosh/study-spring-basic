package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 예상 호출
// call AppConfig.memberService
// call AppConfig.memberRepository
// call AppConfig.memberRepository
// call AppConfig.orderService
// call AppConfig.memberRepository

// 실제 호출
// call AppConfig.memberService
// call AppConfig.memberRepository
// call AppConfig.orderService
@Configuration
public class AppConfig {
  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  // cmd + option + m하면 추출하여 리펙토링이 가능
  @Bean
  public MemoryMemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository(); // 두번 호출 됨 (memberService, orderService) -> 싱글톤이 깨지는 것 처럼 보임. 그러나 그렇지 않음 전부 같음
  }

  @Bean
  public OrderService orderService(){
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy(){
//    return new FixDiscountPolicy();
    return new RateDiscountPolicy();
  }

}