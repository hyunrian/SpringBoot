package review.data.repository;

import review.data.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    void update(Long id, UserUpdateDto userDto);

    Optional<User> findById(Long id);

    List<User> findAll(UserSearchCondition searchCondition);

    void delete(Long id);

}
