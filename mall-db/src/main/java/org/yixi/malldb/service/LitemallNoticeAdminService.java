package org.yixi.malldb.service;

import org.yixi.malldb.bean.LitemallNotice;
import org.yixi.malldb.bean.LitemallNoticeAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hp
* @description 针对表【litemall_notice_admin(通知管理员表)】的数据库操作Service
* @createDate 2024-11-08 15:46:03
*/
public interface LitemallNoticeAdminService extends IService<LitemallNoticeAdmin> {

    int countUnread(Integer adminId);

    List<LitemallNoticeAdmin> querySelective(String title, String type, Integer adminId, Integer page, Integer limit, String sort, String order);

    LitemallNoticeAdmin find(Integer noticeId, Integer adminId);

    void batchRead(List<Integer> ids, Integer adminId);

//    void deletedById(Integer id, Integer adminId);

    void deleteByIds(List<Integer> ids, Integer adminId);

    List<LitemallNotice> queryByNoticeId(Integer id);

    Integer countReadByNoticeId(Integer id);

    void updateByNoticeId(LitemallNoticeAdmin noticeAdmin, Integer id);
}
