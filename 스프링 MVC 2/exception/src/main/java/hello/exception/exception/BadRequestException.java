package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 요청 오류입니다. 메시지 사용!")
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad") // messages.properties에 등록하여 사용 가능
public class BadRequestException extends RuntimeException {
    // @ResponseStatus: 예외에 따라 http 상태 코드를 지정하는 역할
    // 원래 500 오류가 뜨는 것을 400(Bad request)으로 뜨도록 설정
}

