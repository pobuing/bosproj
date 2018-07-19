package cn.probuing.bos.utils;

import cn.probuing.bos.domain.User;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 16:03
 * @Description:
 */
public class BOSUtils {
    private static Logger logger;

    public static HttpSession getSession() {
        return ServletActionContext.getRequest().getSession();
    }

    public static User getLoginUser() {

        return (User) getSession().getAttribute("loginUser");
    }

    public static Logger getLogger() {
        if (logger != null) {
            return logger;
        } else {
            return Logger.getLogger(BOSUtils.class);
        }

    }
}
