package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallGrouponRules;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_groupon_rules(团购规则表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallGrouponRulesService extends IService<LitemallGrouponRules> {

    List<LitemallGrouponRules> querySelective(String grouponRuleId, Integer page, Integer limit, String sort, String order);

    Integer countByGoodsId(Integer goodsId);
}
