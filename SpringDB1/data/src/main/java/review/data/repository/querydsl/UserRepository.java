package review.data.repository.querydsl;

import org.springframework.data.jpa.repository.JpaRepository;
import review.data.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
