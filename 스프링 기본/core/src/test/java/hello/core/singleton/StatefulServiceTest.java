package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    /*
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // threadA : 사용자A가 10,000원 주문
        statefulService1.order("userA", 10000);
        // threadB : 사용자B가 20,000원 주문
        statefulService2.order("userB", 20000);

        // threadA : 사용자A의 주문 사용 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 20000

        // service1과 service2는 변수명만 다를 뿐 같은 객체임

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }
     */

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // threadA : 사용자A가 10,000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // threadB : 사용자B가 20,000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // threadA : 사용자A의 주문 사용 금액 조회
        System.out.println("price = " + userAPrice); // 20000

        Assertions.assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}