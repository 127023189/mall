package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallIssue;
import org.yixi.malldb.service.LitemallIssueService;
import org.yixi.malldb.mapper.LitemallIssueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_issue(常见问题表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallIssueServiceImpl extends ServiceImpl<LitemallIssueMapper, LitemallIssue>
    implements LitemallIssueService{

    @Override
    public List<LitemallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(question)){
            queryWrapper.like("question", question);
        }

        queryWrapper.eq("deleted", false);

        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallIssue> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




