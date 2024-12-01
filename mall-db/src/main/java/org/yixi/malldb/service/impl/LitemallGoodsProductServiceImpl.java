package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yixi.malldb.bean.LitemallGoodsProduct;
import org.yixi.malldb.service.LitemallGoodsProductService;
import org.yixi.malldb.mapper.LitemallGoodsProductMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
* @author hp
* @description 针对表【litemall_goods_product(商品货品表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallGoodsProductServiceImpl extends ServiceImpl<LitemallGoodsProductMapper, LitemallGoodsProduct>
    implements LitemallGoodsProductService{

    @Override
    public void addStock(Integer productId, Integer number) {
        UpdateWrapper<LitemallGoodsProduct> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", productId)
                 .setSql("number = number + " + number) // 使用 setSql 实现字段自增
                 .set("update_time", LocalDateTime.now());
        this.update(updateWrapper);
    }
}




