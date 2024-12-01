package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallCoupon;
import org.yixi.malldb.service.LitemallCouponService;
import org.yixi.malldb.mapper.LitemallCouponMapper;
import org.springframework.stereotype.Service;
import org.yixi.malldb.util.CouponConstant;

import java.util.List;
import java.util.Random;

/**
* @author hp
* @description 针对表【litemall_coupon(优惠券信息及规则表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCouponServiceImpl extends ServiceImpl<LitemallCouponMapper, LitemallCoupon>
    implements LitemallCouponService{

    @Override
    public List<LitemallCoupon> querySelective(String name, Short type, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        if(type!=null){
            queryWrapper.eq("type", type);
        }
        if(status!=null){
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallCoupon> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }


    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public String generateCode() {
        String code = getRandomNum(8);
        while(findByCode(code) != null){
            code = getRandomNum(8);
        }
        return code;
    }

    @Override
    public List<LitemallCoupon> queryRegister() {
        // 创建查询条件
        QueryWrapper<LitemallCoupon> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", CouponConstant.TYPE_REGISTER)
                .eq("status", CouponConstant.STATUS_NORMAL)
                .eq("deleted", false);

    // 执行查询并返回结果
        return this.list(queryWrapper);
    }

    private LitemallCoupon findByCode(String code) {
        LambdaQueryWrapper<LitemallCoupon> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LitemallCoupon::getCode, code) // code 等于指定值
                    .eq(LitemallCoupon::getType, CouponConstant.TYPE_CODE) // type 等于常量
                    .eq(LitemallCoupon::getStatus, CouponConstant.STATUS_NORMAL) // status 等于常量
                    .eq(LitemallCoupon::getDeleted, false); // deleted 为 false

        // 查询结果列表
        List<LitemallCoupon> couponList = this.list(queryWrapper);

        if (couponList.size() > 1) {
            throw new RuntimeException("存在多个相同 code 的优惠券");
        } else if (couponList.isEmpty()) {
            return null;
        } else {
            return couponList.get(0);
        }
    }
}




