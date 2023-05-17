package practice.notice_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import practice.notice_board.domain.Post;
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

        System.out.println("categoryName = " + categoryName);
        List<Post> postByCategory = postService.getPostByCategory(categoryName);

        for (Post post : postByCategory) {
            System.out.println("post = " + post.toString());
        }

        model.addAttribute("categoryName", categoryName);


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
        List<Post> postByCategory = postService.getPostByCategory(categoryName);

        return postByCategory;
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
