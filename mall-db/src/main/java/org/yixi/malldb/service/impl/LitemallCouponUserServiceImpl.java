package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallCouponUser;
import org.yixi.malldb.service.LitemallCouponUserService;
import org.yixi.malldb.mapper.LitemallCouponUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_coupon_user(优惠券用户使用表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCouponUserServiceImpl extends ServiceImpl<LitemallCouponUserMapper, LitemallCouponUser>
    implements LitemallCouponUserService{

    @Override
    public List<LitemallCouponUser> queryList(Integer userId, Integer couponId, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (userId != null){
            queryWrapper.eq("user_id", userId);
        }
        if (couponId != null){
            queryWrapper.eq("coupon_id", couponId);
        }
        if (status != null){
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }

        Page<LitemallCouponUser> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();

    }

    @Override
    public Integer countUserAndCoupon(Integer userId, Integer couponId) {
        QueryWrapper <LitemallCouponUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("coupon_id", couponId);
        queryWrapper.eq("deleted", false);
        return this.count(queryWrapper);
    }

    @Override
    public LitemallCouponUser queryOne(Integer userId, Integer couponId) {
        QueryWrapper <LitemallCouponUser> queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        if(couponId != null){
            queryWrapper.eq("coupon_id", couponId);
        }
        queryWrapper.eq("deleted", false);

        return this.getOne(queryWrapper);
    }

    @Override
    public List<LitemallCouponUser> queryAll(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId == null){
            queryWrapper.eq("user_id", userId);
        }
        queryWrapper.eq("deleted", false);
        return this.list(queryWrapper);
    }
}




