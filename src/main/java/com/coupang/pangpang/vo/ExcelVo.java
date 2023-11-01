package com.coupang.pangpang.vo;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Component
@Getter
@Setter
public class ExcelVo {
    private String DEFUALT_PATH;
    private String DEFUALT_NAME;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String fileName;

    private String currentTime;

    public ExcelVo(
            @Value("${excel.default_file_path}") String path,
            @Value("${excel.name_prefix}") String prefix,
            @Value("${excel.name_sufix}") String sufix) {

        Timestamp timestamp  = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss") ;

        this.DEFUALT_PATH = path;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("sheet1");
        this.fileName = this.DEFUALT_NAME;
        this.currentTime = format.format(timestamp);
        this.DEFUALT_NAME = prefix + "_" + this.currentTime + sufix;

    }

    public String getFileName() {
        return this.fileName.length() > 0 ? this.fileName+"_"+this.currentTime+".xls" : this.DEFUALT_NAME;
    }

}
