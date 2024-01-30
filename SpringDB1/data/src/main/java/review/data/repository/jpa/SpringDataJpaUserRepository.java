package review.data.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import review.data.domain.User;

import java.util.List;

public interface SpringDataJpaUserRepository extends JpaRepository<User, Long> {

    List<User> findByUserNameLike(String userName);

    @Query("select u from User u where u.userName like :userName")
    List<User> findWithCondition(@Param("userName") String userName);

}
