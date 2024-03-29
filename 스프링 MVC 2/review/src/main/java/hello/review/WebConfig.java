package hello.review;

import hello.review.interceptor.MyInterceptor;
import hello.review.user.login.argumentresolver.LoginUserArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        log.info("addInterceptors 실행");
//        registry.addInterceptor(new MyInterceptor())
//                .order(1) // 낮을수록 먼저 호출됨
//                .addPathPatterns("/**")
//                .excludePathPatterns("/", "/user/join", "/user/login", "/user/logout", "/css/**", "/*.ico", "/error");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserArgumentResolver());
    }
}
