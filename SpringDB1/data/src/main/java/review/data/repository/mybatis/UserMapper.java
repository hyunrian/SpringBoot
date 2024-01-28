package review.data.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import review.data.domain.User;
import review.data.repository.UserSearchCondition;
import review.data.repository.UserUpdateDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    //MyBatis 매핑 xml을 호출해주는 매퍼 인터페이스
    //xml 파일은 resources 하위에 interface와 동일한 디렉토리 구조 내에 위치해야 함
    //원하는 위치에 두고 싶으면 application.properties에
    // "mybatis.mapper-locations=classpath:mapper/**/*.xml" 등으로 작성 -> resources/mapper 포함 하위 폴더의 xml 인식 가능

    void save(User user);

    void update(@Param("id") Long id, @Param("userDto") UserUpdateDto userDto);

    Optional<User> findById(Long id);

    List<User> findAll(UserSearchCondition searchCondition);

    void delete(Long id);

}
