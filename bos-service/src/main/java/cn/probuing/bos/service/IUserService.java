package cn.probuing.bos.service;

import cn.probuing.bos.domain.User;
import cn.probuing.bos.utils.PageBean;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 09:25
 * @Description:
 */
public interface IUserService {
    public User login(User model);

    /**
     * 修改密码
     *
     * @param id
     * @param password
     */
    void editPassword(String id, String password);

    void save(User model, String[] roleIds);

    void pageQuery(PageBean pageBean);
}
