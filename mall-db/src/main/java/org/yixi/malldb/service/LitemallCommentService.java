package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_comment(评论表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallCommentService extends IService<LitemallComment> {

    List<LitemallComment> querySelective(String userId, String valueId, Integer page, Integer limit, String sort, String order);
}
