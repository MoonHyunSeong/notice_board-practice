package practice.notice_board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.Post;
import practice.notice_board.dto.PostDto;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class PostDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 게시글 생성
     */
    public Boolean createPost(PostDto postdto, String memberId, int categoryId) {
        String sql = "INSERT INTO post(title, content, create_date, member_id, category_id) VALUES(?, ?, CURRENT_TIMESTAMP(), ?, ?)";

        try {
            jdbcTemplate.update(sql, postdto.getTitle(), postdto.getContent(),
                    memberId, categoryId);

            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 게시글 수정
     */
    public Boolean updatePost(String content) {
        return true;
    }

    /**
     * 게시글 삭제
     */
    public Boolean deletePost(String memberId, String postTitle) {

        String sql = "DELETE FROM Post WHERE member_id = ? and title = ?";

        try {
            jdbcTemplate.update(sql, memberId, postTitle);
            return true;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * like up
     */

    /**
     * like cancel
     */

    /**
     * 제목에 따른 게시글 찾아오기.
     */


    /**
     * 카테고리 클릭 시 해당 페이지로 이동해서 모든 글들 가져오게 하려고 만듬.
     * @return
     */
    public List<Post> getAllPostByCategory(int categoryId) {
        String sql = "SELECT * FROM post WHERE category_id = ?";
        RowMapper<Post> rowMapper = postMapping();
        List<Post> result = jdbcTemplate.query(sql, rowMapper, categoryId);

        return result;
    }


    /**
     * 카테고리 별 게시글 제목 -> 메인페이지용 -> 페이징은 고려 x 조건을 max 5개로 줘야할듯
     * 카테고리도 이름만 가져오자. Map<String(categoryName), List<Post>>
     * @return
     */
    public Map<String, List<Post>> getAllPost() {

        String sql = "SELECT p.id, p.title, p.content, p.likes, p.create_date, p.update_date, p.member_id, p.category_id, c.category_name " +
                "FROM post p JOIN category c ON p.category_id = c.id " +
                "ORDER BY c.category_name  ASC";

        return jdbcTemplate.query(sql, rs -> {
            Map<String, List<Post>> result = new LinkedHashMap<>();
            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int likes = rs.getInt("likes");
                LocalDateTime createDate = rs.getObject("create_date", LocalDateTime.class);
                LocalDateTime updateDate = rs.getObject("update_date", LocalDateTime.class);
                String memberId = rs.getString("member_id");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");

                Post post = new Post(id, title, content, likes, createDate, updateDate, memberId, categoryId);
                if (result.containsKey(categoryName)) {
                    result.get(categoryName).add(post);
                } else {
                    List<Post> posts = new ArrayList<>();
                    posts.add(post);
                    result.put(categoryName, posts);
                }
            }
            return result;
        });
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
