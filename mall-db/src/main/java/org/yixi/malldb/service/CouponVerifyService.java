package org.yixi.malldb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yixi.malldb.bean.LitemallCart;
import org.yixi.malldb.bean.LitemallCoupon;
import org.yixi.malldb.bean.LitemallCouponUser;
import org.yixi.malldb.util.CouponConstant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CouponVerifyService {

    @Autowired
    private LitemallCouponService couponService;

    @Autowired
    private LitemallCouponUserService couponUserService;

    @Autowired
    private LitemallGoodsService goodsService;

    /**
     * 优惠券检查服务
     * @param userId
     * @param couponId
     * @param userCouponId
     * @param checkedGoodsPrice
     * @param cartList
     * @return
     */
    public Object checkCoupon(Integer userId, Integer couponId, Integer userCouponId, BigDecimal checkedGoodsPrice, List<LitemallCart> cartList){
        LitemallCoupon coupon = couponService.getById(couponId);
        if(coupon == null || coupon.getDeleted() == 1){
            return null;
        }

        LitemallCouponUser couponUser = couponUserService.getById(userCouponId);
        if(couponUser == null){
            couponUser = couponUserService.queryOne(userId, couponId);  // 查询优惠券
        }else if(!couponId.equals(couponUser.getCouponId())){ // 检测优惠券是否属于当前用户
            return null;
        }

        if(couponUser == null){
            return null;
        }

        Integer timeType = coupon.getTimeType();
        Integer days = coupon.getDays();
        LocalDateTime now = LocalDateTime.now();
        if(timeType.equals(CouponConstant.TIME_TYPE_TIME)){
            if(now.isBefore(coupon.getStartTime()) || now.isAfter(coupon.getEndTime())){ // 检查时间
                return null;
            }
        }else if(timeType.equals(CouponConstant.TIME_TYPE_DAYS)){
            LocalDateTime expired = couponUser.getAddTime().plusDays(days); // 优惠券过期时间
            if(now.isAfter(expired)){ // 检查时间
                return null;
            }
        }else {
            return  null;
        }

        // 检测商品是否符合
        Map<Integer,List<LitemallCart>> cartMap = new HashMap<>(); // 商品分类
        // 可以是用优惠卷的商品或分类
        List<Integer> goodsValueList = new ArrayList<>(Arrays.asList(coupon.getGoodsValue()));
        Integer type = coupon.getType(); // 优惠券类型

        if(type.equals(CouponConstant.GOODS_TYPE_CATEGORY) ||
           type.equals(CouponConstant.GOODS_TYPE_ARRAY)){
            for(LitemallCart cart : cartList){
                Integer key = type.equals(CouponConstant.GOODS_TYPE_CATEGORY)?cart.getGoodsId():
                        goodsService.getById(cart.getGoodsId()).getCategoryId(); // 商品分类
                List<LitemallCart> carts = cartMap.get(key);
                if(carts == null){
                    carts = new ArrayList<>();
                }
                carts.add(cart); // 添加到对应分类
                cartMap.put(key, carts);
            }

            // 购物车中可以使用优惠券的商品或分类
            goodsValueList.retainAll(cartMap.keySet()); // 取出分类交集

            BigDecimal totalPrice = new BigDecimal(0);

            for(Integer goodsId : goodsValueList) {
                List<LitemallCart> carts = cartMap.get(goodsId); // 获取购物车商品

                // 计算总价
                for (LitemallCart cart : carts) {
                    totalPrice = totalPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
                }
            }
                // 是否达到优惠卷满减金额
                if (totalPrice.compareTo(coupon.getMin()) == -1) {
                    return null;
                }
            }

            Integer status = coupon.getStatus(); // 优惠券状态
            if(!status.equals(CouponConstant.STATUS_NORMAL)){
                return null;
            }

            // 是否满足最低消费
            if(checkedGoodsPrice.compareTo(coupon.getMin()) == -1){
                return null;
            }

            return coupon;


    }
}
