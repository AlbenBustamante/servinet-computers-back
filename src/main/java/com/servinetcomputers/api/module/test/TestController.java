package com.servinetcomputers.api.module.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/pong")
    public ResponseEntity<Void> pong() {
        return ResponseEntity.ok().build();
    }

}
