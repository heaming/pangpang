package com.coupang.pangpang.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class ExcelVo {
    private String DEFUALT_PATH;
    private String DEFUALT_NAME;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;

    public ExcelVo(
            @Value("${excel.default_file_path}") String path,
            @Value("${excel.name_prefix}") String prefix,
            @Value("${excel.name_sufix}") String sufix) {

        this.DEFUALT_PATH = path;
        this.DEFUALT_NAME = prefix + sufix;
        this.wb = new XSSFWorkbook();
        this.sheet = wb.createSheet();
        }


}
