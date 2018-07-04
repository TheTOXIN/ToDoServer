package com.toxin.todo.enums;

public enum RoleEnum {
    ADMIN(1L),
    USER(2L);

    private Long id;

    RoleEnum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
