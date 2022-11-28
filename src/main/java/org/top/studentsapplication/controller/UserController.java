package org.top.studentsapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.top.studentsapplication.service.UserService;

@RestController
public class UserController {

    // контроллер для тестирования пользователей
    @Autowired
    private UserService userService;

    @PostMapping("/users/new")
    public String addUser(@RequestParam String username, @RequestParam String password) {
        userService.addUser(username, password);
        return "user added";
    }
}