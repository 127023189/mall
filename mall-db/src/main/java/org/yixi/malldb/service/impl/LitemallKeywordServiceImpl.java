package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallKeyword;
import org.yixi.malldb.service.LitemallKeywordService;
import org.yixi.malldb.mapper.LitemallKeywordMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_keyword(关键字表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallKeywordServiceImpl extends ServiceImpl<LitemallKeywordMapper, LitemallKeyword>
    implements LitemallKeywordService{

    @Override
    public List<LitemallKeyword> querySelective(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.like("keyword", keyword);
        }
        if(!StringUtils.isEmpty(url)){
            queryWrapper.eq("url", url);
        }
        queryWrapper.eq("deleted", false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            boolean isAsc = "ASC".equalsIgnoreCase(order);
            queryWrapper.orderBy(true, isAsc, sort); // 按照字段排序
        }
        // 分页查询
        Page<LitemallKeyword> pageResult = new Page<>(page, limit);
        return this.page(pageResult, queryWrapper).getRecords();
    }
}




