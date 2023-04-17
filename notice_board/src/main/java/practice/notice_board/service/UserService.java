package practice.notice_board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.notice_board.dao.UserDao;
import practice.notice_board.domain.User;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User userCheck(String userId, String password) {
        userDao.
    }

}
