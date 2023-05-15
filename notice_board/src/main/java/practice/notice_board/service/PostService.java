package practice.notice_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.notice_board.dao.CategoryDao;
import practice.notice_board.dao.MemberDao;
import practice.notice_board.dao.PostDao;
import practice.notice_board.domain.Category;
import practice.notice_board.domain.Member;
import practice.notice_board.domain.Post;
import practice.notice_board.dto.PostDto;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;
    private final MemberDao memberDao;
    private final CategoryDao categoryDao;

    public Boolean createPost(PostDto postDto, String userId, String categoryName) {

        Optional<Member> userByUserId = memberDao.getUserByUserId(userId);
        Category getCategory = categoryDao.getCategoryByCategoryName(categoryName);


        if (userByUserId.isPresent() && getCategory != null) {
            String memberId = userByUserId.get().getId();
            int categoryId = getCategory.getId();

            Boolean result = postDao.createPost(postDto, memberId, categoryId);
            if (result == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<Post> getPostByCategory(String categoryName) {
        Category getCategory = categoryDao.getCategoryByCategoryName(categoryName);
        int categoryId = getCategory.getId();

        List<Post> allPostByCategory = postDao.getAllPostByCategory(categoryId);
        return allPostByCategory;
    }



}
