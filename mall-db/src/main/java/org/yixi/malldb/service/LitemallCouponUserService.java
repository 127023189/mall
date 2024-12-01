package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallCouponUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_coupon_user(优惠券用户使用表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCouponUserService extends IService<LitemallCouponUser> {

    List<LitemallCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer limit, String sort, String order);

    Integer countUserAndCoupon(Integer userId, Integer couponId);

    LitemallCouponUser queryOne(Integer userId, Integer couponId);

    List<LitemallCouponUser> queryAll(Integer userId);
}
