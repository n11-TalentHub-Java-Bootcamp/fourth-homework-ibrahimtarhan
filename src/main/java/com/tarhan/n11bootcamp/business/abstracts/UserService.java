package com.tarhan.n11bootcamp.business.abstracts;

import com.tarhan.n11bootcamp.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User save(User user);
    void delete(Long userId);
    User getById(Long userId);

}
