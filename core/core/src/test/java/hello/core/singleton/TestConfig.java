package hello.core.singleton;

import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public StateFulService stateFulService(){
        return new StateFulService();
    }

}
