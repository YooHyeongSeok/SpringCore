package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

    }

    @Test
    @DisplayName("빈 이름으로 조회 안됨")
    void findBeanByNameX() {
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class
                , () -> ac.getBean("xxxx", MemberService.class));
    }


}
