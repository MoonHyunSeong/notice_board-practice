package practice.notice_board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import practice.notice_board.domain.Post;
import practice.notice_board.service.PostService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/board/{categoryName}")
    public String noticeBoard(@PathVariable String categoryName, Model model) {

        List<Post> postByCategory = postService.getPostByCategory(categoryName);

        for (Post post : postByCategory) {
            System.out.println("post = " + post.toString());
        }

        model.addAllAttributes(postByCategory);


        return "/board/post";
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
