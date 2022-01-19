package com.tarhan.n11bootcamp.controllers;

import com.tarhan.n11bootcamp.business.abstracts.UserService;
import com.tarhan.n11bootcamp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAll(){
        return userService.getAll();
    }
    @PostMapping("add")
    public User AddUser(@RequestBody User user){
        return userService.save(user);
    }
    @DeleteMapping("/{id}")
    public void delete(Long id){
        userService.delete(id);
    }

}
