package com.coupang.pangpang.service;

import com.coupang.pangpang.vo.ExcelVo;
import com.coupang.pangpang.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonToExcelService  {

    @Autowired
    private ExcelVo excel;

    public void downloadExcel(List<ProductVo> datas, String searchValue) throws IOException {
//        excel = new ExcelVo();
        XSSFWorkbook wb = excel.getWorkbook();
        XSSFSheet sheet = excel.getSheet();


        if (searchValue.length() > 0 ) {
            this.excel.setFileName(searchValue);
        }

        Map<Integer, Object[]> map = new TreeMap<>();
        String[] headers = new String[]{
                "attributeTypes",
                "brandName",
                "categoryId",
                "deliveryMethod",
                "imagePath",
                "itemCountOfProduct",
                "itemId",
                "itemName",
                "manufacture",
                "matchType",
                "matchingResultId",
                "productId",
                "productName",
                "pvLast28Day",
                "rating",
                "ratingCount",
                "salePrice",
                "sponsored",
                "venderId"
        };
        map.put(0, headers);

        for(int i = 0; i < datas.size(); i++) {
            ProductVo data = datas.get(i);

            Object[] dataSet = new Object[19];
            dataSet[0] = data.getAttributeTypes();
            dataSet[1] = data.getBrandName();
            dataSet[2] = data.getCategoryId();
            dataSet[3] = data.getDeliveryMethod();
            dataSet[4] = data.getImagePath();
            dataSet[5] = data.getItemCountOfProduct();
            dataSet[6] = data.getItemId();
            dataSet[7] = data.getItemName();
            dataSet[8] = data.getManufacture();
            dataSet[9] = data.getMatchType();
            dataSet[10] = data.getMatchingResultId();
            dataSet[11] = data.getProductId();
            dataSet[12] = data.getProductName();
            dataSet[13] = data.getPvLast28Day();
            dataSet[14] = data.getRating();
            dataSet[15] = data.getRatingCount();
            dataSet[16] = data.getSalePrice();
            dataSet[17] = data.getSponsored();
            dataSet[18] = data.getVenderId();

            map.put(i+1, dataSet);
        }

        Set<Integer> keyset = map.keySet();

        for(Integer key : keyset) {
            Row row = sheet.createRow(key);
            Object[] cellArr = map.get(key);
            int cellNum = 0;

            for(Object obj : cellArr) {
                Cell cell = row.createCell(cellNum++);

                if(obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Long) {
                    cell.setCellValue((Long) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }

        wb.write(new FileOutputStream(new File(excel.getDEFUALT_PATH(), excel.getFileName())));
    }
}
