package com.guming.shop.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wujie
 * @date 2022/3/2 11:20
 * 门店详情信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopBo {

    @ExcelProperty(value = "门店ID")
    private String id;

    @ExcelProperty(value = "门店名称")
    private String shopName;

    @ExcelProperty(value = "曝光量--总和")
    private Integer exposureSum;

    @ExcelProperty(value = "详情点击量--总和")
    private Integer clicksSum;

    @ExcelProperty(value = "订单量--总和")
    private Integer orderQuantitySum;

    @ExcelProperty(value = "订单交易额--总和")
    private Integer orderTransactionSum;
}
