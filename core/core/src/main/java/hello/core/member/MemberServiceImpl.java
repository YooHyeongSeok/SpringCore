package hello.core.member;

public class MemberServiceImpl implements MemberService {

    /*DIP, OCP 위반으로 추상화에 의존하도록 변경*/
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
    
    //확인용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
