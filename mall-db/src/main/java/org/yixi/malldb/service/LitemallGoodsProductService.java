package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallGoodsProduct;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hp
* @description 针对表【litemall_goods_product(商品货品表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallGoodsProductService extends IService<LitemallGoodsProduct> {

    void addStock(Integer productId, Integer number);
}
