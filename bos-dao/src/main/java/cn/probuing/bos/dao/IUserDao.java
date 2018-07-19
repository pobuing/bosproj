package cn.probuing.bos.dao;

import cn.probuing.bos.dao.base.IBaseDao;
import cn.probuing.bos.domain.User;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 10:12
 * @Description:
 */
public interface IUserDao extends IBaseDao<User> {

    User findUserByUserNameAndPassword(String username, String password);
}
