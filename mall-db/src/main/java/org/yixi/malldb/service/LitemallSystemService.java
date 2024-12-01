package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallSystem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author hp
* @description 针对表【litemall_system(系统配置表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallSystemService extends IService<LitemallSystem> {
    Map<String,String> queryAll();
    void addConfig(String key, String value);

    Map<String, String> listMail();

    void updateConfig(Map<String, String> data);

    Map<String, String> listOrder();
}
