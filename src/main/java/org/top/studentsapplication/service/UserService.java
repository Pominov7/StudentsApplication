package org.top.studentsapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.User;
import org.top.studentsapplication.db.repository.UserRepository;

// Сервис для работы с таблицей пользователей
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository; // репозиторий

    @Autowired
    private PasswordEncoder encoder; // кодировщик

    // Добавление нового пользователя
    public void addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        password = encoder.encode(password);    // захэшировать пароль
        user.setPassword(password); // пароль надо хэшировать
        userRepository.save(user);   // сохранил пользователя
    }
}