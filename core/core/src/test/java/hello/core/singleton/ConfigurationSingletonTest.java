package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {

        ApplicationContext ac = new
                AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService",
                MemberServiceImpl.class);

        OrderServiceImpl orderService = ac.getBean("orderService",
                OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository",
                MemberRepository.class);


        //3개 다 같은 인스턴스
        System.out.println("memberservice -> memberRepository :::" + memberService.getMemberRepository());
        System.out.println("orderService -> memberRepository :::" + orderService.getMemberRepository());
        System.out.println("memberRepository -> memberRepository :::" + memberRepository);

        /*AppConfig log 확인 memberRepository는 한번 호출
        call AppConfig.memberService
        call AppConfig.memberRepository
        call AppConfig.orderService
        */
    }
}
