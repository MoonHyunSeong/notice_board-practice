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
    public String mainPage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        if (session == null) {
            return "세션이 없습니다.";
        } else {
            log.info("sessionID = {} ", session.getId());
            return "welcomePage";
        }

//        return "welcomePage";
    }
}
