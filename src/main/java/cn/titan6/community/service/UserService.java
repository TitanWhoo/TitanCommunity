package cn.titan6.community.service;

import cn.titan6.community.bean.User;
import cn.titan6.community.dao.UserDao;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User find(String username, String password) {
        username = StringEscapeUtils.escapeHtml4(username);
        password = StringEscapeUtils.escapeHtml4(password);
        return userDao.find(username, password);
    }

    public User find(int id) {
        return userDao.findById(id);
    }

    public User find(User user) {
        user.setUsername(StringEscapeUtils.escapeHtml4(user.getUsername()));
        user.setPassword(StringEscapeUtils.escapeHtml4(user.getPassword()));
        return userDao.findByUser(user);
    }

    public boolean save(User user) {
        User found = userDao.findByUser(user);
        if (found != null) {
            return false;
        } else {
            return userDao.save(user);
        }
    }

}
