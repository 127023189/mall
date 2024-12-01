package org.yixi.malldb.service;

import org.apache.ibatis.annotations.ResultMap;
import org.yixi.malldb.bean.LitemallAd;
import org.yixi.malldb.bean.LitemallAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_admin(管理员表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallAdminService extends IService<LitemallAdmin> {

    @ResultMap("BaseResultMap")
    List<LitemallAdmin> findAdmin(String username);


    List<LitemallAdmin> querySelective(String username, Integer page, Integer limit, String sort, String order);
}
