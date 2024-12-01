package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallSearchHistory;
import org.yixi.malldb.service.LitemallSearchHistoryService;
import org.yixi.malldb.mapper.LitemallSearchHistoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_search_history(搜索历史表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallSearchHistoryServiceImpl extends ServiceImpl<LitemallSearchHistoryMapper, LitemallSearchHistory>
    implements LitemallSearchHistoryService{

    @Override
    public List<LitemallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallSearchHistory> queryWrapper = new QueryWrapper<>();

        // 添加条件
        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("keyword", keyword);
        }
        queryWrapper.eq("deleted", false);

        // 设置排序
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            boolean isAsc = "ASC".equalsIgnoreCase(order);
            queryWrapper.orderBy(true, isAsc, sort); // 按照字段排序
        }

        // 分页查询
        Page<LitemallSearchHistory> pageResult = new Page<>(page, limit);
        return this.page(pageResult, queryWrapper).getRecords();
    }
}




