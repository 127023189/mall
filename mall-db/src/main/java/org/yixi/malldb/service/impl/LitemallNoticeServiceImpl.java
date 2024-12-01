package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallNotice;
import org.yixi.malldb.service.LitemallNoticeService;
import org.yixi.malldb.mapper.LitemallNoticeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_notice(通知表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallNoticeServiceImpl extends ServiceImpl<LitemallNoticeMapper, LitemallNotice>
    implements LitemallNoticeService{


    @Override
    public List<LitemallNotice> querySelective(String title, String content, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)){
            queryWrapper.like("title", title);
        }
        if(!StringUtils.isEmpty(content)){
            queryWrapper.like("content", content);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallNotice> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




