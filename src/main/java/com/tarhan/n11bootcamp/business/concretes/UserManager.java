package com.tarhan.n11bootcamp.business.concretes;

import com.tarhan.n11bootcamp.business.abstracts.UserService;
import com.tarhan.n11bootcamp.dataAccess.abstracts.UserDao;
import com.tarhan.n11bootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManager implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(Long userId) {
        User user = userDao.getById(userId);

        userDao.delete(user);
    }

    @Override
    public User getById(Long userId) {
        return userDao.getById(userId);
    }
}
