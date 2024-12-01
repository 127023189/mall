package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yixi.malldb.bean.LitemallPermission;
import org.yixi.malldb.bean.LitemallRole;
import org.yixi.malldb.service.LitemallPermissionService;
import org.yixi.malldb.mapper.LitemallPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【litemall_permission(权限表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallPermissionServiceImpl extends ServiceImpl<LitemallPermissionMapper, LitemallPermission>
    implements LitemallPermissionService{

    @Override
    public Set<String> queryByRoleIds(Integer[] roleIds) {
        Set<String> permissions = new HashSet<>();
        if(roleIds==null||roleIds.length==0){
            return permissions;
        }
        QueryWrapper<LitemallPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", Arrays.asList(roleIds))  // 使用 role_id 字段进行查询
                .eq("deleted", false);                  // 查询 deleted = false 的记录

        List<LitemallPermission> permissionList = this.list(queryWrapper);

        for (LitemallPermission permission : permissionList) {
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    @Override
    public boolean checkSuperPermission(List<Integer> roleIds) {
        if(roleIds == null || roleIds.isEmpty()){
            return false;
        }
         QueryWrapper<LitemallPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds)
                .eq("permission", "*")
                .eq("deleted", false);
        return this.count(queryWrapper) != 0;
    }

    @Override
    public Set<String> queryByRoleId(List<Integer> roleIds) {
        Set<String> permissions = new HashSet<String>();
        if(roleIds == null || roleIds.isEmpty()){
            return permissions;
        }

        QueryWrapper<LitemallPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds)
                    .eq("deleted", false);

        // 查询符合条件的 LitemallPermission 列表
        List<LitemallPermission> permissionList = this.list(queryWrapper);

        for(LitemallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;


    }
}




