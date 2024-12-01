package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallKeyword;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_keyword(关键字表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallKeywordService extends IService<LitemallKeyword> {

    List<LitemallKeyword> querySelective(String keyword, String url, Integer page, Integer limit, String sort, String order);
}
