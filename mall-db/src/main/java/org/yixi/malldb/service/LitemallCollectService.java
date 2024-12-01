package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_collect(收藏表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCollectService extends IService<LitemallCollect> {

    List<LitemallCollect> querySelective(String userId, String valueId, Integer page, Integer limit, String sort, String order);
}
