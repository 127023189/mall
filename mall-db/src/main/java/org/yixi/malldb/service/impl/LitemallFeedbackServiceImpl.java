package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallFeedback;
import org.yixi.malldb.service.LitemallFeedbackService;
import org.yixi.malldb.mapper.LitemallFeedbackMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_feedback(意见反馈表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallFeedbackServiceImpl extends ServiceImpl<LitemallFeedbackMapper, LitemallFeedback>
    implements LitemallFeedbackService{

    @Override
    public List<LitemallFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallFeedback> queryWrapper = new QueryWrapper<>();

        // 添加条件
        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like("username", username);
        }
        queryWrapper.eq("deleted", false);

        // 设置排序
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            boolean isAsc = "ASC".equalsIgnoreCase(order);
            queryWrapper.orderBy(true, isAsc, sort); // 按照字段排序
        }

        // 分页查询
        Page<LitemallFeedback> pageResult = new Page<>(page, limit);
        return this.page(pageResult, queryWrapper).getRecords();
    }
}




