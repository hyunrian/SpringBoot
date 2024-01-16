package hello.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MessageSourceTest {

    @Autowired MessageSource ms;

    @Test
    void getMessage() {
        String message = ms.getMessage("hello", null, null);
        System.out.println("message = " + message);
    }

}
