package hello.core.order;

import hello.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class OrderServiceImplTest {

    @Test
    void testOrderService() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        /*
        fixDiscountPolicy,rateDiscountPolicy구현체에 둘다 @Component 설정
        org.springframework.beans.factory.UnsatisfiedDependencyException: No qualifying bean of type 'hello.core.discount.DiscountPolicy' available:
        expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy*/
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

    }

}