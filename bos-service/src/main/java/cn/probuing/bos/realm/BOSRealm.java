package cn.probuing.bos.realm;

import cn.probuing.bos.dao.IUserDao;
import cn.probuing.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/13 12:13
 * @Description:
 */
public class BOSRealm extends AuthorizingRealm {
    @Autowired
    private IUserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("realm 授权方法执行。。。");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("realm 认证方法执行。。。");
        UsernamePasswordToken myToken = (UsernamePasswordToken) authenticationToken;
        String username = myToken.getUsername();
        //根据获取的令牌的username查询数据库中的密码
        User user = userDao.findUserByUserName(username);
        if (user == null) {
            //用户名不存在
            return null;
        }
        //创建简单认证信息对象
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        return info;
    }
}
