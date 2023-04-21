package practice.notice_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.notice_board.domain.User;
import practice.notice_board.dto.UserDto;
import practice.notice_board.dto.UserJoinDto;
import practice.notice_board.dto.UserLogInDto;
import practice.notice_board.service.UserService;

import java.util.UUID;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String logInPage() {
        return "user/login";
    }

    @PostMapping("/login")
    public String userLogIn(@ModelAttribute UserLogInDto logInUser, HttpSession session, Model model) {

        log.info("user login start");
        UserDto<User> result = userService.userLogin(logInUser.getUserId(), logInUser.getPassword());

        if (result.getStatusCode() == 1) { // 로그인 성공
            session.setAttribute("user", result.getData());
            log.info("user login end");
            return "welcomePage";
        } else if (result.getStatusCode() == 2) { // 비밀번호가 틀렸을 때
            model.addAttribute("loginError", "비밀번호가 틀렸습니다.");
            log.info("user login fail cuz pw");
            return "user/login";
        } else { // 등록된 아이디가 아닐 때 statusCode == 3
            model.addAttribute("loginError", "등록되지 않은 아이디입니다.");
            log.info("user login fail cuz not member");
            return "user/login";
        }
    }


    /**
     * 중복체크 일단 두자.
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/check-duplication")
    public String checkDuplication(@RequestParam("userId") String userId, Model model) {
        log.info("duplicate in");
        model.addAttribute("result", userService.checkDuplication(userId));
        return "user/join";
    }


    @GetMapping("/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/join")
    public String userJoin(@ModelAttribute UserJoinDto newUser) {

        log.info("join : {}", "Join start");
        userService.userJoin(newUser);

        return "user/login";
        //return "welcomePage";
    }

}
