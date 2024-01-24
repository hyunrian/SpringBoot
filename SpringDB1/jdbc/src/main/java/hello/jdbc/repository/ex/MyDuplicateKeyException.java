package hello.jdbc.repository.ex;

/**
 * 키 중복 예외
 * 데이터 중복인 경우에만 던질 예외
 */
public class MyDuplicateKeyException extends MyDbException{
    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
