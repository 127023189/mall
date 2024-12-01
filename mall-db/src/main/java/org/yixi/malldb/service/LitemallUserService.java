package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_user(用户表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallUserService extends IService<LitemallUser> {

    List<LitemallUser> querySelective(String username, String moblie, Integer page, Integer limit, String sort, String order);

    List<LitemallUser> queryByUsername(String username);

    LitemallUser queryByOid(String openId);
}
