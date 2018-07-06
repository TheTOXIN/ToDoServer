package com.toxin.todo.service;

import com.toxin.todo.dto.SignDtoRequest;
import com.toxin.todo.dto.SignDtoResponse;
import com.toxin.todo.entity.Role;
import com.toxin.todo.entity.User;
import com.toxin.todo.enums.RoleEnum;
import com.toxin.todo.repository.RoleRepository;
import com.toxin.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignServiceImpl implements SignService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignDtoResponse sign(SignDtoRequest request) {
        SignDtoResponse dto = new SignDtoResponse();

        UUID userId = request.isReg() ? up(request) : in(request);
        dto.setUserId(userId);

        return dto;
    }

    private UUID up(SignDtoRequest request) {
        User user = userRepository.findByLogin(request.getLogin());
        Role role = roleRepository.findByName(RoleEnum.USER.name());

        if (user == null) {
            user = new User();

            user.setLogin(request.getLogin());
            user.setHash(passwordEncoder.encode(request.getPassword()));
            user.setRole(role);

            return userRepository.save(user).getId();
        }

        return null;
    }

    private UUID in(SignDtoRequest request) {
        User user = userRepository.findByLogin(request.getLogin());

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getHash())) {
            return user.getId();
        }

        return null;
    }

}
