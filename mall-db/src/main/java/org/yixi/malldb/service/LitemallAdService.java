package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallAd;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_ad(广告表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallAdService extends IService<LitemallAd> {

    List<LitemallAd> querySelective(String username, String content,Integer page, Integer limit, String sort, String order);

    void add(LitemallAd ad);

    LitemallAd findById(Integer id);
}
