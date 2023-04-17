package practice.notice_board.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.User;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public User getUserByUserId() {
        String sql = "SELECT * FROM member WHERE userId = ?";

        });
    }
}
