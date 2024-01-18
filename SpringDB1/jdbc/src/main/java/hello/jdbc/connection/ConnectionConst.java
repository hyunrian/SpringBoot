package hello.jdbc.connection;

public abstract class ConnectionConst { // 객체를 생성할 수 없도록 abstract로 작성
    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";
}
