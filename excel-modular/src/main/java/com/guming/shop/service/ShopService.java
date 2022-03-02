package com.guming.shop.service;

import com.guming.shop.model.ShopBo;
import com.guming.shop.model.ShopData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wujie
 * @date 2022/3/2 14:26
 */
public interface ShopService {

    /**
     *导入数据
     * @param file
     * @return
     */
    List<ShopData> importShopData(MultipartFile file);

    /**
     * 根据id获取门店信息
     * @param shopId
     * @return
     */
    public ShopBo getShopInfo(String shopId);

    /**
     * 导出数据
     * @param response
     */
    void exportShopData(HttpServletResponse response);
}