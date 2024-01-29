package review.data.repository.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import review.data.domain.User;
import review.data.repository.UserRepository;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user); //테이블에 객체 저장
        return user;
    }

    @Override
    public void update(Long id, UserUpdateDto userDto) {
        User user = em.find(User.class, id);
        user.setUserName(userDto.getUserName());
        user.setPhoneNum(userDto.getPhoneNum());
        //entity의 변경을 기반으로 transaction이 커밋되는 시점에 db에 반영됨(update)
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll(UserSearchCondition searchCondition) {
        String jpql = "select u from User u";

        String userName = searchCondition.getUserName();

        if (StringUtils.hasText(userName)) {
            jpql += " where u.userName like concat('%', :userName, '%')";
        }

        TypedQuery<User> query = em.createQuery(jpql, User.class);

        if (StringUtils.hasText(jpql)) {
            query.setParameter("userName", userName);
        }

        return query.getResultList();
    }

    @Override
    public void delete(Long id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }
}
