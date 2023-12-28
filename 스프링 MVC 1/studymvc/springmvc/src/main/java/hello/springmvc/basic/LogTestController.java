package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());
    // -> lombok의 @Slf4j로 바로 설정 가능

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 로그의 레벨을 설정하여 확인 가능(trace > debug > info > warn > error)
        // application.properties에서 설정 가능
        log.trace("trace log={}", name); // 로컬 pc 등
        log.debug("debug log={}", name); // 개발 서버에서 주로 설정하는 레벨
        log.info("info log={}", name); // 운영 서버에서 주로 설정하는 레벨
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        /**
         * 로그를 사용하면 스레드 정보, 클래스 이름 등 부가 정보를 함께 볼 수 있고 출력 모양 조정이 가능
         * 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영 서버에서는 출력하지 않는 등 상황에 맞게 설정 가능(application.properties)
         * 콘솔 출력 뿐만 아니라 파일이나 네트워크 등 로그를 별도의 위치에 남길 수 있음
         * 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그 분할이 가능
         * 성능도 System.out보다 좋음
         * log.debug("data=" + data)처럼 연산자를 사용한 출력을 하면 안됨!
         */

        return "OK";
    }
}
