package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.User;
import cn.probuing.bos.service.IUserService;
import cn.probuing.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/19 09:08
 * @Description:
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
    private Logger logger = Logger.getLogger(this.getClass());
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private IUserService userService;
    /**
     * 登录方法
     */
    public String login() {
        //判断验证码是否输入正确
        String validateCode = (String) ActionContext.getContext().getSession().get("key");
        logger.info(validateCode+"----"+checkcode+model.toString());

        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validateCode)) {
            //正确
            User user = userService.login(model);
            if (user != null) {
                ActionContext.getContext().put("loginUser", user);
                return HOME;
            } else {
                this.addActionError("用户名或密码输入错误");
                return LOGIN;
            }
        } else {
            //错误
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }
}
