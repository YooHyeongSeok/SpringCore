package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {

        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할때마다 객체 생성
        MemberService memberService = appConfig.memberService();
        //2. 조회 : 호출할때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른지 확인.
        System.out.println("memberservice1 ::: " + memberService);
        System.out.println("memberservice2 ::: " + memberService2);

        assertThat(memberService).isNotSameAs(memberService2);
        //메모리 낭비
        //객체 1개 생성 및 공유하도록 설계

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //생성자 생성못하도록 방어 해놓음.
        //new SingletonService();

        //1. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService = SingletonService.getInstance();
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값 같은지 확인.
        System.out.println("single1 :::" + singletonService);
        System.out.println("single2 :::" + singletonService2);
        /*single1 :::hello.core.singleton.SingletonService@34e9fd99
        single2 :::hello.core.singleton.SingletonService@34e9fd99*/

        assertThat(singletonService).isSameAs(singletonService2);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 :::" + memberService);
        System.out.println("memberService2 :::" + memberService);

        assertThat(memberService).isSameAs(memberService2);

    }


}
