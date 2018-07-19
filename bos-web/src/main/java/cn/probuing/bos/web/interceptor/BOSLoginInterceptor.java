package cn.probuing.bos.web.interceptor;

import cn.probuing.bos.domain.User;
import cn.probuing.bos.utils.BOSUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 16:02
 * @Description:
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {


    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        BOSUtils.getLogger().info(actionInvocation.getProxy().getMethod());
        //判断当前session中是否有user
        User user = BOSUtils.getLoginUser();
        if (user == null) {
            return "login";
        }
        return actionInvocation.invoke();
    }
}
