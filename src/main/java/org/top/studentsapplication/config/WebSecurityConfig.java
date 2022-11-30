package org.top.studentsapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.top.studentsapplication.service.security.DBUserDetailsService;


@EnableWebSecurity
public class WebSecurityConfig {
    // зависимость кодировщика паролей
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    public DBUserDetailsService dbUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/", "/students", "/webjars/**", "/css/**", "/users/new").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout().logoutSuccessUrl("/")
                .and().csrf().disable();

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails root =
//                User.withDefaultPasswordEncoder()
//                        .username("root")
//                        .password("root")
//                        .roles("ADMIN")
//                        .build();
//
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//        return new InMemoryUserDetailsManager(root, user);
//    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().antMatchers("/styles/**");
    }
}
