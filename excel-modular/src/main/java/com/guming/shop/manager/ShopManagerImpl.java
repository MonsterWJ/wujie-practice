package com.guming.shop.manager;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.guming.shop.model.ShopBo;
import com.guming.shop.model.ShopData;
import org.apache.commons.codec.Charsets;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Wujie
 * @date 2022/3/2 11:46
 */
@Component
public class ShopManagerImpl implements ShopManager {

    //门店详情汇总信息
    private static final Map<String,ShopBo> shopMap = new HashMap<>();

    //门店日期信息记录
    private static final List<ShopData> shopList = new ArrayList<>();

    @Override
    public List<ShopData> importShopData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), ShopData.class, new AnalysisEventListener<ShopData>() {

                final List<ShopData> shopDataList = new ArrayList<>();
                @Override
                public void invoke(ShopData shopData, AnalysisContext analysisContext) {
                    if (shopData != null) {
                        shopDataList.add(shopData);
                    }
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    try {
                        file.getInputStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //转换成门店详情信息
                    toShopDetails(shopDataList);
                    //二次上传
                    if (!shopList.isEmpty()) {
                        shopList.clear();
                    }
                    shopList.addAll(shopDataList);
                }
            }).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shopList;
    }

    /**
     * 门店信息计算总和
     * @param shopDataList 门店每日信息
     */
    private void toShopDetails(List<ShopData> shopDataList) {
        //将相同的放在一起
        shopDataList.sort(Comparator.comparing(ShopData::getId));

        for (int i = 0; i < shopDataList.size(); i++) {
            ShopData shopData = shopDataList.get(i);
            String shopId = shopData.getId();
            Integer exposureSum =shopData.getExposure();
            Integer clicksSum = shopData.getClicks();
            Integer orderQuantitySum = shopData.getOrderQuantity();
            Integer orderTransactionSum = shopData.getOrderTransaction();

            for (int j = i+1; j < shopDataList.size(); j++) {
                ShopData shopData1 = shopDataList.get(j);
                if (shopData1.getId().equals(shopId)) {
                    exposureSum += shopData1.getExposure();
                    clicksSum += shopData1.getClicks();
                    orderQuantitySum += shopData1.getOrderQuantity();
                    orderTransactionSum += shopData1.getOrderTransaction();
                }else {
                    shopMap.put(shopId,
                            new ShopBo(shopId,shopData.getShopName(),
                                    exposureSum,clicksSum,orderQuantitySum,orderTransactionSum));
                    i=j-1;
                    break;
                }
            }
        }
    }

    public ShopBo getShopInfo(String shopId) {
        return shopMap.get(shopId);
    }

    @Override
    public void exportShopData(HttpServletResponse response) {
        String fileName = new String("门店信息记录.xlsx".getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        ExcelWriter excelWriter = EasyExcel.write(out).build();
        //导出第一个sheet
        WriteSheet sheet1 = getSheet(0,"门店详细数据",ShopData.class);
        excelWriter.write(shopList,sheet1);
        //导出第二个sheet
        WriteSheet sheet2 = getSheet(1,"门店汇总数据",ShopBo.class);
        excelWriter.write(new ArrayList<>(shopMap.values()),sheet2);

        excelWriter.finish();
    }

    /**
     *
     * 获取sheet
     */
    private WriteSheet getSheet(Integer sheetNo,String sheetName,Class<?> clazz) {
        return EasyExcel.writerSheet(sheetNo,sheetName).head(clazz).build();
    }
}
