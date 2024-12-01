package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
* @author hp
* @description 针对表【litemall_role(角色表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallRoleService extends IService<LitemallRole> {

    Set<String> queryByids(Integer[] roleIds);

    List<LitemallRole> querySelective(String name, Integer page, Integer limit, String sort, String order);

    boolean checkExist(String name);
}
