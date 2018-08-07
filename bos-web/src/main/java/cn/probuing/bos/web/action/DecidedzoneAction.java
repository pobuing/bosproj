package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Decidedzone;
import cn.probuing.bos.service.IDecidedZoneService;
import cn.probuing.bos.web.action.base.BaseAction;
import cn.probuing.client.Customer;
import cn.probuing.client.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author wx-blackmac
 * @create 2018-08-01 22:32
 * bestwishes for you
 * good luck!
 **/
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
    public String[] subareaid;
    @Autowired
    private IDecidedZoneService iDecidedZoneService;
    //注入代理对象
    @Autowired
    private ICustomerService proxy;
    //属性驱动 接收传递的客户id
    private List<Integer> customerIds;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add() {
        iDecidedZoneService.save(model, subareaid);
        return LIST;
    }

    public String pageQuery() throws IOException {
        iDecidedZoneService.pageQuery(pageBean);
        this.java2Json(pageBean, new String[]{"currentPage", "detachedCriteria", "pageSize", "subareas", "decidedzones"});
        return NONE;
    }

    /**
     * 查询没有关联定区的客户
     *
     * @return NONE
     */
    public String findListNotAssociation() throws IOException {
        List<Customer> list = proxy.findListNotAssociation();
        if (list == null) {
            list = new ArrayList<>();
        }
        this.java2Json(list, new String[]{});
        return NONE;
    }

    /**
     * 查询已经关联定区的客户
     *
     * @return NONE
     */
    public String findListHasAssociation() throws IOException {
        String id = model.getId();
        List<Customer> list = proxy.findListHasAssociation(id);
        if (list == null) {
            list = new ArrayList<>();
        }
        this.java2Json(list, new String[]{});
        return NONE;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    /**
     * 关联客户到定区
     *
     * @return LIST
     */
    public String assigncustomerstodecidedzone() {
        proxy.assigncustomerstodecidedzone(model.getId(), customerIds);
        return LIST;
    }
}
