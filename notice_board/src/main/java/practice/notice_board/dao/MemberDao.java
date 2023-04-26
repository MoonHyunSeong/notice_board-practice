package practice.notice_board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.Member;
import practice.notice_board.dto.MemberJoinDto;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<Member> getUserByUserId(String logInUserId) {
        String sql = "SELECT * FROM member WHERE userId = ?";

        RowMapper<Member> rowMapper = memberMapping();

        try {
            Member member = jdbcTemplate.queryForObject(sql, rowMapper, logInUserId);
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<Member> getAllUsers() {
        String sql = "SELECT * FROM member";

        RowMapper<Member> rowMapper = memberMapping();

        return jdbcTemplate.query(sql, rowMapper);
    }

    private RowMapper<Member> memberMapping() {
        RowMapper<Member> rowMapper = (rs, rowNum) -> {
            String id = rs.getString("id");
            String username = rs.getString("username");
            String userId = rs.getString("userId");
            String password = rs.getString("password");
            String tel = rs.getString("tel");
            String email = rs.getString("email");
            String address = rs.getString("address");
            return new Member(id, username, userId, password, tel, email, address);
        };
        return rowMapper;
    }


    public void userJoin(MemberJoinDto memberJoinDto, String userUUID) {
        String sql = "INSERT INTO member (id, username, userid, password, tel, email, address)" +
                " VALUES(?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.update(sql, userUUID, memberJoinDto.getUsername(), memberJoinDto.getUserId(), memberJoinDto.getPassword(),
                    memberJoinDto.getTel(), memberJoinDto.getEmail(), memberJoinDto.getAddress());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
