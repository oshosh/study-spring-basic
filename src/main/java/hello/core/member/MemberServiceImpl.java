package hello.core.member;

// implements MemberService options + enter로 생성
public class MemberServiceImpl implements MemberService {
  private final MemberRepository memberRepository;
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }
  @Override
  public void join(Member member) {
      memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
