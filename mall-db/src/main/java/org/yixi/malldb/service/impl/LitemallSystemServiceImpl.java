package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.yixi.malldb.bean.LitemallSystem;
import org.yixi.malldb.service.LitemallSystemService;
import org.yixi.malldb.mapper.LitemallSystemMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author hp
* @description 针对表【litemall_system(系统配置表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:04
*/
@Service
public class LitemallSystemServiceImpl extends ServiceImpl<LitemallSystemMapper, LitemallSystem>
    implements LitemallSystemService{

    @Autowired
    private LitemallSystemMapper systemMapper;
    @Override
    public Map<String, String> queryAll() {
        // 构建查询条件
        QueryWrapper<LitemallSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", false);  // 指定字段名进行查询

        // 执行查询
        List<LitemallSystem> systemList = systemMapper.selectList(queryWrapper);

        // 将结果存入 Map 中
        Map<String, String> systemConfigs = new HashMap<>();
        for (LitemallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }
        return systemConfigs;
    }

    @Override
    public void addConfig(String key, String value) {
        LitemallSystem system = new LitemallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        this.save(system);
    }

    @Override
    public Map<String, String> listMail() {
        // 创建 QueryWrapper 进行条件查询
        QueryWrapper<LitemallSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("key_name", "litemall_mall_")
                .eq("deleted", false);

        // 查询数据
        List<LitemallSystem> systemList = this.list(queryWrapper);


        // 转化为 Map
        Map<String, String> data = new HashMap<>();
        for (LitemallSystem system : systemList) {
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    @Override
    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            // 构建更新条件
            UpdateWrapper<LitemallSystem> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("key_name", entry.getKey()) // 条件：key_name 等于当前键
                    .eq("deleted", false);         // 条件：未逻辑删除

            // 构建更新对象
            LitemallSystem system = new LitemallSystem();
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());

            // 执行更新
            this.update(system, updateWrapper);

        }
    }

    @Override
    public Map<String, String> listOrder() {
        // 构建查询条件
        QueryWrapper<LitemallSystem> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("key_name", "litemall_order_%")  // 条件：key_name 包含 "litemall_order_"
                .eq("deleted", false);               // 条件：未被逻辑删除

        // 查询符合条件的记录
        List<LitemallSystem> systemList = this.list(queryWrapper);

        // 转换为 Map<Key, Value>
        return systemList.stream()
                .collect(Collectors.toMap(LitemallSystem::getKeyName, LitemallSystem::getKeyValue));
    }
}




