package com.kingthy.platform.job;

import com.kingthy.platform.dto.report.*;
import com.kingthy.platform.entity.report.*;
import com.kingthy.platform.service.basedata.*;
import com.kingthy.platform.service.report.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Scheduling(描述其作用)
 * <p>
 * 赵生辉 2017年07月13日 14:03
 *
 * @version 1.0.0
 */
@Component
@Configurable
public class SchedulingJob
{

    @Autowired
    private StyleCategoryService styleCategoryService;

    @Autowired
    private ClassCategoryService classCategoryService;

    @Autowired
    private CoatCategoryService coatCategoryService;

    @Autowired
    private DressCategoryService dressCategoryService;

    @Autowired
    private MaterielCategoryService materielCategoryService;

    @Autowired
    private PantsCategoryService pantsCategoryService;

    @Autowired
    private SegmentCategoryService segmentCategoryService;

    @Autowired
    private SkirtCategoryService skirtCategoryService;

    @Autowired
    private SuitCategoryService suitCategoryService;


    /**
     * 定时更新风格的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateStyleGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = styleCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新类目的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateClassGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = classCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新上衣的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateCoatGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = coatCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新连衣裙的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateDressGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = dressCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新材质的商品数量
     */
   // @Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateMaterielGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = materielCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新裤装的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updatePantGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = pantsCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新价格分段的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateSegmentGoodsNumPrice()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = segmentCategoryService.updateGoodsNumPrice();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新年龄的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateSegmentGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = segmentCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新半身裙的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateSkirtGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = skirtCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }

    /**
     * 定时更新套装的商品数量
     */
    //@Scheduled(cron = "0 0/1 8-20 * * ?")
    public void updateSuitGoodsNum()
    {
        Date date = new Date();
        System.out.println("定时任务开始："+date);
        int result = suitCategoryService.updateGoodsNum();
        System.out.println("更新商品数量结果为："+result);
    }


}
