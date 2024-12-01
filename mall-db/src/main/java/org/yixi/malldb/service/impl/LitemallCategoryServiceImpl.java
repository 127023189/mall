package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.yixi.malldb.bean.LitemallCategory;
import org.yixi.malldb.service.LitemallCategoryService;
import org.yixi.malldb.mapper.LitemallCategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_category(类目表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallCategoryServiceImpl extends ServiceImpl<LitemallCategoryMapper, LitemallCategory>
    implements LitemallCategoryService{

    @Override
    public List<LitemallCategory> queryByPid(Integer pid) {
        QueryWrapper<LitemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid", pid)
                .eq("deleted", false);

        return this.list(queryWrapper);
    }

    @Override
    public List<LitemallCategory> queryL1() {
        QueryWrapper<LitemallCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("level", "L1");
        queryWrapper.eq("deleted", false);
        return this.list(queryWrapper);
    }
}




