package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Function;
import cn.probuing.bos.service.IFunctionService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/15 15:13
 * @Description:
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
    @Autowired
    private IFunctionService service;

    public String listajax() {
        List<Function> list = service.findAll();
        this.java2Json(list, new String[]{"parentFunction", "roles","children"});
        return NONE;
    }


    public String add() {
        service.save(model);
        return LIST;
    }

    public String pageQuery() throws IOException {
        String page = model.getPage();
        pageBean.setCurrentPage(Integer.parseInt(page));
        service.pageQuery(pageBean);
        this.java2Json(pageBean, new String[]{"parentFunction", "roles", "children"});
        return NONE;
    }
}
