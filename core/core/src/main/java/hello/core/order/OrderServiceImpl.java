package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

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
