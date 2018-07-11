package com.toxin.todo.repository;

import com.toxin.todo.entity.Role;
import com.toxin.todo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    List<Role> findAll();
    Role findByName(@Param("name") String name);
}
