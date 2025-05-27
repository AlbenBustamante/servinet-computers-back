package com.servinetcomputers.api.module.author.infrastructure.in.rest;

import com.servinetcomputers.api.module.author.application.port.in.AuthorInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/authors")
@RestController
public class AuthorController {
    private final AuthorInputPort port;

    @PostMapping
    public ResponseEntity<AuthorDto> create()
}
