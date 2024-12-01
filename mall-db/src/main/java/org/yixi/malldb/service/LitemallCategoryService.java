package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_category(类目表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCategoryService extends IService<LitemallCategory> {

    List<LitemallCategory> queryByPid(Integer i);

    List<LitemallCategory> queryL1();
}
