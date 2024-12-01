package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallAd;
import org.yixi.malldb.bean.LitemallAdmin;
import org.yixi.malldb.service.LitemallAdService;
import org.yixi.malldb.mapper.LitemallAdMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author hp
* @description 针对表【litemall_ad(广告表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallAdServiceImpl extends ServiceImpl<LitemallAdMapper, LitemallAd>
    implements LitemallAdService{

    @Override
    public List<LitemallAd> querySelective(String username, String content,Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallAd> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(username)){
            queryWrapper.like("username",username); // 模糊查询
        }
        if(!StringUtils.isEmpty(content)){
            queryWrapper.like("content",content);
        }
        queryWrapper.eq("deleted",false);
        // 排序
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                queryWrapper.orderByAsc(sort);
            } else if ("desc".equalsIgnoreCase(order)) {
                queryWrapper.orderByDesc(sort);
            }
        }
        Page<LitemallAd> pageResult = new Page<>(page, limit);
        return this.page(pageResult,queryWrapper).getRecords();

    }


    @Override
    public void add(LitemallAd ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        this.save(ad);
    }

    @Override
    public LitemallAd findById(Integer id) {
        return this.findById(id);
    }
}




