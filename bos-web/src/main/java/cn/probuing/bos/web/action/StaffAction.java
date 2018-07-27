package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.service.IStaffService;
import cn.probuing.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

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
    private String ids;

    /**
     * 添加取派员
     *
     * @return 跳转到列表页面
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public String pageQuery() throws IOException {
        staffService.pageQuery(pageBean);
        this.java2json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize"});
        return NONE;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


    /**
     * 删除取派员 逻辑删除
     *
     * @return
     */
    public String deleteBatch() {
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     * @return
     */
    public String edit() {

        Staff staff = staffService.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }


    /**
     * 查询所有未删除的取派员 返回json
     * @return
     */
    public String listajax(){
        List<Staff> list =  staffService.findListNotDelete();
        this.java2Json(list,new String[]{"decidedzones"});
        return NONE;

    }
}
