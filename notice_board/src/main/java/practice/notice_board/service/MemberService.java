package practice.notice_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.notice_board.dao.MemberDao;
import practice.notice_board.domain.Member;
import practice.notice_board.dto.MemberDto;
import practice.notice_board.dto.MemberJoinDto;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;

    public MemberDto userLogin(String userId, String password) {
        //Optional<Member> userByUserId = memberDao.getUserByUserId(userId);

        Member validationMember = memberDao.getUserByUserId(userId)
                .filter(member -> member.getPassword().equals(password))
                .orElse(null);

        if (validationMember == null) {
            return new MemberDto<>(3, null); // 사용자를 찾지 못한 경우
        } /*else if (!userByUserId.get().getPassword().equals(password)) {
            return new MemberDto<>(2, null); // 비밀번호가 틀린 경우
        } */else {
            return new MemberDto<>(1, validationMember); // 로그인 성공
        }
    }

    public boolean checkDuplication(String userId) {
        Optional<Member> result = memberDao.getUserByUserId(userId);

        if (result.isEmpty()) {
            return true; // 중복 ID x
        } else {
            return false; // 중복 ID o
        }
    }

    public Boolean userJoin(MemberJoinDto newUser){
        UUID userUUID = UUID.randomUUID();
        Boolean result = memberDao.userJoin(newUser, String.valueOf(userUUID));

        if (result == true) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean removeUser(String userId) {
        Boolean result = memberDao.userDelete(userId);

        if (result == true) {
            return true;
        } else {
            return false;
        }

    }
}
