package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallTopic;
import org.yixi.malldb.service.LitemallTopicService;
import org.yixi.malldb.mapper.LitemallTopicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_topic(专题表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallTopicServiceImpl extends ServiceImpl<LitemallTopicMapper, LitemallTopic>
    implements LitemallTopicService{

    @Override
    public List<LitemallTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title", title);
        }
        if(!StringUtils.isEmpty(subtitle)){
            queryWrapper.like("subtitle", subtitle);
        }
        queryWrapper.eq("deleted", false);

        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallTopic> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();

    }
}




