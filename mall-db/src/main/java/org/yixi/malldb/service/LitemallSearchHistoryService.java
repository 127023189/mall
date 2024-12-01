package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallSearchHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_search_history(搜索历史表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallSearchHistoryService extends IService<LitemallSearchHistory> {

    List<LitemallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer limit, String sort, String order);
}
