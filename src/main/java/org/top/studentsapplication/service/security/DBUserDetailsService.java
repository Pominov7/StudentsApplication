package org.top.studentsapplication.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.top.studentsapplication.db.entity.User;
import org.top.studentsapplication.db.repository.UserRepository;
import org.top.studentsapplication.service.UserService;

@Service
public class DBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 1. получить пользователя
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            // если пользователь не найден
            throw new UsernameNotFoundException(userName);
        }
        // 2. вернуть объект UserDetails
        return new DBUserDetails(user);
    }
}