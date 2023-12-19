package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest3 { // Provider로 PrototypeBean 생성하기(build.gradle 설정 추가 필기)
    // 자바 표준이며 스프링프레임워크에 의존적이지 않음 -> 스프링이 아닌 다른 컨테이너에서도 사용 가능
    // PrototypeBean: 매번 사용할 때마다 의존관계 주입이 완료된 새로운 객체가 필요할 때 사용
    // 실무에서는 SingletonBean으로 대부분의 문제를 해결할 수 있음
    // ObjectProvider, Provider 등은 프로토타입 뿐만 아니라 DL(Dependency Lookup)이 필요한 경우에는 언제든지 사용 가능

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); // 한번에 등록하기
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        assertThat(count2).isEqualTo(1); // 항상 새로운 prototypeBean이 생성됨
    }

    @Scope("singleton") // 디폴트값이므로 "singleton" 생략 가능
    static class ClientBean {

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
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
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
