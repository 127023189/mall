package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallBrand;
import org.yixi.malldb.service.LitemallBrandService;
import org.yixi.malldb.mapper.LitemallBrandMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_brand(品牌商表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallBrandServiceImpl extends ServiceImpl<LitemallBrandMapper, LitemallBrand>
    implements LitemallBrandService{

    @Override
    public List<LitemallBrand> querySelective(String id, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallBrand> queryWrapper= new QueryWrapper<>();
        if(!StringUtils.isEmpty(id)){
            queryWrapper.eq("id", id);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallBrand> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




