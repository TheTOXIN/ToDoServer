package com.toxin.todo.service;

import com.toxin.todo.dto.SignDtoRequest;
import com.toxin.todo.dto.SignDtoResponse;

public interface SignService {
    SignDtoResponse sign(SignDtoRequest request);
}
