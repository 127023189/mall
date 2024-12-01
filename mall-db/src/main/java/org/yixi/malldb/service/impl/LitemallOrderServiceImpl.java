package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallOrder;
import org.yixi.malldb.bean.OrderVo;
import org.yixi.malldb.service.LitemallOrderService;
import org.yixi.malldb.mapper.LitemallOrderMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author hp
* @description 针对表【litemall_order(订单表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallOrderServiceImpl extends ServiceImpl<LitemallOrderMapper, LitemallOrder>
    implements LitemallOrderService{

    @Autowired
    private LitemallOrderMapper litemallOrderMapper;

    @Override
    public Map<String, Object> querySelective(String nickname, String consignee, String orderSn, LocalDateTime start, LocalDateTime end, List<Short> orderStatusArray, Integer page, Integer limit, String sort, String order) {
        List<String> querys = new ArrayList<>(4);
        if (!StringUtils.isEmpty(nickname)) {
            querys.add(" u.nickname like '%" + nickname + "%' ");
        }
        if (!StringUtils.isEmpty(consignee)) {
            querys.add(" o.consignee like '%" + consignee + "%' ");
        }
        if (!StringUtils.isEmpty(orderSn)) {
            querys.add(" o.order_sn = '" + orderSn + "' ");
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if (start != null) {
            querys.add(" o.add_time >= '" + df.format(start) + "' ");
        }
        if (end != null) {
            querys.add(" o.add_time < '" + df.format(end) + "' ");
        }
        if (orderStatusArray != null && orderStatusArray.size() > 0) {
            querys.add(" o.order_status in (" + StringUtils.collectionToDelimitedString(orderStatusArray, ",") + ") ");
        }
        querys.add(" o.deleted = 0 and og.deleted = 0 ");
        String query = StringUtils.collectionToDelimitedString(querys, "and");
        String orderByClause = null;
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            orderByClause = "o." + sort + " " + order +", o.id desc ";
        }

        PageHelper.startPage(page, limit);
        Page<Map> list1 =(Page) litemallOrderMapper.getOrderIds(query, orderByClause);

        List<Integer> ids = new ArrayList<>();
        for (Map map : list1) {
            Integer id = (Integer) map.get("id");
            ids.add(id);
        }

        List<OrderVo> list2 = new ArrayList<>();
        if (!ids.isEmpty()) {
            querys.add(" o.id in (" + StringUtils.collectionToDelimitedString(ids, ",") + ") ");
            query = StringUtils.collectionToDelimitedString(querys, "and");
            list2 = litemallOrderMapper.getOrderList(query, orderByClause);
        }
        // 结果封装
    Map<String, Object> data = new HashMap<>();
    data.put("list", list2);
    data.put("total", list1.getTotal());
    data.put("page", list1.getPageNum());
    data.put("limit", list1.getPageSize());
    data.put("pages", list1.getPages());

    return data;
    }

    @Override
    public boolean updateWithOptimisticLocker(LitemallOrder order) {
        order.setUpdateTime(LocalDateTime.now());
        return this.updateById(order);
    }

    @Override
    public void updateAftersaleStatus(Integer orderId, Short statusRecept) {
        // 创建 UpdateWrapper，用于指定更新条件
        UpdateWrapper<LitemallOrder> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", orderId); // 指定主键条件

        // 创建实体对象，设置需要更新的字段
        LitemallOrder order = new LitemallOrder();
        order.setAftersaleStatus(statusRecept.intValue()); // 设置需要更新的字段
        order.setUpdateTime(LocalDateTime.now()); // 设置更新时间

        // 调用 update 方法
        this.update(order, updateWrapper);
    }
}




