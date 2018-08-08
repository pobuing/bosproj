package cn.probuing.bos.web.action;

import cn.probuing.bos.domain.Noticebill;
import cn.probuing.bos.service.INoticebillService;
import cn.probuing.bos.web.action.base.BaseAction;
import cn.probuing.client.Customer;
import cn.probuing.client.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @Auther: wxblack-mac
 * @Date: 2018/8/8 15:29
 * @Description:
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private INoticebillService noticebillService;

    public String findCustomerByTelephone() throws IOException {
        Customer customer = customerService.findCustomerByTelephone(model.getTelephone());
        this.java2Json(customer, new String[]{});
        return NONE;
    }


    public String add(){
        noticebillService.save(model);
        return "noticebill_add";
    }

}
