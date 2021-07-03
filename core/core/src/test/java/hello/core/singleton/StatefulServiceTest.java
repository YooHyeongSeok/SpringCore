package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StatefulServiceTest {

    @Test
    void statefulServiceTest() {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService = ac.getBean("stateFulService", StateFulService.class);
        StateFulService stateFulService2 = ac.getBean("stateFulService", StateFulService.class);

        //A사용자 주문
        stateFulService.order("userA", 10000);
        //B사용자 주문
        stateFulService2.order("userB", 20000);

        //A사용자한테 2만원이 표출되는 현상 발생
        int price = stateFulService.getPrice();
        System.out.println("price :::" + price);

        Assertions.assertThat(stateFulService.getPrice()).isEqualTo(20000);

    }


}
