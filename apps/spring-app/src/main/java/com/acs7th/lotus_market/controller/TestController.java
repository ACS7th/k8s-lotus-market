package com.acs7th.lotus_market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping()
    public String getSession(HttpSession httpSession) {
        return httpSession.getId();
    }

    @PostMapping()
    public String getSession2() {
        log.info("test");
        return "String";
    }
    
}