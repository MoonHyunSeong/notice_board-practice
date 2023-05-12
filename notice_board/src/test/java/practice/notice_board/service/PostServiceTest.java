package practice.notice_board.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import practice.notice_board.dao.PostDao;
import practice.notice_board.domain.Post;
import practice.notice_board.dto.PostDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {

    @Autowired
    private PostDao postDao;

    public PostServiceTest() {}

    @Test
    public void createPost() throws Exception {
        //given

        String memberId = "fe52496c-1090-4967-abb2-8ecf002dc7be";

        PostDto post1 = new PostDto("제목1", "내용1",  LocalDateTime.now());
        PostDto post2 = new PostDto("제목2", "내용2",  LocalDateTime.now());
        PostDto post3 = new PostDto("제목3", "내용3",  LocalDateTime.now());
        PostDto post4 = new PostDto("제목4", "내용4",  LocalDateTime.now());
        PostDto post5 = new PostDto("제목5", "내용5", LocalDateTime.now());

        //when
        postDao.createPost(post5, memberId, 3 );
        //then

    }

    @Test
    public void deletePost() throws Exception {
        //given
        String memberId = "fe52496c-1090-4967-abb2-8ecf002dc7be";
        String postTitle = "제목5";

        //when
        postDao.deletePost(memberId, postTitle);
        //then

    }

    @Test
    public void getAllPostByCategory() throws Exception {
        //given
        String categoryName = "programming";

        //when
        List<Post> allPostByCategory =
                postDao.getAllPostByCategory(1);

        //then

        for (Post post : allPostByCategory) {
            System.out.println("post = " + post.getTitle());
        }

    }

    @Test
    public void getAllPost() throws Exception {
        //given
        Map<String, List<Post>> allPost = postDao.getAllPost();
        //when
        for (Post s : allPost.get("programming")) {
            System.out.println("s = " + s.toString());
        }
        //then

    }
}