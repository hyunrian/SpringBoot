package hello.exception.api;

import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ApiExceptionV3Controller {

    // @ExceptionHandler: 해당 컨트롤러에서 처리하고 싶은 예외를 지정하면 됨
    // 지정한 예외 또는 그 자식 클래스를 모두 처리할 수 있음
    // @ExceptionHandler({AEx.class, BEx.class})로 다양한 예외 한번에 처리 가능
    // ()안 예외를 생략하면 메서드 파라미터의 예외가 지정됨

    @GetMapping("/api3/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello " + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }
}
