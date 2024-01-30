package review.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.data.domain.User;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;
import review.data.repository.querydsl.UserQueryRepository;
import review.data.repository.querydsl.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

//    private final SpringDataJpaUserRepository jpaRepository;
    private final UserRepository userRepository;
    private final UserQueryRepository queryRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public void update(Long id, UserUpdateDto updateDto) {
        User user = userRepository.findById(id).orElseThrow();

        user.setUserName(updateDto.getUserName());
        user.setPhoneNum(updateDto.getPhoneNum());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

//    public List<User> findAll(UserSearchCondition condition) {
//
//        String userName = condition.getUserName();
//
//        if (StringUtils.hasText(userName)) {
//           return jpaRepository.findByUserNameLike("%" + userName + "%");
//        } else {
//            return jpaRepository.findAll();
//        }
//    }

    public List<User> findAll(UserSearchCondition condition) {
        return queryRepository.findAll(condition);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
