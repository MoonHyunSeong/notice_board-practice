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

    public User userLogin(String userId, String password) {
        User userByUserId = userDao.getUserByUserId(userId);

        if (userByUserId == null || !userByUserId.getPassword().equals(password)) {
            return null;
        } else {
            return userByUserId;
        }

    }

}
