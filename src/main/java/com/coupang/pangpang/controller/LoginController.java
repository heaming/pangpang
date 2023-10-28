package com.coupang.pangpang.controller;

import com.coupang.pangpang.selenium.service.CoupangLoginService;
import com.coupang.pangpang.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupang")
@Slf4j
public class LoginController {
    private final CoupangLoginService service;

    @GetMapping("/search")
    public void login(@RequestParam(name = "param", required = false) String param) {
        log.info(param);
        service.login(param);
    }

    @PostMapping("/next")
    public @ResponseBody List<ProductVo> next(@RequestBody List<ProductVo> vo) {
        log.info(vo.get(0).toString());
        return vo;
    }

}
