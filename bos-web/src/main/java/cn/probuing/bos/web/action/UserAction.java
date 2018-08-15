package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.User;
import cn.probuing.bos.service.IUserService;
import cn.probuing.bos.utils.BOSUtils;
import cn.probuing.bos.utils.MD5Utils;
import cn.probuing.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
    @Autowired
    private IUserService userService;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    /**
     * 登录方法
     */
    public String login() {
        //判断验证码是否输入正确
        String validateCode = (String) ActionContext.getContext().getSession().get("key");
        logger.info(validateCode + "----" + checkcode + model.toString());

        if (StringUtils.isNotBlank(checkcode) && checkcode.equals(validateCode)) {
            //使用shiro框架提供的方式进行认证
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            try {
                subject.login(token);
            } catch (Exception e) {
                this.addActionError("用户名或密码输入错误");
                e.printStackTrace();
                return LOGIN;
            }
            User user = (User) subject.getPrincipal();
            ActionContext.getContext().getSession().put("loginUser", user);
            return HOME;
        } else {
            //错误
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }


    /**
     * 用户注销退出登录
     */
    public String logOut() {
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }


    /**
     * 修改用户密码
     *
     * @return
     */
    public String editPassword() throws IOException {
        //获取当前登录的用户
        User loginUser = BOSUtils.getLoginUser();
        String f = "1";
        //调用service
        try {
            userService.editPassword(loginUser.getId(), model.getPassword());
        } catch (Exception e) {
            f = "0";
            e.printStackTrace();
        }
        //写回数据
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

    //属性驱动 接收多个角色id
    private String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    public String add(){
        userService.save(model,roleIds);
        return LIST;
    }

    public String pageQuery() throws IOException {
        userService.pageQuery(pageBean);
        this.java2Json(pageBean,new String[]{"noticebills","roles"});

        return NONE;
    }

}
