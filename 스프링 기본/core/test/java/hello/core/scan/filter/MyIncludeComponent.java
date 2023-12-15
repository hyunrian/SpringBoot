package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent { // @MyIncludeComponent가 붙은 것을 컴포넌트 스캔 요소에 추가
}
