package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallNotice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_notice(通知表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallNoticeService extends IService<LitemallNotice> {

    List<LitemallNotice> querySelective(String title, String content, Integer page, Integer limit, String sort, String order);
}
