package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
* @author hp
* @description 针对表【litemall_cart(购物车商品表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCartService extends IService<LitemallCart> {

    void updateProduct(Integer id, String goodsSn, String name, BigDecimal price, String url);

    List<LitemallCart> queryByUid(Integer userId);

    LitemallCart queryExist(Integer goodsId, Integer productId, Integer userId);

    LitemallCart findById(Integer userId, Integer id);

    void updateCheck(Integer userId, List<Integer> productIds, Boolean isChecked);

    void delete(List<Integer> productIds, Integer userId);

    List<LitemallCart> queryByUidAndChecked(Integer userId);
}
