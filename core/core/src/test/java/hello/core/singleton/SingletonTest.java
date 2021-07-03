package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        System.out.println("memberservice1 ::: "+ memberService);
        System.out.println("memberservice2 ::: "+ memberService2);

        assertThat(memberService).isNotSameAs(memberService2);
        //메모리 낭비
        //객체 1개 생성 및 공유하도록 설계

    }
}
