package com.coupang.pangpang.controller;

import com.coupang.pangpang.dto.ProductDto;
import com.coupang.pangpang.selenium.service.SeleniumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupang")
@Slf4j
public class SeleniumController {
    private final SeleniumService seleniumServicervice;
//    private final

    @GetMapping("/search")
    public void search(@RequestParam(name = "param", required = true) String param) {
        log.info("PARAM:: "+param);
        seleniumServicervice.login(param);
//        return "param";
    }

    @PostMapping("/excel-download")
//    public @ResponseBody List<ProductDto> exportToExcel(@RequestBody List<ProductDto> dtos) {
    public @ResponseBody List<ProductDto> exportToExcel(@RequestBody List<ProductDto> dtos) {
        log.info(dtos.get(0).toString());
        return dtos;
    }
}
