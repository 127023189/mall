package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_address(收货地址表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallAddressService extends IService<LitemallAddress> {

    List<LitemallAddress> querySelective(String userId, String name, Integer page, Integer limit, String sort, String order);

    LitemallAddress queryUid(Integer userId, Integer addressId);

    LitemallAddress queryDefault(Integer userId);
}
