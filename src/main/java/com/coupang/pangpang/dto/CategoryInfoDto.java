package com.coupang.pangpang.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryInfoDto {
    private String categoryHierarchy;
    private Integer leafCategoryCode;
    private Integer rootCategoryCode;
}
