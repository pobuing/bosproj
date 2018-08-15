package cn.probuing.bos.service;

import cn.probuing.bos.domain.Role;
import cn.probuing.bos.utils.PageBean;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 17:07
 * @Description:
 */
public interface IRoleService {
    void save(Role model, String functionIds);

    void pageQuery(PageBean pageBean);

    List<Role> findAll();
}
