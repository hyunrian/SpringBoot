package hello.review.user.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long id = 0L;

    public User join(User user) {
        user.setId(++id);
        store.put(user.getId(), user);
        return user;
    }
}
