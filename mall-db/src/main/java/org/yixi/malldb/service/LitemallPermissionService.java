package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【litemall_permission(权限表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallPermissionService extends IService<LitemallPermission> {

    Set<String> queryByRoleIds(Integer[] roleIds);

    boolean checkSuperPermission(List<Integer> roleIds);

    Set<String> queryByRoleId(List<Integer> roleIds);
}
