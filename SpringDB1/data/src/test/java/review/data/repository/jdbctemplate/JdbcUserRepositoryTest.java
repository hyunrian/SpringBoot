package review.data.repository.jdbctemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import review.data.domain.User;
import review.data.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JdbcUserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User user = new User("test", "0001112222");
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getUserName()).isEqualTo("test");
        assertThat(savedUser.getPhoneNum()).isEqualTo("0001112222");
    }

    @Test
    void update() {
        User user = new User("java", "0001112222");
        userRepository.save(user);
        user.setUserName("spring3");
        userRepository.update(user);

    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();

        System.out.println("users = " + users.toString());
        assertThat(users).contains(new User("spring", "0001112222"));
        assertThat(users).contains(new User("test", "0001112222"));
    }

    @Test
    void findById() {
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.orElse(null);
        assertThat(user.getUserName()).isEqualTo("test");
    }

    @Test
    void delete() {
        userRepository.delete(1L);
        User user = userRepository.findById(1L).orElse(null);
        assertThat(user).isNull();
    }

}