package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    // 500에러를 400으로 바꾸기
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView(); // 빈 ModelAndView 반환 -> 뷰를 렌더링하지 않고 정상 흐름으로 서블릿이 리턴됨
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
        // null을 반환하면 다음 ExceptionResolver를 찾아서 실행함
        // 처리할 수 있는 resolver가 없으면 예외처리가 되지 않고 기존에 발생한 예외를 서블릿 밖으로 던짐
    }
}
