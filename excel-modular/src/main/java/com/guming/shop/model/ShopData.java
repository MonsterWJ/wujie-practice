package com.guming.shop.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;


/**
 * @author Wujie
 * @date 2022/3/2 12:50
 * 对应excel每行数据
 */
@Data
public class ShopData {

    @ExcelProperty(value = "日期")
    @DateTimeFormat("yyyy-MM-dd")
    private String date;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty(value = "门店ID")
    private String id;

    @ExcelProperty(value = "门店名称")
    private String shopName;

    @ExcelProperty(value = "曝光量")
    private Integer exposure;

    @ExcelProperty(value = "详情点击量")
    private Integer clicks;

    @ExcelProperty(value = "订单量")
    private Integer orderQuantity;

    @ExcelProperty(value = "订单交易额")
    private Integer orderTransaction;

}
