package review.data.repository.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import review.data.domain.User;
import review.data.repository.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcUserRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("user")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {

/*
        //SimpleJdbcInsert를 사용하지 않는 코드
        String sql = "insert into user(user_name, phone_num) values(:userName, :phoneNum)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        return user;
*/

        //SimpleJdbcInsert를 사용하는 코드
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        Number key = jdbcInsert.executeAndReturnKey(param);
        user.setId(key.longValue());
        return user;
    }

    @Override
    public void update(User user) {

        String sql = "update user " +
                "set user_name = :userName, " +
                "phone_num = :phoneNum " +
                "where id = :id";
//        SqlParameterSource param = new BeanPropertySqlParameterSource(user);

        // 위의 방식과 아래 방식 중 적합한 것으로 사용하면 됨
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userName", user.getUserName())
                .addValue("phoneNum", user.getPhoneNum())
                .addValue("id", user.getId());

        template.update(sql, param);

    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "select user_name, phone_num from user where id = :id";

        try {
            Map<String, Long> param = Map.of("id", id);
            RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

            User user = template.queryForObject(sql, param, rowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {

        String sql = "select user_name, phone_num from user";
        RowMapper<User> rowMapper = BeanPropertyRowMapper.newInstance(User.class);

        return template.query(sql, rowMapper);
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from user where id = :id";
        Map<String, Long> param = Map.of("id", id);

        template.update(sql, param);
    }
}
