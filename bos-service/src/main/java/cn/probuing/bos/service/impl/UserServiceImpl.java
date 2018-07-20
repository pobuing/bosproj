package cn.probuing.bos.service.impl;

import cn.probuing.bos.dao.IUserDao;
import cn.probuing.bos.domain.User;
import cn.probuing.bos.service.IUserService;
import cn.probuing.bos.utils.MD5Utils;
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
}
