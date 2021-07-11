package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService{

    /*
    * 의존주입 충돌날 경우 필드 주입
    * 필드명으로 주입 가능
        @Autowired
        private MemberRepository memberRepository;
        @Autowired
        private  DiscountPolicy rateDiscountPolicy;
    * */

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository
            ,@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    /*
        수정자 자동주입 예시
    */
    @Autowired
    public DiscountPolicy setDiscountPolicy(@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        return discountPolicy;
    }

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
