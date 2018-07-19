package cn.probuing.bos.impl;

import cn.probuing.bos.dao.IUserDao;
import cn.probuing.bos.dao.base.impl.BaseDaoImpl;
import cn.probuing.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 10:15
 * @Description:
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
    @Override
    public User findUserByUserNameAndPassword(String username, String password) {

        String hql = "FROM User u where u.username=? AND u.password=?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}
