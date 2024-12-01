package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallCollect;
import org.yixi.malldb.service.LitemallCollectService;
import org.yixi.malldb.mapper.LitemallCollectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_collect(收藏表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCollectServiceImpl extends ServiceImpl<LitemallCollectMapper, LitemallCollect>
    implements LitemallCollectService{

    @Override
    public List<LitemallCollect> querySelective(String userId, String valueId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userId)){
            queryWrapper.eq("user_id", userId);
        }
        if(!StringUtils.isEmpty(valueId)){
            queryWrapper.eq("value_id", valueId);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallCollect> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




