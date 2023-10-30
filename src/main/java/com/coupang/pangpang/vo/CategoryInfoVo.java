package com.coupang.pangpang.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CategoryInfoVo {
    private String categoryHierarchy;
    private Integer leafCategoryCode;
    private Integer rootCategoryCode;
}
