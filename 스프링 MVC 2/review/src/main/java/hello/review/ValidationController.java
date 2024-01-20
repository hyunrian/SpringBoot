package hello.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class ValidationController {

//    private Validation validation = new Validation();
    @Autowired private MessageSource ms;

    @GetMapping("/test")
    public String view(Model model) {
        Validation validation = new Validation();
        model.addAttribute("validation", validation);
        return "test/validationtest";
    }

    @PostMapping("/test")
    public String setValues(@Validated @ModelAttribute Validation validation, BindingResult bindingResult) {
        log.info("validation={}", validation);
        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
//            log.info("properties test={}", ms.getMessage("hello2", null, null));
            log.info("MessageSource NotNull message={}", ms.getMessage("NotNull.validation.minVal", null, null));
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.info("fieldError.getDefaultMessage={}", fieldError.getDefaultMessage());
                /**
                 * 에러 메시지는 타임리프에서, 그리고 컨트롤러(또는 API)에서의 처리방식이 다름
                 * 컨트롤러에서 메시지 확인은 ms.getMessage(code, args, locale)로만 확인이 가능하며
                 * fieldError.getDefaultMessage()로는 직접 설정한 메시지를 확인할 수 없음
                 * 타임리프는 MessageSource를 사용하여 메시지를 처리하기 때문에
                 * errors.properties에서 설정한 메시지를 바로 사용함
                  */
            }
        }
        return "test/validationtest";
    }


}
