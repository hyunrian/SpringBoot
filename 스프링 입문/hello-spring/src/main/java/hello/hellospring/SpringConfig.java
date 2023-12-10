package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // 코드로 직접 스프링 빈 등록하기(@Service, @Repository 없이 여기에 등록시킴. @Controller는 해줘야 함)
    // 이렇게 하면 향후 상황에 따라 구현 클래스를 변경해야 할 때 용이

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
