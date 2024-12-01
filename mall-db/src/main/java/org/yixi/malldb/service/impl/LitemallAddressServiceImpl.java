package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallAddress;
import org.yixi.malldb.service.LitemallAddressService;
import org.yixi.malldb.mapper.LitemallAddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_address(收货地址表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallAddressServiceImpl extends ServiceImpl<LitemallAddressMapper, LitemallAddress>
    implements LitemallAddressService{

    @Override
    public List<LitemallAddress> querySelective(String userId, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(userId)){
            queryWrapper.eq("user_id", userId);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name", name);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallAddress> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public LitemallAddress queryUid(Integer userId, Integer addressId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id", userId);
        }
        if(addressId != null){
            queryWrapper.eq("id", addressId);
        }
        queryWrapper.eq("deleted", false);

        return this.getOne(queryWrapper);

    }

    @Override
    public LitemallAddress queryDefault(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(userId != null){
            queryWrapper.eq("user_id",userId);
        }
        queryWrapper.eq("is_default", true);
        queryWrapper.eq("deleted", false);
        return this.getOne(queryWrapper);
    }
}




