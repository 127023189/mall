package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallCoupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_coupon(优惠券信息及规则表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCouponService extends IService<LitemallCoupon> {

    List<LitemallCoupon> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order);

    String generateCode();

    List<LitemallCoupon> queryRegister();
}
