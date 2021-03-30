package com.itstep.ckj13_mvc.service;

import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.exception.UsernameOrEmailExistsException;
import com.itstep.ckj13_mvc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername())==null&&
                userRepository.findByEmail(user.getEmail())==null) {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            User newUser = new User();
            newUser.setUsername(user.getUsername());
            newUser.setRole("ROLE_USER");
            newUser.setPassword(encoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setFirstname(user.getFirstname());
            newUser.setLastname(user.getLastname());
            newUser.setAge(user.getAge());
            return userRepository.save(newUser);
        }else{
            throw new UsernameOrEmailExistsException(user.getUsername());
        }
    }
}
