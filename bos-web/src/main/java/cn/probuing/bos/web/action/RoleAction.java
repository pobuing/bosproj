package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Role;
import cn.probuing.bos.service.IRoleService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 17:05
 * @Description:
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
    private String functionIds;
    @Autowired
    private IRoleService service;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    public String add() {
        service.save(model, functionIds);
        return LIST;
    }

    public String pageQuery() throws IOException {
        service.pageQuery(pageBean);
        this.java2Json(pageBean, new String[]{"functions", "users"});
        return NONE;
    }

    public String listajax() {
        List<Role> list = service.findAll();
        this.java2Json(list, new String[]{"functions", "users"});
        return NONE;
    }
}
