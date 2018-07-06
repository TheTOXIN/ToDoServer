package com.toxin.todo.repository;

import com.toxin.todo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends CrudRepository<User, UUID> {
    User findByLogin(@Param("login") String login);

    @Query(nativeQuery = true, value = "SELECT u.login FROM users u WHERE u.id = ?1")
    String getLoginById(@Param("id") UUID id);
}

