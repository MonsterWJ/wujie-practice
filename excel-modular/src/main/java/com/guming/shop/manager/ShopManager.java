package com.guming.shop.manager;

import com.guming.shop.model.ShopBo;
import com.guming.shop.model.ShopData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Wujie
 * @date 2022/3/2 11:37
 */
public interface ShopManager {

    /**
     * 导入数据
     * @param file
     * @return
     */
    List<ShopData> importShopData(MultipartFile file);

    /**
     * 根据id获取门店信息
     * @param shopId
     * @return
     */
    ShopBo getShopInfo(String shopId);

    /**
     * 导出
     * @param response
     */
    void exportShopData(HttpServletResponse response);
}
