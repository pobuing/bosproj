package cn.probuing.bos.service;

import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.utils.PageBean;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/23 17:32
 * @Description:
 */
public interface IStaffService {
    void save(Staff model);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);

    List<Staff> findListNotDelete();
}
