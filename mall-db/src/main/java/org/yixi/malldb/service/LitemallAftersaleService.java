package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallAftersale;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_aftersale(售后表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallAftersaleService extends IService<LitemallAftersale> {

    List<LitemallAftersale> querySelective(Integer orderId, String aftersaleSn, Short status, Integer page, Integer limit, String sort, String order);
}
