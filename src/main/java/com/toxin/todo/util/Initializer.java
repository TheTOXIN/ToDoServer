package com.toxin.todo.util;

import com.toxin.todo.entity.Role;
import com.toxin.todo.entity.User;
import com.toxin.todo.enums.RoleEnum;
import com.toxin.todo.repository.RoleRepository;
import com.toxin.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class Initializer {

    private final Logger log = LoggerFactory.getLogger(Initializer.class);

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public Initializer(
        RoleRepository roleRepository,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void init() {
        log.info("-=START INIT=-");
        createRoles();
        createAdmin();
        log.info("-=END INIT=-");
    }

    private void createRoles() {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            Role role = new Role();

            role.setId(roleEnum.getId());
            role.setName(roleEnum.name());

            roleRepository.save(role);
        }
    }

    private void createAdmin() {
        if (userRepository.findByLogin("admin") != null) return;

        User admin = new User();

        admin.setLogin("admin");
        admin.setHash(passwordEncoder.encode("root"));
        admin.setRole(roleRepository.findById(RoleEnum.ADMIN.getId()).orElse(null));

        userRepository.save(admin);
    }

}
