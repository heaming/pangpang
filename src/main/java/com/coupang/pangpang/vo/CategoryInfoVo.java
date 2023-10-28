package com.coupang.pangpang.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryInfoVo {
    private String categoryHierarchy;
    private Integer leafCategoryCode;
    private Integer rootCategoryCode;
}
