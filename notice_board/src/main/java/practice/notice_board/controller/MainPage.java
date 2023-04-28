package practice.notice_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Slf4j
public class MainPage {

    @GetMapping("/")
    public String mainPage() {
        return "welcomePage";

//        return "welcomePage";
    }
}
