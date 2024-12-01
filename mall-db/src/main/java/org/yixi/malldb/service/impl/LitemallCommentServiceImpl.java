package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallComment;
import org.yixi.malldb.service.LitemallCommentService;
import org.yixi.malldb.mapper.LitemallCommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_comment(评论表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCommentServiceImpl extends ServiceImpl<LitemallCommentMapper, LitemallComment>
    implements LitemallCommentService{

    @Override
    public List<LitemallComment> querySelective(String userId, String valueId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallComment> queryWrapper = new QueryWrapper<>();

        // type=2 是订单商品回复，这里过滤
        queryWrapper.ne("type", (byte) 2);

        if (!StringUtils.isEmpty(userId)) {
            queryWrapper.eq("user_id", Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            queryWrapper.eq("value_id", Integer.valueOf(valueId))
                        .eq("type", (byte) 0);
        }
        queryWrapper.eq("deleted", false);

        // 设置排序
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                queryWrapper.orderByAsc(sort);
            } else if ("desc".equalsIgnoreCase(order)) {
                queryWrapper.orderByDesc(sort);
            }
        }

        // 分页查询
        Page<LitemallComment> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }
}




