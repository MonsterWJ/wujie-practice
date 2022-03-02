package com.guming.shop.service;

import com.guming.shop.manager.ShopManager;
import com.guming.shop.model.ShopBo;
import com.guming.shop.model.ShopData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wujie
 * @date 2022/3/2 14:49
 */

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopManager shopManager;

    @Override
    public List<ShopData> importShopData(MultipartFile file) {

        return shopManager.importShopData(file);


    }

    @Override
    public ShopBo getShopInfo(String shopId) {
        return shopManager.getShopInfo(shopId);
    }

    @Override
    public void exportShopData(HttpServletResponse response) {
        shopManager.exportShopData(response);
    }
}