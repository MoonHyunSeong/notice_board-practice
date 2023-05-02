package practice.notice_board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class NoticeController {

    @GetMapping("/notice")
    public String noticeBoard() {
        return "notice/notice";
    }
}