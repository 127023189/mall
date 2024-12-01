package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallAdmin;
import org.yixi.malldb.service.LitemallAdminService;
import org.yixi.malldb.mapper.LitemallAdminMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author hp
* @description 针对表【litemall_admin(管理员表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallAdminServiceImpl extends ServiceImpl<LitemallAdminMapper, LitemallAdmin>
    implements LitemallAdminService{

    @Override
    public List<LitemallAdmin> findAdmin(String username) {
        QueryWrapper<LitemallAdmin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        List<LitemallAdmin> list = this.list(wrapper);
        return list;
    }

    @Override
    public List<LitemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order) {
        QueryWrapper wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like("username", username);
        }
        wrapper.eq("deleted", false);
        // 处理排序条件
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            wrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallAdmin> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, wrapper).getRecords();
    }


}




