package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링에서 설정 정보 저장 클래스를 의미하는 애너테이션
public class AppConfig { // 사용할 객체를 설정할 클래스. 애플리케이션의 실제 동작에 필요한 구현 객체를 생성함

    // 생성자를 통한 주입 연결
    // 역할에 대한 구분이 한 눈에 들어오도록 작성하는 것이 좋음
    // -> 애플리케이션의 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있음

    // MemoryMemberRepository가 결과적으로 3번 생성되지만 테스트해보면 모두 같은 객체임
    // @Configuration이 없으면 call AppConfig.memberRepository가 3번 호출(객체 3개 생성. 싱글톤 보장X)되며 스프링 컨테이너에서 관리하지 않음

    @Bean // 스프링 컨테이너에 등록
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
