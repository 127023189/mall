package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
* @author hp
* @description 针对表【litemall_order(订单表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallOrderService extends IService<LitemallOrder> {

    Map<String, Object> querySelective(String nickname, String consignee, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order);

    boolean updateWithOptimisticLocker(LitemallOrder order);

    void updateAftersaleStatus(Integer orderId, Short statusRecept);
}
