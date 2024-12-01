package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallGrouponRules;
import org.yixi.malldb.service.LitemallGrouponRulesService;
import org.yixi.malldb.mapper.LitemallGrouponRulesMapper;
import org.springframework.stereotype.Service;
import org.yixi.malldb.util.GrouponConstant;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_groupon_rules(团购规则表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallGrouponRulesServiceImpl extends ServiceImpl<LitemallGrouponRulesMapper, LitemallGrouponRules>
    implements LitemallGrouponRulesService{

    @Override
    public List<LitemallGrouponRules> querySelective(String grouponRuleId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(grouponRuleId)){
            queryWrapper.eq("id", grouponRuleId);
        }
        queryWrapper.eq("deleted", false);

        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }

        Page<LitemallGrouponRules> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public Integer countByGoodsId(Integer goodsId) {
        QueryWrapper<LitemallGrouponRules> queryWrapper = new QueryWrapper<>();
        if(goodsId!=null){
            queryWrapper.eq("goods_id", goodsId)
                    .eq("status", GrouponConstant.RULE_STATUS_ON);
        }
        queryWrapper.eq("deleted", false);

        return this.count(queryWrapper);
    }
}




