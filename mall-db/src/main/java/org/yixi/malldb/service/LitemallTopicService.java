package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallTopic;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_topic(专题表)】的数据库操作Service
* @createDate 2024-11-08 15:46:04
*/
public interface LitemallTopicService extends IService<LitemallTopic> {

    List<LitemallTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order);
}
