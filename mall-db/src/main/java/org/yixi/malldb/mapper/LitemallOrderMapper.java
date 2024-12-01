package org.yixi.malldb.mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.yixi.malldb.bean.LitemallOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yixi.malldb.bean.OrderVo;

import java.util.List;
import java.util.Map;

/**
* @author hp
* @description 针对表【litemall_order(订单表)】的数据库操作Mapper
* @createDate 2024-11-08 15:46:03
* @Entity generator.domain.LitemallOrder
*/
public interface LitemallOrderMapper extends BaseMapper<LitemallOrder> {

    @MapKey("id")
    List<Map> getOrderIds(@Param("query") String query, @Param("orderByClause") String orderByClause);

    List<OrderVo> getOrderList(@Param("query") String query, @Param("orderByClause") String orderByClause);
}




