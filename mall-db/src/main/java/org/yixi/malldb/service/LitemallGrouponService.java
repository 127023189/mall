package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallGroupon;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_groupon(团购活动表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallGrouponService extends IService<LitemallGroupon> {

    List<LitemallGroupon> querySelective(String grouponRuleId, Integer page, Integer limit, String sort, String order);

    List<LitemallGroupon> queryJoinRecord(Integer id);
}