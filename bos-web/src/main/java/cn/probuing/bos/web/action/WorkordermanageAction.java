package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Workordermanage;
import cn.probuing.bos.service.IWorkOrderManageService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/9 11:45
 * @Description:
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
    @Autowired
    private IWorkOrderManageService workOrderManageService;

    public String add() {
        workOrderManageService.save(model);
        return NONE;
    }
}
