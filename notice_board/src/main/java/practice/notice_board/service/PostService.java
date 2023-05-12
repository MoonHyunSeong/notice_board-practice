package practice.notice_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.notice_board.dao.CategoryDao;
import practice.notice_board.dao.MemberDao;
import practice.notice_board.dao.PostDao;
import practice.notice_board.domain.Member;
import practice.notice_board.dto.PostDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;
    private final MemberDao memberDao;
    private final CategoryDao categoryDao;

    public void createPost(PostDto postDto, String userId, String categoryName) {
        Optional<Member> userByUserId = memberDao.getUserByUserId(userId);
        String memberId = userByUserId.get().getId();

        int categoryId = categoryDao.getIdByCategoryName(categoryName);

        if (userByUserId.isPresent() || (categoryId == -1)) {
            //fail
        } else {
            postDao.createPost(postDto, memberId, categoryId);
        }
    }



}
