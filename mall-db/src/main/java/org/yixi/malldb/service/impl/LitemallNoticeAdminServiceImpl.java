package org.yixi.malldb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.util.StringUtils;
import org.yixi.malldb.bean.LitemallNotice;
import org.yixi.malldb.bean.LitemallNoticeAdmin;
import org.yixi.malldb.service.LitemallNoticeAdminService;
import org.yixi.malldb.mapper.LitemallNoticeAdminMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
* @author hp
* @description 针对表【litemall_notice_admin(通知管理员表)】的数据库操作Service实现
* @createDate 2024-11-08 15:46:03
*/
@Service
public class LitemallNoticeAdminServiceImpl extends ServiceImpl<LitemallNoticeAdminMapper, LitemallNoticeAdmin>
    implements LitemallNoticeAdminService{

    @Override
    public int countUnread(Integer adminId) {  // 统计未读通知
        QueryWrapper<LitemallNoticeAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_id", adminId) // admin_id = adminId
                .isNull("read_time")     // read_time IS NULL
                .eq("deleted", false);   // deleted = false
        return this.count(queryWrapper);
    }

    @Override
    public List<LitemallNoticeAdmin> querySelective(String title, String type, Integer adminId, Integer page, Integer limit, String sort, String order) {
        QueryWrapper<LitemallNoticeAdmin> queryWrapper = new QueryWrapper<>();

// 模糊查询
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("notice_title", title); // 使用数据库列名
        }

// 根据 type 判断是否已读或未读
        if ("read".equals(type)) {
            queryWrapper.isNotNull("read_time");
        } else if ("unread".equals(type)) {
            queryWrapper.isNull("read_time");
        }

// 其他过滤条件
        queryWrapper.eq("admin_id", adminId)
                .eq("deleted", false);

// 动态排序
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            if ("asc".equalsIgnoreCase(order)) {
                queryWrapper.orderByAsc(sort); // 直接传入列名字符串
            } else if ("desc".equalsIgnoreCase(order)) {
                queryWrapper.orderByDesc(sort); // 直接传入列名字符串
            }
        }

// 分页查询
        Page<LitemallNoticeAdmin> pageResult = new Page<>(page, limit);
        return this.page(pageResult, queryWrapper).getRecords();
    }

    @Override
    public LitemallNoticeAdmin find(Integer noticeId, Integer adminId) {
        QueryWrapper<LitemallNoticeAdmin> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("notice_id", noticeId)
                .eq("admin_id", adminId)
                .eq("deleted", false);

        return this.getOne(queryWrapper);
    }

    @Override
    public void batchRead(List<Integer> ids, Integer adminId) {
        LambdaUpdateWrapper<LitemallNoticeAdmin> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.in(LitemallNoticeAdmin::getId, ids)
                .eq(LitemallNoticeAdmin::getAdminId, adminId)
                .eq(LitemallNoticeAdmin::getDeleted, false);

        LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
        LocalDateTime now = LocalDateTime.now();
        noticeAdmin.setReadTime(now);
        noticeAdmin.setUpdateTime(now);

        this.update(noticeAdmin, lambdaUpdateWrapper);
    }

//    @Override
//    public void deletedById(Integer id, Integer adminId) {
//                QueryWrapper<LitemallNoticeAdmin> queryWrapper = new QueryWrapper<>();
//
//                queryWrapper.eq("id", id)
//                        .eq("admin_id", adminId)
//                        .eq("deleted", false);
//
//                LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
//                noticeAdmin.setUpdateTime(LocalDateTime.now());
//                noticeAdmin.setDeleted(1);
//
//                this.update(noticeAdmin, queryWrapper);
//
//    }

    @Override
    public void deleteByIds(List<Integer> ids, Integer adminId) {
        QueryWrapper<LitemallNoticeAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids)  // id 在 ids 列表中的记录
                .eq("admin_id", adminId)  // adminId 匹配
                .eq("deleted", false);  // 未被删除的记录

// 创建要更新的实体
        LitemallNoticeAdmin noticeAdmin = new LitemallNoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());  // 更新当前时间
        noticeAdmin.setDeleted(1);  // 设置删除标志为 true（1）

// 执行更新
        this.update(noticeAdmin, queryWrapper);
    }

    @Override
    public List<LitemallNotice> queryByNoticeId(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(id!=null){
            queryWrapper.eq("notice_id", id);
        }
        queryWrapper.eq("deleted", false);
        return this.list(queryWrapper);
    }

    @Override
    public Integer countReadByNoticeId(Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("notice_id", id);
        queryWrapper.isNotNull("read_time");
        queryWrapper.eq("deleted", false);
        return this.count(queryWrapper);
    }

    @Override
    public void updateByNoticeId(LitemallNoticeAdmin noticeAdmin, Integer id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("notice_id", id);
        queryWrapper.eq("deleted", false);
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        this.update(noticeAdmin, queryWrapper);
    }
}




