package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yixi.malldb.bean.LitemallCart;
import org.yixi.malldb.service.LitemallCartService;
import org.yixi.malldb.mapper.LitemallCartMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author hp
* @description 针对表【litemall_cart(购物车商品表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCartServiceImpl extends ServiceImpl<LitemallCartMapper, LitemallCart>
    implements LitemallCartService{

    @Override
    public void updateProduct(Integer id, String goodsSn, String name, BigDecimal price, String url) {
        // 构建更新实体
        LitemallCart cart = new LitemallCart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(name);

        // 使用 MyBatis-Plus 提供的 UpdateWrapper 来定义更新条件
        UpdateWrapper<LitemallCart> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("product_id", id); // 条件：product_id 等于指定的 id

        // 执行更新操作
        this.update(cart, updateWrapper);
    }

    @Override
    public List<LitemallCart> queryByUid(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        queryWrapper.eq("deleted", false);
        return this.list(queryWrapper);
    }

    @Override
    public LitemallCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        queryWrapper.eq("product_id", productId);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted", false);
        return this.getOne(queryWrapper);
    }

    @Override
    public LitemallCart findById(Integer userId, Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("deleted", false);
        return this.getOne(queryWrapper);
    }

    @Override
    public void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        if(productIds != null){
            queryWrapper.in("product_id", productIds);
        }
        queryWrapper.eq("deleted", false);

        LitemallCart cart = new LitemallCart();
        cart.setChecked(isChecked ? 1 : 0);
        cart.setUpdateTime(LocalDateTime.now());

        this.update(cart, queryWrapper);
    }

    @Override
    public void delete(List<Integer> productIds, Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(productIds != null){
            queryWrapper.in("product_id", productIds);
        }
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        queryWrapper.eq("deleted", false);

        this.remove(queryWrapper);
    }

    @Override
    public List<LitemallCart> queryByUidAndChecked(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        queryWrapper.eq("checked", true);
        queryWrapper.eq("deleted", false);

        return this.list(queryWrapper);
    }
}




