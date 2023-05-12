package practice.notice_board.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public int getIdByCategoryName(String categoryName) {

        String sql = "SELECT id FROM CATEGORY WHERE category_name = ?";

        try {
            return jdbcTemplate.update(sql, categoryName);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
