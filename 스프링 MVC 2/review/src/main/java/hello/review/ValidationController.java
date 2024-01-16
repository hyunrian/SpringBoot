package hello.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ValidationController {

    private Validation validation = new Validation();
    @Autowired private MessageSource ms;

    @GetMapping("/test")
    public String setValues(@Validated @ModelAttribute Validation validation, BindingResult bindingResult) {
        log.info("validation={}", validation);
        if (bindingResult.hasErrors()) {
//            log.info("errors={}", bindingResult);
            log.info("properties test={}", ms.getMessage("hello2", null, null));
            log.info("error message test={}", ms.getMessage("NotNull", null, null));
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.info("errorMessage={}", fieldError.getDefaultMessage());
            }
        }
        return "OK";
    }
}
