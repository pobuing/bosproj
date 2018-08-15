package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IUserDao;
import cn.probuing.bos.domain.Role;
import cn.probuing.bos.domain.User;
import cn.probuing.bos.service.IUserService;
import cn.probuing.bos.utils.MD5Utils;
import cn.probuing.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 10:07
 * @Description:
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User login(User model) {
        //md5处理
        String password = MD5Utils.md5(model.getPassword());

        return userDao.findUserByUserNameAndPassword(model.getUsername(), password);
    }

    @Override
    public void editPassword(String id, String password) {
        //使用md5对密码加密
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editpassword", password, id);
    }

    @Override
    public void save(User model, String[] roleIds) {
        String password = model.getPassword();
        password = MD5Utils.md5(password);
        model.setPassword(password);
        userDao.save(model);
        if (roleIds != null && roleIds.length > 0) {
            for (String roleId : roleIds) {
                Role role = new Role(roleId);
                model.getRoles().add(role);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        userDao.queryPage(pageBean);
    }
}
