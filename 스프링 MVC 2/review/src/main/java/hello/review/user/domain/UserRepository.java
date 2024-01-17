package hello.review.user.domain;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static long id = 0L;

    public User join(User user) {
        user.setId(++id);
        store.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<User> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(u -> u.getUserid().equals(loginId))
                .findAny();
    }
}
