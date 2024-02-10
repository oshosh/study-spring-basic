package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
  private int discountPercent = 10;
  @Override
  public int discount(Member member, int price) {
    // cmd + shift + t => test코드 생성하도록 도와줌
    if(member.getGrade() == Grade.VIP){
      return price * discountPercent / 100;
    }else{
      return 0;
    }
  }
}
