package com.guming.shop.controller;

import com.guming.api.pojo.Result;
import com.guming.shop.model.ShopBo;
import com.guming.shop.model.ShopData;
import com.guming.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wujie
 * @date 2022/3/2 16:33
 */
@RestController
public class ExcelController {

    @Autowired
    private ShopService shopService;

    /**
     * excel导入门店信息
     * @param file 上传的文件
     * @return
     */
    @PostMapping("/import")
    public Result importShopData(MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件上传失败！");
        }
        List<ShopData> shopData = shopService.importShopData(file);
        if (shopData.isEmpty()) {
            return Result.error("文件解析失败！");
        }
        return Result.success(shopData);
    }

    /**
     * excel导出门店信息
     * @param response
     */
    @GetMapping("/export")
    public void exportShopData(HttpServletResponse response) {
        shopService.exportShopData(response);
    }

    /**
     * 根据id查询门店信息
     * @param shopId
     * @return
     */
    @GetMapping("/selectById/{shopId}")
    public Result selectExcelData(@PathVariable("shopId") String shopId) {

        if ("".equals(shopId)) {
            return Result.error("门店id不能为空");
        }
        ShopBo shopBo = shopService.getShopInfo(shopId);
        if (shopBo == null) {
            return Result.error("门店id不存在");
        }
        return Result.success(shopBo);
    }

}
