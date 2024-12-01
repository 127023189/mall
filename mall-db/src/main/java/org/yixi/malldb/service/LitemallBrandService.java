package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallBrand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_brand(品牌商表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallBrandService extends IService<LitemallBrand> {

    List<LitemallBrand> querySelective(String id, String name, Integer page, Integer limit, String sort, String order);
}
