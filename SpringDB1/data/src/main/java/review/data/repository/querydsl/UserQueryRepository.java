package review.data.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import review.data.domain.User;
import review.data.repository.UserSearchCondition;

import java.util.List;

import static review.data.domain.QUser.*;

@Repository
public class UserQueryRepository {

    private final JPAQueryFactory query;

    public UserQueryRepository(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<User> findAll(UserSearchCondition condition) {

        String userName = condition.getUserName();

        return query
                .select(user) //QUser.user -> user (static import)
                .from(user)
                .where(likeUserName(userName)) //조건이 여러개인 경우 where(method1(), method2(), ..)로 사용(and 조건으로 처리). 값이 null이면 해당 조건이 무시됨
                .fetch();
    }

    private BooleanExpression likeUserName(String userName) {
        if (StringUtils.hasText(userName)) {
            return user.userName.like("%" + userName + "%");
        }
        return null;
    }
}
