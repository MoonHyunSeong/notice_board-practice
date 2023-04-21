package practice.notice_board.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.User;
import practice.notice_board.dto.UserJoinDto;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<User> getUserByUserId(String logInUserId) {
        String sql = "SELECT * FROM member WHERE userId = ?";

        RowMapper<User> rowMapper = (rs, rowNum) -> {
            String id = rs.getString("id");
            String username = rs.getString("username");
            String userId = rs.getString("userId");
            String password = rs.getString("password");
            String tel = rs.getString("tel");
            String email = rs.getString("email");
            String address = rs.getString("address");
            return new User(id, username, userId, password, tel, email, address);
        };

        try {
            User user = jdbcTemplate.queryForObject(sql, rowMapper, logInUserId);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void userJoin(UserJoinDto userJoinDto, String userUUID) {
        String sql = "INSERT INTO member (id, username, userid, password, tel, email, address)" +
                " VALUES(?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.update(sql, userUUID, userJoinDto.getUsername(), userJoinDto.getUserId(), userJoinDto.getPassword(),
                    userJoinDto.getTel(), userJoinDto.getEmail(), userJoinDto.getAddress());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
