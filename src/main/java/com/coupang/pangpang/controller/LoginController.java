package com.coupang.pangpang.controller;

import com.coupang.pangpang.selenium.service.CoupangLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupang")
public class LoginController {
    private final CoupangLoginService service;

    @GetMapping("/search")
    public void login(@RequestParam(name = "param", required = false) String param) {
        service.login(param);
    }
}
