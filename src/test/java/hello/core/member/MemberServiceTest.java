package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


// cmd + e 이전 히스토리로 볼 수 있음
public class MemberServiceTest {
  MemberService memberService;
  @BeforeEach
  public void beforeEach(){
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }
  @Test
  void join(){
    //given
    Member member = new Member(1L, "memberA", Grade.VIP);

    //when
    memberService.join(member);
    Member findeMember = memberService.findMember(1L);

    //then
    Assertions.assertThat(member).isEqualTo(findeMember);
  }
}
