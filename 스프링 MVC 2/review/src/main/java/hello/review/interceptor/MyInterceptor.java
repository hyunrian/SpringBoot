package hello.review.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    private static final String LOG_ID = "logid";

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String requestURI = request.getRequestURI();
//
//        String uuid = UUID.randomUUID().toString(); // 요청 로그 구분
//        request.setAttribute(LOG_ID, uuid); // preHandle에서 지정한 값을 postHandle, afterCompletion에서 사용하려면 request에 담아야 함
//
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod hm = (HandlerMethod) handler;
//        }
//        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);
//
//        return true; // 다음 인터셉터나 컨트롤러 호출
//    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            log.info("로그인하지 않은 사용자가 요청함");
            // 로그인 페이지로 redirect
            response.sendRedirect("/user/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String requestURI = request.getRequestURI();
//        String logId = (String) request.getAttribute(LOG_ID);
//        log.info("RESPONSE [{}][{}]", logId, requestURI);
    }
}
