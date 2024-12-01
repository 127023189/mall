package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallAftersale;
import org.yixi.malldb.service.LitemallAftersaleService;
import org.yixi.malldb.mapper.LitemallAftersaleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_aftersale(售后表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallAftersaleServiceImpl extends ServiceImpl<LitemallAftersaleMapper, LitemallAftersale>
    implements LitemallAftersaleService{

    @Override
    public List<LitemallAftersale> querySelective(Integer orderId, String aftersaleSn, Short status, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(orderId != null){
            queryWrapper.eq("order_id", orderId);
        }
        if(!StringUtils.isEmpty(aftersaleSn)){
            queryWrapper.like("aftersale_sn", aftersaleSn);
        }
        if(status != null){
            queryWrapper.eq("status", status);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallAftersale> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




