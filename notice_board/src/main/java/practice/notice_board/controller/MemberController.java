package practice.notice_board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import practice.notice_board.domain.Member;
import practice.notice_board.dto.MemberDto;
import practice.notice_board.dto.MemberJoinDto;
import practice.notice_board.dto.MemberLoginDto;
import practice.notice_board.service.MemberService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String logInPage() {
        return "member/login";
    }

    @PostMapping("/login")
    public String userLogIn(@ModelAttribute MemberLoginDto logInUser,
            BindingResult bindingResult, HttpServletRequest request,
                            Model model) {

        if (bindingResult.hasErrors()) {
            return "member/login";
        }

        MemberDto<Member> result = memberService.userLogin(logInUser.getUserId(), logInUser.getPassword());

        if (result.getStatusCode() == 1) { // 로그인 성공
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", result.getData().getUsername());
            session.setAttribute("loginMemberId", result.getData().getUserId());
            log.info("login? {}", result.getData().getUsername());
            return "redirect:/";
        } else if (result.getStatusCode() == 2) { // 비밀번호가 틀렸을 때
            model.addAttribute("loginError", "비밀번호가 틀렸습니다.");
            log.info("user login fail cuz pw");
            return "member/login";
        } else { // 등록된 아이디가 아닐 때 statusCode == 3
            model.addAttribute("loginError", "등록되지 않은 아이디입니다.");
            log.info("user login fail cuz not member");
            return "member/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        log.info("now member id ? = {} ", session.getAttribute("loginMemberId"));
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/member/myPage")
    public String getMyPage() {
        return "member/myPage";
    }

    @GetMapping("/member/removeMember")
    public String removeMember(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        log.info("now member id ? = {} ", session.getAttribute("loginMemberId"));


        memberService.removeUser((String) session.getAttribute("loginMemberId"));
        if (session != null) {
            session.invalidate();
        }


        return "redirect:/";
    }


    @GetMapping("/member/add")
    public String joinPage() {
        return "member/join";
    }

    @PostMapping("/member/add")
    public String userJoin(@ModelAttribute MemberJoinDto newUser) {

        log.info("join : {}", "Join start");
        memberService.userJoin(newUser);

        return "redirect: /login";
        //return "welcomePage";
    }


    @PostMapping("/check-duplicate")
    @ResponseBody
    public Map<String, Object> checkDuplication(@RequestParam("userId") String userId) {
        log.info("duplicate in");
        Map<String, Object> result = new HashMap<>();

        boolean isDuplicate = memberService.checkDuplication(userId);

        result.put("isDuplicate", isDuplicate);

        return result;
    }




    /*@GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "welcomePage";
        }
        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}",
                        name, session.getAttribute(name)));
        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new
                Date(session.getLastAccessedTime()));
        log.info("isNew={}", session.isNew());
        return "welcomePage";
    }*/

}
