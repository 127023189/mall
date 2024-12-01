package org.yixi.malldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yixi.malldb.bean.LitemallCoupon;
import org.yixi.malldb.bean.LitemallCouponUser;
import org.yixi.malldb.util.CouponConstant;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignService {

    @Autowired
    private LitemallCouponUserService couponUserService;

    @Autowired
    private LitemallCouponService couponService;

    /**
     * 优惠券领取服务
     * @param userId
     */
    public void assignForRegister(Integer userId){
        List<LitemallCoupon> couponList = couponService.queryRegister();
        for(LitemallCoupon coupon : couponList){
            Integer couponId = coupon.getId();

            Integer count = couponUserService.countUserAndCoupon(userId, couponId);

            // 如果已经领取优惠券则不进行领取
            if(count > 0){
                continue;
            }

            Integer limit = coupon.getLimit();
            while(limit > 0){
                LitemallCouponUser couponUser = new LitemallCouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Integer timeType = coupon.getTimeType();
                if(timeType.equals(CouponConstant.TIME_TYPE_TIME)){
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                }
                else{
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                couponUser.setAddTime(LocalDateTime.now());
                couponUser.setUpdateTime(LocalDateTime.now());
                couponUserService.save(couponUser);

                limit--;
            }
        }
    }
}
