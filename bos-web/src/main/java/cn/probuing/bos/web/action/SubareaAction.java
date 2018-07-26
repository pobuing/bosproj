package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Subarea;
import cn.probuing.bos.service.ISubareaService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/7/26 16:33
 * @Description:
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private ISubareaService iSubareaService;
    public String add(){
        iSubareaService.save(model);
        return LIST;
    }
}
