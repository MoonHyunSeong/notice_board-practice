package practice.notice_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.notice_board.domain.Comment;
import practice.notice_board.domain.Post;
import practice.notice_board.dto.CommentDto;
import practice.notice_board.dto.PostDto;
import practice.notice_board.service.PostService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/board/{categoryName}")
    public String noticeBoard(@PathVariable String categoryName, Model model) {

        model.addAttribute("categoryName", categoryName);

        return "/board/posts";
    }

    @GetMapping("/board/all/{categoryName}")
    @ResponseBody
    public List<Post> noticeBoard(@PathVariable String categoryName) {

        List<Post> postByCategory = postService.getAllPostByCategory(categoryName);

        return postByCategory;
    }

    @GetMapping("/board/post/{postId}")
    public String getPost(@PathVariable String postId, Model model,
                          RedirectAttributes redirectAttributes) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);


        redirectAttributes.addAttribute("id", postId);
        //temp
        return "/board/post";
    }



    @GetMapping("/board/createPost")
    public String createPostView(@RequestParam String categoryName, Model model) {

        model.addAttribute("categoryName", categoryName);

        return "board/createPost";
    }

    @PostMapping("/board/createPost")
    public String createPost(@ModelAttribute PostDto newPost, HttpServletRequest request
            , RedirectAttributes redirectAttributes) {

        String categoryName = newPost.getCategoryName();

        HttpSession session = request.getSession(false);
        String loginMemberId = (String) session.getAttribute("loginUserId");


        Boolean result = postService.createPost(newPost, loginMemberId, categoryName);
        // result
        if (result == true) {
            // 리다이렉트에 변수주려면 이렇게 쓰면 된다.
            redirectAttributes.addAttribute("categoryName", categoryName);
            return "redirect:/board/{categoryName}";
        } else {
            return "/welcomePage";
        }
    }

    @GetMapping("/board/recentPosts/{categoryName}")
    @ResponseBody
    public List<Post> getPostsByCategory(@PathVariable String categoryName) {
        List<Post> postByCategory = postService.getPostByCategoryFive(categoryName);

        return postByCategory;
    }

    /**
     * 댓글 전부 가져오기 ajax이기 때문에 나중에 responsebody 쓰기
     */
    @GetMapping("/board/comments/{postId}")
    @ResponseBody
    public List<Comment> getAllComments(@PathVariable String postId) {
        List<Comment> allComments = postService.getAllComments(postId);

        return allComments;
    }


    // 경로 문제 조심하기.
    @PostMapping("/board/post/createComment")
    public String createComment(@ModelAttribute CommentDto comment, RedirectAttributes redirectAttributes) {

        postService.createComment(comment);

        redirectAttributes.addAttribute("postId", comment.getPostId());
        return "redirect:/board/post/{postId}";
    }




    /**
     * 1. categoryName을 가지고 list를 뽑아와서 모델에 담은 뒤 리턴.
     * 2. view로 categoryName을 던져서 뷰에서 js로 비동기 처리.
     */

    /*@GetMapping("/{categoryName}")
    public String noticeBoard(@PathVariable String categoryName, Model model) {
        model.addAttribute("categoryName", categoryName);
        return "board/post";
        //view에서 categoryName을 받고 비동기로 처리하는 방법.
    }*/
}
