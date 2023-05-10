package practice.notice_board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.Member;
import practice.notice_board.domain.Post;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Post> getPostByCategory(String categoryName) {
        String sql = "SELECT * FROM post WHERE categoryName = ?";
        RowMapper<Post> rowMapper = postMapping();
        List<Post> result = jdbcTemplate.query(sql, rowMapper,categoryName);

        return result;
    }

    private RowMapper<Post> postMapping() {
        RowMapper<Post> rowMapper = (rs, rowNum) -> {
            int id = (int) rs.getLong("id");
            String title = rs.getString("title");
            String content = rs.getString("content");
            int likes = rs.getInt("likes");
            LocalDateTime create_date = rs.getTimestamp("create_date").toLocalDateTime();
            LocalDateTime update_date = rs.getTimestamp("update_date") != null ? rs.getTimestamp("update_date").toLocalDateTime() : null;
            String member_id = rs.getString("member_id");
            int category_id = rs.getInt("category_id");
            return new Post(id, title, content, likes, create_date, update_date, member_id, category_id);
        };
        return rowMapper;
    }

}
