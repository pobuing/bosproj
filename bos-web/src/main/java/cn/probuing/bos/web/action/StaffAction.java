package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Staff;
import cn.probuing.bos.service.IStaffService;
import cn.probuing.bos.utils.PageBean;
import cn.probuing.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
    //属性驱动
    private int page;
    private int rows;

    /**
     * 添加取派员
     *
     * @return 跳转到列表页面
     */
    public String add() {
        staffService.save(model);
        return LIST;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * 分页查询
     *
     * @return
     */
    public String pageQuery() throws IOException {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建离线查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);
        //转换json
        JsonConfig config = new JsonConfig();
        //设置排除数据
        config.setExcludes(new String[]{"currentPage", "detachedCriteria", "pageSize"});
        String json = JSONObject.fromObject(pageBean,config).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }

    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }


    /**
     * 删除取派员 逻辑删除
     * @return
     */
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     *
     * @return
     */
    public String edit(){

        Staff staff = staffService.findById(model.getId());
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }
}
