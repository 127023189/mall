package org.yixi.malldb.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.yixi.malldb.bean.LitemallStorage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_storage(文件存储表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
@Mapper
public interface LitemallStorageService extends IService<LitemallStorage> {

    LitemallStorage findByKey(String key);

    List<LitemallStorage> querySelective(String key, String name, Integer page, Integer limit, String sort, String order);

    void deleteByKey(String keyName);
}
