package practice.notice_board.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import practice.notice_board.domain.User;
import practice.notice_board.dto.userLogInDto;
import practice.notice_board.service.UserService;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String logInPage() {
        return "user/logIn";
    }

    @PostMapping("/login")
    public String userLogIn(@ModelAttribute userLogInDto logInUser, HttpSession session, Model model) {
        User result = userService.userLogin(logInUser.getUserId(), logInUser.getPassword());

        if (result != null) {
            session.setAttribute("user", result);
            return "redirect:/welcomePage";
        } else {
            model.addAttribute("loginError", "잘못된 로그인 정보입니다.");
            return "user/logIn";
        }
    }


    @GetMapping("/join")
    public String joinPage() {
        return "user/join";
    }

    @PostMapping("/join")
    public void userJoin(@RequestParam Map<String, String> joinMap) {

        log.info("join map : {}", joinMap);

        //return "welcomePage";
    }

}
