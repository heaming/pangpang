package com.coupang.pangpang.service;

import com.coupang.pangpang.vo.ExcelVo;
import com.coupang.pangpang.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsonToExcelService {

    private ExcelVo excel;

    public void downloadExcel(List<ProductVo> datas) {

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

        for(String )



    }



}
