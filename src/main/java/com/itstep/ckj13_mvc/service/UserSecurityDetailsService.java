package com.itstep.ckj13_mvc.service;

import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.entity.UserSecurity;
import com.itstep.ckj13_mvc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserSecurityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException(username);
        }

        return new UserSecurity(user);
    }


}
