package review.data.repository.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import review.data.domain.User;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;
import review.data.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class SpringDataJpaUserRepositoryTest {

    @Autowired
    UserService service;

    @Test
    void save() {
        User user = new User("star", "0121");
        User savedUser = service.save(user);

        assertThat(savedUser.getUserName()).isEqualTo("star");
        assertThat(savedUser.getPhoneNum()).isEqualTo("0121");
    }

    @Test
    void update() {
        User user = new User("java", "0001112222");
        User savedUser = service.save(user);

        service.update(savedUser.getId(), new UserUpdateDto("new", "11"));
        User foundUser = service.findById(savedUser.getId()).orElse(null);

        assertThat(foundUser.getUserName()).isEqualTo("new");
        assertThat(foundUser.getPhoneNum()).isEqualTo("11");
    }

    @Test
    void findAll() {
        User user1 = new User("user1", "1");
        User user2 = new User("user2", "2");
        User savedUser1 = service.save(user1);
        User savedUser2 = service.save(user2);
        List<User> users = service.findAll(new UserSearchCondition("se"));

        System.out.println("users = " + users.toString());
        assertThat(users).contains(savedUser1);
        assertThat(users).contains(savedUser2);
    }

    @Test
    void findById() {
        User user = new User("cckk", "038");
        User savedUser = service.save(user);
        User foundUser = service.findById(savedUser.getId()).orElse(null);
        assertThat(foundUser.getUserName()).isEqualTo("cckk");
    }

    @Test
    void delete() {
        User user = new User("cckk", "038");
        User savedUser = service.save(user);
        service.delete(savedUser.getId());
        User deletedUser = service.findById(1L).orElse(null);
        assertThat(deletedUser).isNull();
    }

}