package practice.notice_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.notice_board.dao.UserDao;
import practice.notice_board.domain.User;
import practice.notice_board.dto.UserDto;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public UserDto<User> userLogin(String userId, String password) {
        Optional<User> userByUserId = userDao.getUserByUserId(userId);

        if (userByUserId.isEmpty()) {
            return new UserDto<>(3, null); // 사용자를 찾지 못한 경우
        } else if (!userByUserId.get().getPassword().equals(password)) {
            return new UserDto<>(2, null); // 비밀번호가 틀린 경우
        } else {
            return new UserDto<>(1, userByUserId.get()); // 로그인 성공
        }
    }

}
