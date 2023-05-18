package practice.notice_board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import practice.notice_board.domain.Category;

import javax.sql.DataSource;

@Repository
public class CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * category ID 가져오기
     */
    public Category getCategoryByCategoryName(String categoryName) {

        String sql = "SELECT * FROM CATEGORY WHERE category_name = ?";

        RowMapper<Category> rowMapper = categoryRowMapper();

        try {
            Category category = jdbcTemplate.queryForObject(sql, rowMapper, categoryName);
            return category;
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private RowMapper<Category> categoryRowMapper() {
        RowMapper<Category> rowMapper = (rs, rowNum) -> {
            int id = rs.getInt("id");
            String categoryName = rs.getString("category_name");
            return new Category(id,categoryName);
        };
        return rowMapper;
    }
}
