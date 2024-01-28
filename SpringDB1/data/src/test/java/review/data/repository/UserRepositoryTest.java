package review.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import review.data.domain.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User user = new User("star", "0121");
        User savedUser = userRepository.save(user);

        assertThat(savedUser.getUserName()).isEqualTo("star");
        assertThat(savedUser.getPhoneNum()).isEqualTo("0121");
    }

    @Test
    void update() {
        User user = new User("java", "0001112222");
        User savedUser = userRepository.save(user);
        UserUpdateDto userDto = new UserUpdateDto("new java", savedUser.getPhoneNum());
        userRepository.update(savedUser.getId(), userDto);

    }

    @Test
    void findAll() {
        User user1 = new User("user1", "1");
        User user2 = new User("user2", "2");
        User savedUser1 = userRepository.save(user1);
        User savedUser2 = userRepository.save(user2);
        List<User> users = userRepository.findAll(new UserSearchCondition("se"));

        System.out.println("users = " + users.toString());
        assertThat(users).contains(savedUser1);
        assertThat(users).contains(savedUser2);
    }

    @Test
    void findById() {
        User user = new User("cckk", "038");
        User savedUser = userRepository.save(user);
        User foundUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertThat(foundUser.getUserName()).isEqualTo("cckk");
    }

    @Test
    void delete() {
        User user = new User("cckk", "038");
        User savedUser = userRepository.save(user);
        userRepository.delete(savedUser.getId());
        User deletedUser = userRepository.findById(1L).orElse(null);
        assertThat(deletedUser).isNull();
    }

}