package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_log(操作日志表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallLogService extends IService<LitemallLog> {

    List<LitemallLog> querySelective(String name, Integer page, Integer limit, String sort, String order);
}
