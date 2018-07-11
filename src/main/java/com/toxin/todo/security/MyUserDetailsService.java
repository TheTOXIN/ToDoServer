package com.toxin.todo.security;

import com.toxin.todo.entity.User;
import com.toxin.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByLogin(login);

        if (user == null) throw new UsernameNotFoundException(String.format("User %s does not exist!", login));

        return new MyUserDetails(user);
    }


}
