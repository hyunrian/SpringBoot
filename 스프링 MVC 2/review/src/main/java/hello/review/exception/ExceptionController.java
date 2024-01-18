package hello.review.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/ex")
    public void error() {
        throw new RuntimeException("exception!");
    }

    @GetMapping("/404")
    public void error2(HttpServletResponse response) throws IOException {
        response.sendError(404);
    }

    @GetMapping("/500")
    public void error3(HttpServletResponse response) throws IOException {
        response.sendError(500);
        int status = response.getStatus();
        log.info("status={}", status);
    }
}
