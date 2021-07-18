package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {

        //prototype scope 호출 확인
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean p1 = ac.getBean(PrototypeBean.class);
        p1.addCount();
        Assertions.assertThat(p1.getCount()).isEqualTo(1);

        PrototypeBean p2 = ac.getBean(PrototypeBean.class);
        p2.addCount();
        Assertions.assertThat(p2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonWithClientPrototypeTest1() {

        //client Bean 호출(내부 prototype bean 내부 호출)
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(2);

        //해당 prototype bean은 Client빈이 생성될때 주입되므로 같이 singleton유지
    }

    @Scope("prototype")
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean destroy");
        }
    }

    static class ClientBean {

        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;

        }

    }

}
