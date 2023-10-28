package com.coupang.pangpang.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductDto {
    private String attributeTypes;
    private String brandName;
    private Integer categoryId;
    private String deliveryMethod;
    private List<CategoryInfoDto> displayCategoryInfo;
    private String imagePath;
    private Long itemCountOfProduct;
    private Long itemId;
    private String itemName;
    private String manufacture;
    private String matchType;
    private Long matchingResultId;
    private Long productId;
    private String productName;
    private String pvLast28Day;
    private Double rating;
    private Long ratingCount;
    private Long salePrice;
    private String sponsored;
    private Long venderId;
}
