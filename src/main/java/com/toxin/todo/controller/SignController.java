package com.toxin.todo.controller;

import com.toxin.todo.dto.SignDtoRequest;
import com.toxin.todo.dto.SignDtoResponse;
import com.toxin.todo.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    private final SignService oAuthService;

    @Autowired
    public SignController(SignService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @PostMapping("/sign")
    public SignDtoResponse todo(@RequestBody SignDtoRequest request) {
        return oAuthService.sign(request);
    }

}
