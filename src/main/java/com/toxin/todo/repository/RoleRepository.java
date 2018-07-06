package com.toxin.todo.repository;

import com.toxin.todo.entity.Role;
import com.toxin.todo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(@Param("name") String name);
}
