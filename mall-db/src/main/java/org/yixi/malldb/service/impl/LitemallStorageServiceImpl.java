package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallStorage;
import org.yixi.malldb.service.LitemallStorageService;
import org.yixi.malldb.mapper.LitemallStorageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_storage(文件存储表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallStorageServiceImpl extends ServiceImpl<LitemallStorageMapper, LitemallStorage>
    implements LitemallStorageService{
    @Autowired
    private LitemallStorageMapper storageMapper;
    public LitemallStorage findByKey(String key) {
        LambdaQueryWrapper<LitemallStorage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LitemallStorage::getKey, key);
        return storageMapper.selectOne(queryWrapper);
    }

    @Override
    public List<LitemallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(key)){
            queryWrapper.eq("key", key);
        }
        if(!StringUtils.isEmpty(name)){
            queryWrapper.eq("name", name);
        }
        queryWrapper.eq("deleted", false);
        if(!StringUtils.isEmpty(sort)&&!StringUtils.isEmpty(order)){
            queryWrapper.orderBy(true, "ASC".equalsIgnoreCase(order), sort);
        }
        Page<LitemallStorage> pageInfo = new Page<>(page, limit);
        return this.page(pageInfo, queryWrapper).getRecords();
    }

    @Override
    public void deleteByKey(String keyName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(!StringUtils.isEmpty(keyName)){
            queryWrapper.eq("key", keyName);
        }
        queryWrapper.eq("deleted", false);
        this.remove(queryWrapper);
    }
}




