package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
/*
 @ComponentScan : 특정 애너테이션이 있는 클래스를 스캔하여 스프링 빈으로 등록
 컴포넌트 스캔 기본 대상 : @Component, @Controller, @Service, @Repository, @Configuration
 애너테이션에는 상속관계가 없음. 애너테이션이 특정 애너테이션을 들고 있는 것을 인식할 수 있는 것은 자바가 아닌 스프링이 지원하는 기능임
 @Configuration이 붙은 것은 등록하지 않도록 설정(기존 코드 유지하기 위함)
 basePackage : 탐색 위치 지정(해당 패키지부터 하위 패키지)
 basePackage를 적용하지 않은 디폴트 값: @ComponentScan 클래스의 package부터 하위 패키지(권장. 설정 정보의 클래스 위치를 프로젝트 최상단에 두기)

@Controller : 스프링 MVC 컨트롤러로 인식
@Repository : 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환
@Configuration : 스프링 설정 정보로 인식하고 스프링 빈이 싱글톤을 유지하도록 추가 처리
@Service : 특별한 처리를 하지 않으나 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나 하며 비즈니스 계층을 인식하는데 도움
 */
public class AutoAppConfig {

}
