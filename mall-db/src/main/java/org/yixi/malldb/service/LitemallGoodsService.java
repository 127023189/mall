package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_goods(商品基本信息表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallGoodsService extends IService<LitemallGoods> {

    List<LitemallGoods> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer limit, String sort, String order);

    boolean checkExistByName(String name);
}
