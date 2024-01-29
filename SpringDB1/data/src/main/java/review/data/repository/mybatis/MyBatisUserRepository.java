package review.data.repository.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import review.data.domain.User;
import review.data.repository.UserRepository;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;

import java.util.List;
import java.util.Optional;

//@Repository
public class MyBatisUserRepository implements UserRepository {

    @Autowired
    UserMapper userMapper;

//    private final UserMapper userMapper;
//
//    public MyBatisUserRepository(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public User save(User user) {
        userMapper.save(user);
        return user;
    }

    @Override
    public void update(Long id, UserUpdateDto userDto) {
        userMapper.update(id, userDto);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll(UserSearchCondition searchCondition) {
        return userMapper.findAll(searchCondition);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }
}