package cn.probuing.bos.web.action;

import cn.probuing.bos.dao.base.IBaseDao;
import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.service.IStaffService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/23 17:29
 * @Description:
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

    @Autowired
    private IStaffService staffService;

    /**
     * 添加取派员
     * @return 跳转到列表页面
     */
    public String add(){
        staffService.save(model);
        return LIST;
    }
}
