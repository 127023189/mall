package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallFeedback;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_feedback(意见反馈表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallFeedbackService extends IService<LitemallFeedback> {

    List<LitemallFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order);
}
