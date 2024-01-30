package review.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import review.data.domain.User;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;
import review.data.repository.jpa.SpringDataJpaUserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final SpringDataJpaUserRepository repository;

    public User save(User user) {
        repository.save(user);
        return user;
    }

    public void update(Long id, UserUpdateDto updateDto) {
        User user = repository.findById(id).orElse(null);

        if (user != null) {
            user.setUserName(updateDto.getUserName());
            user.setPhoneNum(updateDto.getPhoneNum());
        }
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public List<User> findAll(UserSearchCondition condition) {

        String userName = condition.getUserName();

        if (StringUtils.hasText(userName)) {
           return repository.findByUserNameLike("%" + userName + "%");
        } else {
            return repository.findAll();
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
