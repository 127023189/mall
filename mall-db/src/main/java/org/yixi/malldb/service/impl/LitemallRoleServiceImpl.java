package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallRole;
import org.yixi.malldb.service.LitemallRoleService;
import org.yixi.malldb.mapper.LitemallRoleMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【litemall_role(角色表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallRoleServiceImpl extends ServiceImpl<LitemallRoleMapper, LitemallRole>
    implements LitemallRoleService{

    @Override
    public Set<String> queryByids(Integer[] roleIds) {
        Set<String> roles = new HashSet<>();
        if(roleIds==null||roleIds.length==0){
            return roles;
        }
        QueryWrapper<LitemallRole> wrapper = new QueryWrapper<>();
        wrapper.in("id", Arrays.asList(roleIds))
                .eq("enabled", true)
                .eq("deleted", false);
        List<LitemallRole> roleList = this.list(wrapper);
        for (LitemallRole role : roleList) {
            roles.add(role.getName());
        }
        return roles;
    }

    @Override
    public List<LitemallRole> querySelective(String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }

        Page<LitemallRole> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public boolean checkExist(String name) {
        QueryWrapper<LitemallRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).eq("deleted", false);
        return this.count(queryWrapper) != 0;
    }
}




