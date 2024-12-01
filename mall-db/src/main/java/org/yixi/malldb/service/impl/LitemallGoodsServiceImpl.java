package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallGoods;
import org.yixi.malldb.service.LitemallGoodsService;
import org.yixi.malldb.mapper.LitemallGoodsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_goods(商品基本信息表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallGoodsServiceImpl extends ServiceImpl<LitemallGoodsMapper, LitemallGoods>
    implements LitemallGoodsService{

    @Override
    public List<LitemallGoods> querySelective(Integer goodsId, String goodsSn, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(goodsId!=null && goodsId!=0){
            queryWrapper.eq("id", goodsId);
        }
        if(!StringUtils.isEmpty(goodsSn)){
            queryWrapper.eq("goods_sn", goodsSn);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallGoods> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public boolean checkExistByName(String name) {
        QueryWrapper<LitemallGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name)  // 条件：name 等于指定的值
                    .eq("is_on_sale", true)  // 条件：is_on_sale 为 true
                    .eq("deleted", false);  // 条件：deleted 为 false

        // 查询满足条件的记录数
        return this.count(queryWrapper) > 0;
    }
}




