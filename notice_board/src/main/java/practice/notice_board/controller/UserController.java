package practice.notice_board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
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
    public String userLogIn(@ModelAttribute userLogInDto logInUser) {
        Boolean result = userService.userLogin(logInUser.getUserId(), logInUser.getPassword());
        if (result == Boolean.TRUE) {
            return "welcomePage";
        } else {
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
