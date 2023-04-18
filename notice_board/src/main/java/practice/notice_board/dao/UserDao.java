package practice.notice_board.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.User;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User getUserByUserId(String logInUserId) {
        String sql = "SELECT * FROM member WHERE userId = ?";

        RowMapper<User> rowMapper = (rs, rowNum) -> {
            String id = rs.getString("id");
            String username = rs.getString("username");
            String userId = rs.getString("userId");
            String password = rs.getString("password");
            return new User(id, username, userId, password);
        };

        User user = jdbcTemplate.queryForObject(sql, rowMapper, logInUserId);
        if (user == null) {
            return null;
        } else {
            return user;
        }
    }
}
