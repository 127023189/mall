package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallUser;
import org.yixi.malldb.service.LitemallUserService;
import org.yixi.malldb.mapper.LitemallUserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_user(用户表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallUserServiceImpl extends ServiceImpl<LitemallUserMapper, LitemallUser>
    implements LitemallUserService{

    @Override
    public List<LitemallUser> querySelective(String username, String mobile, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallUser> queryWrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(username)) {
            queryWrapper.like("username", username);
        }
        if (!StringUtils.isEmpty(mobile)) {
            queryWrapper.eq("mobile", mobile);
        }
        queryWrapper.eq("deleted", false);

        // 处理排序条件
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort); // "ASC" 或 "DESC"
        }

        // 创建分页对象
        Page<LitemallUser> pageInfo = new Page<>(page, limit);
        // 执行查询并返回分页结果
        List<LitemallUser> records = this.page(pageInfo, queryWrapper).getRecords();
        return records;
    }

    @Override
    public List<LitemallUser> queryByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(username)){
            queryWrapper.eq("username",username);
        }
        return this.list(queryWrapper);
    }

    @Override
    public LitemallUser queryByOid(String openId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(openId)){
            queryWrapper.eq("weixin_openid",openId);
        }
        return this.getOne(queryWrapper);
    }
}




