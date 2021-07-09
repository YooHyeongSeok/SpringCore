package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    /*DIP, OCP 위반으로 추상화에 의존하도록 변경*/

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자가 딱 1개만 있을때 생략 가능
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    /*
        수정자 주입
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        //주입할 대상이 없어도 동작 가능한 옵션
        @Autowired(required = false)
        public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
        @Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }

        //필드 주입
        //외부에서 변경이 불가능하여 테스트하기 힘들다.
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private DiscountPolicy discountPolicy;

        //일반 메서드 주입
        //일반적으로 잘 사용하지 않는다.
        @Autowired
        public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
             this.memberRepository = memberRepository;
             this.discountPolicy = discountPolicy;
         }
    */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //확인용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }


    //자동주입 옵션 처리
    @Autowired(required = false)
    void setNoBean1(Member member) {
        System.out.println("setNoBean1 :::" + member);
    }

    @Autowired
    void setNoBean2(@Nullable Member member) {
        System.out.println("setNoBean2 :::" + member);
    }

    @Autowired
    void setNoBean3(Optional<Member> member) {
        System.out.println("setNoBean3 :::" + member);
    }
}
