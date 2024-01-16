package hello.review;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidationTest {

    Validation validation = new Validation();

    @Test
    void test() {
        validation.setMaxVal(100000);
        validation.setMinVal(0);
        validation.setRangeVal(3);
        System.out.println("validation = " + validation);
    }
}
