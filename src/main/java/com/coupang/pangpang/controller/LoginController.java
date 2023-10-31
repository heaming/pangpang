package com.coupang.pangpang.controller;

import com.coupang.pangpang.selenium.service.CoupangLoginService;
import com.coupang.pangpang.service.JsonToExcelService;
import com.coupang.pangpang.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/coupang")
@Slf4j
public class LoginController {
    private final CoupangLoginService searchService;
    private final JsonToExcelService excelService;

    @GetMapping("/search")
    public void search(@RequestParam(name = "param", required = false) String param) {
        log.info(param);
        searchService.login(param);
    }

    @PostMapping("/excel/{fileName}")
    public @ResponseBody List<ProductVo> excel(@RequestBody List<ProductVo> vo, @PathVariable String fileName) throws IOException {
        log.info(vo.get(0).toString());

        fileName = (fileName == null || fileName.length() == 0) ? "" : fileName;
        excelService.downloadExcel(vo, fileName);
        return vo;
    }


}
