package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallGroupon;
import org.yixi.malldb.service.LitemallGrouponService;
import org.yixi.malldb.mapper.LitemallGrouponMapper;
import org.springframework.stereotype.Service;
import org.yixi.malldb.util.GrouponConstant;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_groupon(团购活动表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallGrouponServiceImpl extends ServiceImpl<LitemallGrouponMapper, LitemallGroupon>
    implements LitemallGrouponService{

    @Override
    public List<LitemallGroupon> querySelective(String rulesId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(rulesId)){
            queryWrapper.eq("rules_id", rulesId);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallGroupon> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public List<LitemallGroupon> queryJoinRecord(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(id!=null){
            queryWrapper.eq("groupon_id", id);
        }
        queryWrapper.eq("deleted", false);
        queryWrapper.eq("status", GrouponConstant.STATUS_NONE);

        queryWrapper.orderBy(true, false, "add_time");
        return this.list(queryWrapper);

    }
}




